package com.example.mercadonabackend.api.auth;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;


@Controller
@RequiredArgsConstructor
public class UserController {

    // get login page
    @GetMapping("/login")
    public String loginPage() {
        return "login";
    }


    // logout link
    @GetMapping("/logout")
    public String logout() {
        return "index";
    }


}
