package com.example.demo_uploadfile_and_savepathfile.model;

import org.springframework.web.multipart.MultipartFile;

public class UploadFile {
    private String description;
    private MultipartFile[] multipartFiles;

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public MultipartFile[] getMultipartFiles() {
        return multipartFiles;
    }

    public void setMultipartFiles(MultipartFile[] multipartFiles) {
        this.multipartFiles = multipartFiles;
    }
}
