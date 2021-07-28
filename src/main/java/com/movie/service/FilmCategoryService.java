package com.movie.service;

import com.movie.bean.FilmCategory;

import java.util.List;
import java.util.Map;

public interface FilmCategoryService {
    int totalCount();
    List<FilmCategory> getFilmCategorys(Map<String,Object> map);
    FilmCategory findById(Integer category_id);
    int addFilmCategory(FilmCategory filmCategory);
    int updateFilmCategory(FilmCategory filmCategory);
    int deleteFilmCategory(Integer category_id);
}
