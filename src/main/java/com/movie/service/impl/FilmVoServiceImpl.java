package com.movie.service.impl;

import com.movie.mapper.FilmVoMapper;
import com.movie.service.FilmVoService;
import com.movie.vo.FilmVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
@Service
public class FilmVoServiceImpl implements FilmVoService {
   @Autowired
    FilmVoMapper filmVoMapper;
    @Override
    public int totalcount() {
        return filmVoMapper.totalcount();
    }

    @Override
    public List<FilmVo> findFilmsVo(Map<String, Object> map) {
        return filmVoMapper.findFilmsVo(map);
    }
}
