package com.movie.controller;

import com.movie.utils.CommonResult;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class ImageUploadController {
    @RequestMapping("/image_Upload")
    public CommonResult upload(){
        CommonResult result=new CommonResult(200,"");
        return result;
    }
}
