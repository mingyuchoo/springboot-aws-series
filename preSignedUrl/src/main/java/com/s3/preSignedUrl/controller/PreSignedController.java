package com.s3.preSignedUrl.controller;


import com.s3.preSignedUrl.service.PreSignedService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.net.URL;

@Controller
public class PreSignedController {

    @Autowired
    private PreSignedService service;

    @GetMapping(value="/home", produces = { MediaType.APPLICATION_JSON_VALUE})
    @ResponseBody
    public URL home() {
        return service.get();
    }
}
