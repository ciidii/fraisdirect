package com.fraisdirect.utils;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ResponseMaker<T> {
    private T t;

}
