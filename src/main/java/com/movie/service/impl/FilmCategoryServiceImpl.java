package com.movie.service.impl;

import com.movie.bean.FilmCategory;
import com.movie.mapper.FilmCategoryMapper;
import com.movie.service.FilmCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class FilmCategoryServiceImpl implements FilmCategoryService {
    @Autowired
    private FilmCategoryMapper filmCategoryMapper;
    @Override
    public int totalCount() {
        return filmCategoryMapper.totalCount();
    }

    @Override
    public List<FilmCategory> getFilmCategorys(Map<String, Object> map) {
        return filmCategoryMapper.getFilmCategorys(map);
    }

    @Override
    public FilmCategory findById(Integer category_id) {
        return filmCategoryMapper.findById(category_id);
    }

    @Override
    public int addFilmCategory(FilmCategory filmCategory) {
        return filmCategoryMapper.addFilmCategory(filmCategory);
    }

    @Override
    public int updateFilmCategory(FilmCategory filmCategory) {
        return updateFilmCategory(filmCategory);
    }

    @Override
    public int deleteFilmCategory(Integer category_id) {
        return filmCategoryMapper.deleteFilmCategory(category_id);
    }
}
