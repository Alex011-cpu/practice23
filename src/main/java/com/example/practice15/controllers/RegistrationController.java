package com.example.practice15.controllers;

import com.example.practice15.models.Role;
import com.example.practice15.models.User;
import com.example.practice15.services.UserDetailServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/registration")
public class RegistrationController {

    @Autowired
    private UserDetailServiceImpl userService;

    @Autowired
    BCryptPasswordEncoder bCryptPasswordEncoder;

    @GetMapping
    public String registration(Model model){
        model.addAttribute("UserForm",new User());
        return "registration";
    }

    @PostMapping
    public String addUser(@ModelAttribute("UserForm") User userForm, @CookieValue("JSESSIONID") String cookie,
                          Model model) {

        if (userForm.getEmail().equals(userService.findUserByEmail(userForm.getEmail()).getEmail())){
            model.addAttribute("errorMessage","Пользователь с такой почтой уже существует");
            return "redirect:registration";
        } else {
            userForm.setPassword(bCryptPasswordEncoder.encode(userForm.getPassword()));
            userForm.setRole(Role.USER);
            userService.saveUser(userForm, cookie);
            return "redirect:/";
        }
    }
}
