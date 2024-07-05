package com.fraisdirect.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("catalogue")
public class CatalogueController{
    @GetMapping("all")
    String hi(){
        return "hello";
    }
}
