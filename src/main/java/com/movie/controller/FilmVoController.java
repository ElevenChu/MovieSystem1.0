package com.movie.controller;

import com.movie.bean.FilmCategory;
import com.movie.service.FilmCategoryService;
import com.movie.service.FilmVoService;
import com.movie.utils.PageUtil;
import com.movie.vo.FilmVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @BelongsProject: MovieSystem
 * @BelongsPackage: com.movie.web
 * @CreateTime: 2020-10-19 11:29
 * @Description: 后台电影信息分页
 */
@Controller
public class FilmVoController {

    @Autowired
    FilmVoService filmVoService;

    @Autowired
    FilmCategoryService filmCategoryService;

    /**
     * 01-数据分页
     * @return
     */
    @RequestMapping("/filmvo_page")
    public String page(@RequestParam(name ="pageIndex",defaultValue = "1") Integer pageIndex,
                       @RequestParam(name ="pageSize",defaultValue = "5") Integer pageSize, Model model){
        //总数
        int totalCount = filmVoService.totalcount();
        Map<String,Object> map=new HashMap<>();
        map.put("startPage",(pageIndex-1)*pageSize);
        map.put("pageSize",pageSize);
        //每页数据
        List<FilmVo> filmCategorys = filmVoService.findFilmsVo(map);
        //封装一个分页工具类
        PageUtil<FilmVo> pageUtil=new PageUtil<FilmVo>(pageIndex,pageSize,totalCount,filmCategorys);
        //存值
        model.addAttribute("pageUtil",pageUtil);
        return "admin_film_list";
    }


    /**
     * 下拉框数据
     * @return
     */
    @ResponseBody
    @RequestMapping("/getFilmCategorys")
    public List<FilmCategory> getFilmCategorys(){
        return filmCategoryService.getAllFilmCategorys();
    }

}
