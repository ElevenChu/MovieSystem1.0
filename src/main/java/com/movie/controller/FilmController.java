package com.movie.controller;

import com.movie.bean.Film;
import com.movie.bean.FilmCategory;
import com.movie.service.FilmCategoryService;
import com.movie.service.FilmService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class FilmController {
    @Autowired
    private FilmService filmService;
    @Autowired
    private FilmCategoryService filmCategoryService;
    @RequestMapping("/index")
    public String film_index(Model model){
        Map<String,List<Film>> map=new HashMap<>();
        //查询推荐的四个电影
        List<Film> latestFilms = filmService.findLatestFilms(4);
        List<FilmCategory> allFilmCategorys = filmCategoryService.getAllFilmCategorys();
        for (FilmCategory category : allFilmCategorys) {
            Integer category_id = category.getCategory_id();
            List<Film> films = filmService.findFilmsByFilmCategory(category_id);
            map.put(category.getFilm_category(),films);
        }
        model.addAttribute("map",map);
        model.addAttribute("latestFilms",latestFilms);
        return "user_home";
    }

}
