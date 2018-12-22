package com.example.demo_uploadfile_and_savepathfile.controller;

import com.example.demo_uploadfile_and_savepathfile.model.Image;
import com.example.demo_uploadfile_and_savepathfile.model.UploadFile;
import com.example.demo_uploadfile_and_savepathfile.service.ImageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.io.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Controller
public class UploadFileController {

    @Autowired
    private ImageService imageService;
    @GetMapping("/")
    public ModelAndView homePage() {
        return new ModelAndView("index");
    }

    @GetMapping("/uploadOneFile")
    public ModelAndView uploadOneFileGet() {
        UploadFile uploadFile = new UploadFile();
        ModelAndView modelAndView = new ModelAndView("uploadOneFile");
        modelAndView.addObject("uploadFile", uploadFile);
        return modelAndView;
    }

    @PostMapping("/uploadOneFile")
    public ModelAndView uploadOneFilePost(HttpServletRequest httpServletRequest, @ModelAttribute("uploadFile") UploadFile uploadFile) {
        return doUpload(httpServletRequest, uploadFile);
    }

    @GetMapping("/uploadMultiFile")
    public ModelAndView uploadMultiFilesGet() {
        UploadFile uploadFile = new UploadFile();
        ModelAndView modelAndView = new ModelAndView("uploadMultiFile");
        modelAndView.addObject("uploadFile", uploadFile);
        return modelAndView;
    }

    @PostMapping("/uploadMultiFile")
    public ModelAndView uploadMultiFilesPost(HttpServletRequest httpServletRequest, @ModelAttribute("uploadFile") UploadFile uploadFile) {
        return doUpload(httpServletRequest, uploadFile);
    }
    public ModelAndView doUpload(HttpServletRequest httpServletRequest, UploadFile uploadFile) {
        String description = uploadFile.getDescription();
        System.out.println("Description: " + description);

        //Thu muc goc de save fileupload tren server
//        String uploadRootPath = httpServletRequest.getServletContext().getRealPath("/home/dat/upload");
//        System.out.println("uploadRootPath = " + uploadRootPath);

        File uploadRootDir = new File("/home/dat/uploads");

        //Tao thu muc goc neu no khong ton tai
        if (!uploadRootDir.exists()) {
            uploadRootDir.mkdirs();
        }

        MultipartFile[] files = uploadFile.getMultipartFiles();

        List<File> uploadedFiles = new ArrayList<>();
        List<String> failedFiles = new ArrayList<>();

        for (MultipartFile file : files) {
            //Ten goc tai Client
            String name = file.getOriginalFilename();
            System.out.println("Client File Name = " + name);

            Date date = new Date();

            String url = "";
            url= String.valueOf((name + date.toString()).hashCode());
            Image image = new Image(url);
            imageService.save(image);

            if (name != null && name.length() > 0) {
                try {
                    File serverFile = new File(uploadRootDir.getAbsolutePath() + File.separator + name);
                    BufferedOutputStream stream = new BufferedOutputStream(new FileOutputStream(serverFile));
                    stream.write(file.getBytes());
                    stream.close();
                    //
                    uploadedFiles.add(serverFile);
                    System.out.println("Write file: " + serverFile);
                } catch (IOException e) {
                    System.out.println("Error Write file: " + name);
                    failedFiles.add(name);
                }
            }
        }
        ModelAndView modelAndView = new ModelAndView("uploadResult");
        modelAndView.addObject("description", description);
        modelAndView.addObject("uploadFiles", uploadedFiles);
        modelAndView.addObject("failedFiles", failedFiles);
        return modelAndView;
    }
}
