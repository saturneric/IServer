package com.bktus.iserver.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(value = "/error")
public class ErrorController {

    @RequestMapping(value = "/")
    public String printErrorView(){
        return "500";
    }
}
