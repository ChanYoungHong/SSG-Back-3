package com.spharosacademy.project.SSGBack.s3.controller;

import com.spharosacademy.project.SSGBack.s3.service.S3UploaderService;
import com.spharosacademy.project.SSGBack.s3.dto.ImageDto;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@RequestMapping("/img")
@RequiredArgsConstructor
public class ImageController {

    private final S3UploaderService s3UploaderService;

    @PostMapping("/")
    public void IMG(@RequestParam("images") MultipartFile multipartFile){
        try {
            s3UploaderService.upload(multipartFile, "myspharosbucket", "myDir");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
