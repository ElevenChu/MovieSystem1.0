package com.movie.controller;

import com.movie.bean.Film;
import com.movie.bean.FilmCategory;
import com.movie.service.FilmCategoryService;
import com.movie.service.FilmService;
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
public class IndexController {

    @Autowired
    FilmService filmService;

    @Autowired
    FilmCategoryService filmCategoryService;

    /**
     * 01-电影首页的数据
     *
     * @param model
     * @return
     */
    @RequestMapping({"/index","/"})
    public String film_index(Model model) {
        //查询推荐的4个电影
        List<Film> latestFilms = filmService.findLatestFilms(4);
        //动作片
        List<Film> filmsByFilmCategory_1 = filmService.findFilmsByFilmCategory(1);
        //爱情片
        List<Film> filmsByFilmCategory_2 = filmService.findFilmsByFilmCategory(2);
        //谍战片
        List<Film> filmsByFilmCategory_3 = filmService.findFilmsByFilmCategory(3);
        model.addAttribute("filmsByFilmCategory_1", filmsByFilmCategory_1);
        model.addAttribute("filmsByFilmCategory_2", filmsByFilmCategory_2);
        model.addAttribute("filmsByFilmCategory_3", filmsByFilmCategory_3);
        model.addAttribute("latestFilms", latestFilms);
        return "user_home";
    }

    /**
     * 搜索电影
     * @param pageIndex
     * @param model
     * @return
     */
    @RequestMapping("/searchFilm")
    public String searchFilm(@RequestParam(name ="pageIndex",defaultValue = "1") Integer pageIndex,
                             @RequestParam(name ="film_name",defaultValue = "") String film_name,
                             Model model){
        //页面大小
        Integer pageSize=8;

        Map<String,Object> map=new HashMap<>();
        map.put("film_name",film_name);
        map.put("pageStart",(pageIndex-1)*pageSize);
        map.put("pageSize",pageSize);

        int totalCount = filmService.totalcount(map);
        List<Film> films = filmService.findFilms(map);
        //封装一个分页工具类
        PageUtil<Film> pageUtil=new PageUtil<Film>(pageIndex,pageSize,totalCount,films);
        //存值
        model.addAttribute("pageUtil",pageUtil);
        //存值--搜索条件数据回显
        model.addAttribute("film_name",film_name);
        return "user_list_film"; //电影搜素数据页码
    }

}
