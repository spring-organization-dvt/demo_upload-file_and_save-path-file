package com.example.demo_uploadfile_and_savepathfile.service;

import com.example.demo_uploadfile_and_savepathfile.model.Image;

import java.util.Optional;

public interface ImageService {
    void save(Image image);

    Optional<Image> findById(Integer id);
}
