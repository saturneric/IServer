package com.bktus.iserver.controller;

import com.bktus.iserver.model.User;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/user")
public class UserController {

    @RequestMapping(value = "profile/{username}")
    public String printProfileView(Model model, @PathVariable String username, Authentication authentication){
        User user = (User) authentication.getPrincipal();
        model.addAttribute("username", user.getUsername());
        model.addAttribute("nickname", user.getUserInfo().getNickName());
        model.addAttribute("user", user);
        return "profile";
    }

}
