package com.southwind.mmall002.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import javax.servlet.http.HttpSession;

@Controller
public class RedirectController {

    @GetMapping("/{url}")
    public String redirect(@PathVariable("url") String url){
        return url;
    }

    @GetMapping({"/main","","/"})
    public String mian(){
        return "redirect:/productCategory/list";
    }
}
