package com.movie.service.impl;

import com.movie.bean.Film;
import com.movie.mapper.FilmMapper;
import com.movie.service.FilmService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;


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

    @Override
    public int addFilm(Film film) {
        return filmMapper.addFilm(film);
    }

    @Override
    public int addFilm_type(int category_id, int film_id) {
        return filmMapper.addFilm_type(category_id,film_id);
    }

    @Override
    public int addScreenwriter_film(int film_id, int screenwriter_id) {
        return filmMapper.addScreenwriter_film(film_id,screenwriter_id);
    }

    @Override
    public int addStar(int film_id, int performer_id) {
        return filmMapper.addStar(film_id,performer_id);
    }
}
