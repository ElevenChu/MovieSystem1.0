package com.movie.service;

import com.movie.vo.FilmVo;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;


public interface FilmVoService {

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
     * 新增电影
     * @param filmVo
     * @return
     */
    int addFilmVo(FilmVo filmVo);

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
    int updateState(Integer film_id,Integer is_delete);
}
