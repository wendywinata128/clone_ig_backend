package com.commsult_test.clone_ig.controllers;

import org.springframework.core.io.FileSystemResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;

import com.commsult_test.clone_ig.dto.BasicResponse;

import java.io.File;

public class BasicController {
    protected <T> ResponseEntity<BasicResponse<T>> respondSuccess(T data) {
        var basicResponse =  BasicResponse.<T>builder()
                .data(data)
                .status(200)
                .message("Request successfull")
                .build();

        return new ResponseEntity<>(basicResponse, HttpStatus.OK);
    }

    protected ResponseEntity<FileSystemResource> sendFile(File file) {

        HttpHeaders headers = new HttpHeaders();

        headers.add(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + file.getName() + "\"");
        headers.add(HttpHeaders.CONTENT_TYPE, MediaType.IMAGE_JPEG_VALUE);  

        FileSystemResource resource = new FileSystemResource(file);

        return new ResponseEntity<>(resource, headers, HttpStatus.OK);
    }
}
