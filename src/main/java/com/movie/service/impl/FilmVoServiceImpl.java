package com.movie.service.impl;

import com.movie.bean.Film;
import com.movie.mapper.FilmVoMapper;
import com.movie.service.FilmService;
import com.movie.service.FilmVoService;
import com.movie.vo.FilmVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;


@Service
public class FilmVoServiceImpl implements FilmVoService {

    @Autowired
    FilmVoMapper filmVoMapper;

    @Autowired
    FilmService filmService;

    @Override
    public int totalcount() {
        return filmVoMapper.totalcount();
    }

    @Override
    public List<FilmVo> findFilmsVo(Map<String, Object> map) {
        return filmVoMapper.findFilmsVo(map);
    }



    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public int addFilmVo(FilmVo filmVo) {
        Film film=new Film(null,filmVo.getFilm_name(),filmVo.getFilm_time(),filmVo.getDirector(),filmVo.getPlay_time(),filmVo.getPoster_url(), 1,filmVo.getDescription());
        int count1=filmService.addFilm(film);
        System.out.println("电影新增成功!");
        int count2=filmService.addFilm_type(filmVo.getCategory_id(),film.getFilm_id());
        System.out.println("电影类别中间表新增成功!");
        int count3=filmService.addScreenwriter_film(film.getFilm_id(),filmVo.getScreenwriter_id());
        System.out.println("电影编剧中间表新增成功!");
        int count4=filmService.addStar(film.getFilm_id(),filmVo.getPerformer_id());
        System.out.println("电影主演中间表新增成功!");
        return count1+count2+count3+count4;
    }

    @Override
    public FilmVo findFilmVoById(int film_id) {
        return filmVoMapper.findFilmVoById(film_id);
    }

    @Override
    public int updateState(Integer film_id, Integer is_delete) {
        return filmVoMapper.updateState(film_id,is_delete);
    }
}
