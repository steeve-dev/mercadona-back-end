package com.example.mercadonabackend.api.auth;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;


@Controller
@RequiredArgsConstructor
public class UserController {

    @GetMapping("/login")
    public String loginPage() {
        return "login";
    }


    @GetMapping("/logout")
    public String logout() {
        return "index";
    }


}
