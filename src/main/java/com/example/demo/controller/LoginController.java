package com.example.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * create by guanhao 2018-12-13
 **/
@Controller
@RequestMapping("")
public class LoginController {

    @RequestMapping(value = "/")
    public String main(){
        return "redirect:/login";
    }

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String login() {
        return "login.html";
    }
}
