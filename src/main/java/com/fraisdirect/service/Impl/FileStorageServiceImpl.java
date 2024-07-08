package com.fraisdirect.service.Impl;

import com.fraisdirect.service.FileStorageService;
import jakarta.validation.constraints.NotNull;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

@Service
public class FileStorageServiceImpl implements FileStorageService {
    @Value("${application.file.upload.photos-output-path}")
    private String fileUploadPath;

    @Override
    public List<String> saveFile(
            @NotNull List<MultipartFile> sources,
            @NotNull Long userId) {
        final String fileUploadSubPath = "products" + File.separator + userId;
        return uploadFile(sources, fileUploadSubPath);
    }

    private List<String> uploadFile(
            @NotNull List<MultipartFile> sources,
            @NotNull String fileUploadSubPath) {
        final String finalUploadPath = fileUploadPath + File.separator + fileUploadSubPath;
        File targetFolded = new File(finalUploadPath);
        List<String> paths = new ArrayList<>();
        boolean exists = targetFolded.exists();
        if (!exists) {
            boolean folderCreated = targetFolded.mkdirs();
            if (!folderCreated){
                throw new RuntimeException("La creation du dossier à échouer");
            }
        }
        sources.forEach(source -> {
            String fileExtension = this.getFileExtension(source.getOriginalFilename());
            String targetFilePath = finalUploadPath + File.separator+ System.currentTimeMillis()+"."+fileExtension;
            paths.add(targetFilePath);
            Path targetPath = Paths.get(targetFilePath);
            try {
                Files.write(targetPath,source.getBytes());
            } catch (IOException e) {
                throw new RuntimeException("File not seved "+e);
            }
        });
        return paths;
    }

    private String getFileExtension(String fileName) {
        if (fileName == null || fileName.isEmpty()) {
            return "";
        }
        int lastDotIndex = fileName.lastIndexOf(".");
        if (lastDotIndex == -1) {
            return "";
        }
        return fileName.substring(lastDotIndex + 1).toLowerCase();
    }
}
