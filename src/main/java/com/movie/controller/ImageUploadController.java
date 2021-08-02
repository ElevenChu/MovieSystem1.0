package com.movie.controller;

import com.movie.utils.CommonResult;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.util.UUID;

@Controller
public class ImageUploadController {
    @ResponseBody
    @RequestMapping("/image_Upload")
    public CommonResult upload(HttpServletRequest request, CommonsMultipartFile file){
      //要上传的原始文件名
        String filename = file.getOriginalFilename();
        System.out.println("要上传的原始文件名是"+filename);//XXX.jpg
        String fileKuozhanming = filename.substring(filename.lastIndexOf("."));
        System.out.println("要上传文件的扩展名"+fileKuozhanming);
        //生成一个新的永不重复的文件名
        String newFileName = UUID.randomUUID().toString() + fileKuozhanming;
        System.out.println("生成一个永不重复的文件名"+newFileName);
        //获取真实的文件上传路径
        String realPath =request.getServletContext().getRealPath("/photo")+"/"+newFileName;
        System.out.println("上传的地址是"+realPath);
        //开始文件上传
        try {
            file.transferTo(new File(realPath));
            System.out.println("文件上传成功");
            return new CommonResult(200,"文件上传成功",newFileName);
        } catch (IOException e) {
            e.printStackTrace();
            return new CommonResult(500,"文件上传异常");
        }


    }
}
