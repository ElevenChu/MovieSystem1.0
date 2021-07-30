package com.movie.controller;

import com.movie.bean.FilmCategory;
import com.movie.service.FilmCategoryService;
import com.movie.utils.CommonResult;
import com.movie.utils.PageUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @BelongsProject: MovieSystem
 * @BelongsPackage: com.movie.web
 * @CreateTime: 2020-10-15 11:22
 * @Description: TODO
 */
@Controller
public class FilmCategoryController {

    @Autowired
    FilmCategoryService filmCategoryService;

    /**
     * 04-更新电影类别
     */
    @ResponseBody
    @RequestMapping(value = "/filmCategory_update",method = RequestMethod.POST)
    public CommonResult update(FilmCategory filmCategory){
        System.out.println("要更新的对象是:"+filmCategory);
        CommonResult result;
        int count = filmCategoryService.updateFilmCategory(filmCategory);
        if(count>0){
            result=new CommonResult(200,"电影类别更新成功");
        }else{
            result=new CommonResult(500,"电影类别更新失败");
        }
        return result;
    }

    /**
     * 根据ID查询
     * @param category_id
     * @return
     */
    @ResponseBody
    @RequestMapping("/filmCategory_toupdate")
    public FilmCategory filmCategory_toupdate(Integer category_id){
        return filmCategoryService.findById(category_id);
    }

    /**
     * 删除
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/delete_FilmCategory",method = RequestMethod.GET)
    public CommonResult delete_FilmCategory(Integer category_id){
        System.out.println("删除ID："+category_id);
        CommonResult result;
        int count = filmCategoryService.deleteFilmCategory(category_id);
        if(count>0){
            result=new CommonResult(200,"电影类别删除成功");
        }else{
            result=new CommonResult(500,"电影类别删除失败");
        }
        return result;
    }

    /**
     * 02-新增电影类别
     */
    @ResponseBody
    @RequestMapping(value = "/filmCategory_add",method = RequestMethod.POST)
    public CommonResult filmCategory_add(FilmCategory filmCategory){
        System.out.println("要新增的对象是:"+filmCategory);
        CommonResult result;
        int count = filmCategoryService.addFilmCategory(filmCategory);
        if(count>0){
            result=new CommonResult(200,"电影类别添加成功");
        }else{
            result=new CommonResult(500,"电影类别添加失败");
        }
        return result;
    }

    /**
     * 01-数据分页
     * @return
     */
    @RequestMapping("/filmCategory_page")
    public String page(@RequestParam(name ="pageIndex",defaultValue = "1") Integer pageIndex,
                       @RequestParam(name ="pageSize",defaultValue = "5") Integer pageSize, Model model){
        //总数
        int totalCount = filmCategoryService.totalCount();
        Map<String,Object> map=new HashMap<>();
        map.put("pageStart",(pageIndex-1)*pageSize);
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
