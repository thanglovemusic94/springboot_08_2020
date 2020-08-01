package com.example.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class HomeController {

    @GetMapping("/")
    public String index(){
        return "hello";
    }

    @GetMapping("/{name}")
    public String name(@PathVariable("name") String name, Model model){
        model.addAttribute("name", name);
        return "hello";
    }

    @GetMapping("/thongtin")
    public String thongtin(){
        return "thongtin";
    }

//    @PostMapping("/thongtinchitiet")
//    public String thongtinchitiet(@RequestParam("name") String name, Model model){
//        model.addAttribute("name", name);
//        return "thongtinchitiet";
//    }





//    @PostMapping("/thongtin")
//    public String postthongtin(@RequestParam(value = "name", required = false) String name, Model model){
//        model.addAttribute("name", name);
//        return "redirect:/thongtin";
//    }
}
