package com.example.demo_uploadfile_and_savepathfile;

import com.example.demo_uploadfile_and_savepathfile.service.ImageService;
import com.example.demo_uploadfile_and_savepathfile.service.ImageServiceImpl;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;

@SpringBootApplication
public class DemoUploadFileAndSavePathFileApplication{
    @Bean
    public ImageService imageService() {
        return new ImageServiceImpl();
    }

	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		registry.addResourceHandler("/resources/**")
				.addResourceLocations("file:///home/dat/uploads/");
	}

	public static void main(String[] args) {
		SpringApplication.run(DemoUploadFileAndSavePathFileApplication.class, args);
	}

}

