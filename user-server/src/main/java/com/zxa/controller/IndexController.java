package com.zxa.controller;

import com.github.tobato.fastdfs.domain.StorePath;
import com.github.tobato.fastdfs.service.FastFileStorageClient;
import com.zxa.common.ApplicationConstant;
import com.zxa.common.ReturnEntity;
import com.zxa.pojo.GoodsDto;
import com.zxa.service.CategoryService;
import com.zxa.service.GoodsService;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RequestMapping("/index")
@Controller
public class IndexController {
    @Autowired
    CategoryService categoryService;
    @Autowired
    FastFileStorageClient fastFileStorageClient;
    @Autowired
    GoodsService goodsService;
    @Value("${default.image.path}")
    private String imagePath;

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
        model.addAttribute("id",id);
        model.addAttribute("category",categoryService.getCategory());
        return "product";
    }

    @GetMapping(value = "/single/{id}")
    public String single(@PathVariable int id, Model model){
        model.addAttribute("good",goodsService.getGoodsSingle(id));
        model.addAttribute("category",categoryService.getCategory());
        return "single";
    }

    @GetMapping(value = "/contact/{id}")
    public String contact(@PathVariable int id, Model model){
        model.addAttribute("category",categoryService.getCategory());
        return "contact";
    }

    @ResponseBody
    @GetMapping("/listCategory")
    public ReturnEntity listCategory(){
        return ReturnEntity.success(categoryService.getCategory());
    }

    @ResponseBody
    @PostMapping("/uploadPic")
    public ReturnEntity uploadPic(MultipartFile file) throws IOException {
        if(file.isEmpty()){
            return ReturnEntity.error(ApplicationConstant.PARAMETER_ERROR);
        }
        StorePath storePath=fastFileStorageClient.uploadFile(file.getInputStream(),file.getSize(),"png",null);
        String url=imagePath+"/"+storePath.getFullPath();
        return ReturnEntity.success(url);

    }

}
