package com.commsult_test.clone_ig.utils;

import java.io.IOException;

import org.springframework.web.multipart.MultipartFile;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.Files;

import java.io.File;


public class FileUploadUtil {

    public static String getFileExtension(String fileName, String notFound){
        int dotIndex = fileName.lastIndexOf(".");

        return dotIndex == -1 ? notFound : fileName.substring(dotIndex);
    }

    public static String uploadFile(MultipartFile file, String fileName, String folderName) throws IOException {
        byte[] bytes = file.getBytes();
        String fileNameExtension = fileName + getFileExtension(file.getOriginalFilename(), ".jpg");
        String destination = "uploads/" + folderName + fileNameExtension;

        Path path = Paths.get(destination);

        Files.write(path, bytes);

        return fileNameExtension;
    }

    public static File getFile(String path){
        File file = new File(path);

        return file;
    }

}
