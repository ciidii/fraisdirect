package com.fraisdirect.utils;


import org.apache.commons.lang3.StringUtils;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class FilesUtils {
    public static byte[] readFileFileFromLocation(String filesUrl) {
        if (StringUtils.isBlank(filesUrl)) {
            return null;
        }
        try {
            Path filePath = new File(filesUrl).toPath();
            return Files.readAllBytes(filePath);
        } catch (IOException e) {
        }
        return new byte[0];
    }
}
