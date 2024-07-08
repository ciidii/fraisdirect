package com.fraisdirect.service;

import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface FileStorageService {
    public List<String> saveFile(List<MultipartFile> files, Long userId);
}
