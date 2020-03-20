package com.bktus.iserver.controller;

import com.bktus.iserver.model.User;
import org.apache.tomcat.util.descriptor.web.ContextHandler;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.security.Principal;

@Controller
@RequestMapping(value = "/")
public class IndexController {

    @RequestMapping(value = "/")
    public String indexView(Model model, Authentication authentication){
        User user = (User) authentication.getPrincipal();
        model.addAttribute("username", authentication.getName());
        model.addAttribute("nickname", user.getUserInfo().getNickName());
        return "index";
    }

}
