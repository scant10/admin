package com.example.admin.controller;

import com.example.admin.model.SearchParam;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class PostController {

    @PostMapping(value = "postMethod")
    public SearchParam PostMethod(@RequestBody SearchParam searchParam){
        return searchParam;
    }
}
