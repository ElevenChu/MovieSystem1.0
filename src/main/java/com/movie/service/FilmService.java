package com.movie.service;

import com.movie.bean.Film;

import java.util.List;
import java.util.Map;

public interface FilmService {
    /**
     * 01-查询最新上映的电影 推荐电影
     * @return
     */
    List<Film> findLatestFilms(int count);

    /**
     * 根据类型查询电影
     * @param category_id
     * @return
     */
    List<Film> findFilmsByFilmCategory(int category_id);

    /**
     * 查询总数
     * @param map
     * @return
     */
    int totalcount(Map<String,Object> map);

    /**
     * 查询每页数据
     * @param map
     * @return
     */
    List<Film> findFilms(Map<String,Object> map);
}
