package com.zxa.controller;

import com.zxa.common.ReturnEntity;
import com.zxa.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@RequestMapping("/index")
@Controller
public class IndexController {
    @Autowired
    CategoryService categoryService;
    @GetMapping(value = {"","/index"})
    public String index(Model model){
        model.addAttribute("category",categoryService.getCategory());
        return "index";
    }

    @GetMapping(value = "/login")
    public String login(Model model){
        model.addAttribute("category",categoryService.getCategory());
        return "login";
    }

    @GetMapping(value = "/register")
    public String register(Model model){
        model.addAttribute("category",categoryService.getCategory());
        return "register";
    }

    @GetMapping(value = {"/product/{id}","/product/product/{id}"})
    public String product(@PathVariable int id, Model model){
        model.addAttribute("category",categoryService.getCategory());
        return "product";
    }

    @GetMapping(value = "/single/{id}")
    public String single(@PathVariable int id, Model model){
        model.addAttribute("category",categoryService.getCategory());
        return "single";
    }

    @GetMapping(value = "/contact/{id}")
    public String contact(@PathVariable int id, Model model){
        model.addAttribute("category",categoryService.getCategory());
        return "contact";
    }

    @ResponseBody
    @GetMapping("listCategory")
    public ReturnEntity listCategory(){
        return ReturnEntity.success(categoryService.getCategory());
    }

}
