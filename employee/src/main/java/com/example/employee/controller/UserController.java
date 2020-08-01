package com.example.employee.controller;

import com.example.employee.model.User;
import com.example.employee.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Controller

public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/")
    public String index(Model model){
        List<User> users = userService.getAllUser();
        model.addAttribute("users", users);
        model.addAttribute("user", new User());
        return "index";
    }

    @PostMapping("/save")
    public String save(@ModelAttribute("user") User user){
        userService.saveUser(user);
        return "redirect:/";
    }

    @GetMapping("/user/delete/{id}")
    public String delete(@PathVariable("id") Long id){
        userService.deleteUser(id);
        return "redirect:/";
    }

    @GetMapping("/user/edit/{id}")
    public String edit(@PathVariable("id") Long id,Model model){
        Optional<User> userEdit = userService.findUserById(id);
        userEdit.ifPresent(user->model.addAttribute("user", user));

        return "redirect:/";
    }
}
