package com.commsult_test.clone_ig.controllers;

import java.io.File;

import org.springframework.core.io.FileSystemResource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.commsult_test.clone_ig.utils.FileUploadUtil;

import lombok.extern.slf4j.Slf4j;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@RestController
@RequestMapping("/files")
@Slf4j
public class FileController extends BasicController {

    @GetMapping("/post/{fileName}")
    public ResponseEntity<FileSystemResource> getFile(@PathVariable String fileName) {

        File file = FileUploadUtil.getFile("uploads/POSTS/" + fileName);
        
        return sendFile(file);
    }

    @GetMapping("/user/{fileName}")
    public ResponseEntity<FileSystemResource> getFileUser(@PathVariable String fileName) {

        File file = FileUploadUtil.getFile("uploads/USERS/" + fileName);
        
        return sendFile(file);
    }
}
