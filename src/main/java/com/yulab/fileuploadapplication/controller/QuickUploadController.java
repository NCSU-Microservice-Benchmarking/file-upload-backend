package com.yulab.fileuploadapplication.controller;

import com.yulab.fileuploadapplication.service.FileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * quick upload
 */
@RestController
@RequestMapping("/QuickUpload")
@CrossOrigin
public class QuickUploadController {
    @Autowired
    private FileService fileService;

    @GetMapping("/")
    public boolean upload(String md5) {
        return fileService.checkMd5(md5);
    }
}
