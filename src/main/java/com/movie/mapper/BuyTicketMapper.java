package com.movie.mapper;

import com.movie.vo.FilmVo;
import com.movie.vo.PlayVo;

import java.util.List;

public interface BuyTicketMapper {
    List<PlayVo> findRoomInfoByFilmId(Integer film_id);
}
