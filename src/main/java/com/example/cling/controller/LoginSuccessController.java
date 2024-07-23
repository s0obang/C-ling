package com.example.cling.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class LoginSuccessController {
    @GetMapping("/login_success")
    public String showLoginSuccessPage() {
        return "login_success";
    }
}
