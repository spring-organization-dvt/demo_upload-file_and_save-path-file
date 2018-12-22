package com.example.demo_uploadfile_and_savepathfile.repository;

import com.example.demo_uploadfile_and_savepathfile.model.Image;
import org.springframework.data.repository.CrudRepository;

public interface ImageRepository extends CrudRepository<Image, Integer> {
}
