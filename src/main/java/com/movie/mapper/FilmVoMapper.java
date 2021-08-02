package com.movie.mapper;

import com.movie.bean.Film;
import com.movie.vo.FilmVo;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;


public interface FilmVoMapper {

    /**
     * 查询总数
     * @return
     */
    int totalcount();

    /**
     * 查询每页数据
     * @param map
     * @return
     */
    List<FilmVo> findFilmsVo(Map<String,Object> map);


    /**
     * 查询电影详细信息
     * @param film_id
     * @return
     */
    FilmVo findFilmVoById(int film_id);


    /**
     * 上架下架
     * @param film_id
     * @param is_delete
     * @return
     */
    int updateState(@Param("film_id") Integer film_id,@Param("is_delete") Integer is_delete);
}
