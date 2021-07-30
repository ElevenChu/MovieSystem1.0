package com.movie.service;

import com.movie.vo.FilmVo;

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
}
