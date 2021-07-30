package com.movie.service.impl;

import com.movie.bean.Film;
import com.movie.mapper.FilmMapper;
import com.movie.service.FilmService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * @BelongsProject: MovieSystem
 * @BelongsPackage: com.movie.service.impl
 * @CreateTime: 2020-10-16 11:07
 * @Description: TODO
 */
@Service
public class FilmServiceImpl implements FilmService {

    @Autowired
    FilmMapper filmMapper;

    @Override
    public List<Film> findLatestFilms(int count) {
        return filmMapper.findLatestFilms(count);
    }

    @Override
    public List<Film> findFilmsByFilmCategory(int category_id) {
        return filmMapper.findFilmsByFilmCategory(category_id);
    }

    @Override
    public int totalcount(Map<String, Object> map) {
        return filmMapper.totalcount(map);
    }

    @Override
    public List<Film> findFilms(Map<String, Object> map) {
        return filmMapper.findFilms(map);
    }
}
