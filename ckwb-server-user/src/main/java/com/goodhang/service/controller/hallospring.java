package com.goodhang.service.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class hallospring {
    @GetMapping("ckwb8081")
    public String cs(){
        return "hi ckwb8081";
    }
}
