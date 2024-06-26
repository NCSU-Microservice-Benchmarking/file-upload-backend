package com.yulab.fileuploadapplication.controller;

import com.yulab.fileuploadapplication.service.FileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

/**
 * big file upload
 */
@RestController
@RequestMapping("/BigFile")
@CrossOrigin
public class BigFileUploadController {
    @Autowired
    private FileService fileService;


    /**
     *
     * @param name
     * @param md5
     * @param chunks    total chunks
     * @param chunk     current chunk index
     * @param file
     * @throws IOException
     */
    @PostMapping("/")
    public void upload(String name,
                       String md5,
                       Long size,
                       Integer chunks,
                       Integer chunk,
                       MultipartFile file) throws IOException {
        if (chunks != null && chunks != 0) {
            fileService.uploadWithBlock(name, md5,size,chunks,chunk,file);
        } else {
            fileService.upload(name, md5,file);
        }
    }
}
