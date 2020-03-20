package com.bktus.iserver.controller;

import com.bktus.iserver.component.form.RegisterForm;
import com.bktus.iserver.model.User;
import com.bktus.iserver.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;

@Controller
@RequestMapping(value = "/register")
public class RegisterController {

    @Resource
    private UserService userService;

    @RequestMapping(value = "")
    public String printRegisterView(Model model){
        model.addAttribute("registerForm", new RegisterForm());
        return "register";
    }

    @PostMapping(value = "")
    public String doRegisterAction(@ModelAttribute RegisterForm registerForm){
        if(!registerForm.getPassword().equals(registerForm.getRetryPassword())){
            return "register";
        }

        if(userService.checkUsernameRegistered(registerForm.getUsername())){
            return "register";
        }

        User user = userService.createUser(registerForm.getUsername(), registerForm.getPassword());

        userService.save(user);

        return "login";
    }

}
