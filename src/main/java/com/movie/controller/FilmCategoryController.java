package com.movie.controller;

import com.movie.bean.FilmCategory;
import com.movie.service.FilmCategoryService;
import com.movie.utils.PageUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class FilmCategoryController {
    @Autowired
    private FilmCategoryService filmCategoryService;

//数据分页
@RequestMapping("/filmCategory_page")
 public String page(
         @RequestParam(value = "pageIndex",defaultValue = "1") Integer pageIndex,
         @RequestParam(value = "pageSize",defaultValue = "5") Integer pageSize,
         Model model
){
    //总数
    int totalCount = filmCategoryService.totalCount();
    Map<String,Object> map=new HashMap<>();
    map.put("startPage",(pageIndex-1)*pageSize);
    map.put("pageSize",pageSize);
    //每页数据
    List<FilmCategory> filmCategorys = filmCategoryService.getFilmCategorys(map);
    //封装一个分页工具类
    PageUtil<FilmCategory> pageUtil=new PageUtil<>(pageIndex,pageSize,totalCount,filmCategorys);
    //存值
    model.addAttribute("pageUtil",pageUtil);

    return "movie_type";
 }

}
