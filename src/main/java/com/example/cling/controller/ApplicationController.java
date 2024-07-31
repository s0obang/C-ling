package com.example.cling.controller;

import com.example.cling.service.ApplicationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ApplicationController {

    private final ApplicationService applicationService;

    @Autowired
    public ApplicationController(ApplicationService applicationService) {
        this.applicationService = applicationService;
    }



}
