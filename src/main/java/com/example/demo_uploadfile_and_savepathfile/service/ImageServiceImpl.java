package com.example.demo_uploadfile_and_savepathfile.service;

import com.example.demo_uploadfile_and_savepathfile.model.Image;
import com.example.demo_uploadfile_and_savepathfile.repository.ImageRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Optional;

public class ImageServiceImpl implements ImageService {
    @Autowired
    private ImageRepository imageRepository;

    @Override
    public void save(Image image) {
        imageRepository.save(image);
    }

    @Override
    public Optional<Image> findById(Integer id) {
        return imageRepository.findById(id);
    }

}
