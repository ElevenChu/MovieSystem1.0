package com.movie.mapper;

import com.movie.bean.Film;
import com.movie.vo.FilmVo;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;



public interface FilmMapper {

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

    /**
     * 新增电影
     * @param film
     * @return
     */
    int addFilm(Film film);

    /**
     * 新增电影类别中间表
     * @return
     */
    int addFilm_type(@Param("category_id") int category_id, @Param("film_id") int film_id);

    /**
     * 新增编剧和电影中间表
     * @param film_id
     * @param screenwriter_id
     * @return
     */
    int addScreenwriter_film( @Param("film_id") int film_id,@Param("screenwriter_id") int screenwriter_id);

    /**
     * 新增主演和电影的中间表
     * @param film_id
     * @param performer_id
     * @return
     */
    int addStar(@Param("film_id") int film_id,@Param("performer_id") int performer_id);

}
