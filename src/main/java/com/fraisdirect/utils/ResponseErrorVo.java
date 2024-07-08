package com.fraisdirect.utils;

import lombok.Data;

import org.springframework.util.ObjectUtils;

@Data

public class ResponseErrorVo<T> {
    private String code;
    private String message;
    private String description;
    public ResponseErrorVo(String code, String message) {
        this.code = code;
        this.message = message;
    }
    public ResponseErrorVo(String code, String message, String description) {
        this.code = code;
        this.message = message;
        this.description = description;
    }
    public String getDescription() {
        return ObjectUtils.isEmpty(description) ? message : description;
    }
}