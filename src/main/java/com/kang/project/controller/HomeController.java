package com.kang.project.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

    /*  메인 화면   */
    @GetMapping("/")
    public String home() {
        return "index";
    }

    /*  회원가입 화면 이동  */
    @GetMapping("/auth/join")
    public String join() {
        return "auth/join";
    }

    /*  로그인 화면 이동   */
    @GetMapping("/auth/login")
    public String login() {
        return "auth/login";
    }
}
