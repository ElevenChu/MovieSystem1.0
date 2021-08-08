package com.movie.service;

import com.movie.vo.PlayVo;

import java.util.List;

public interface BuyTicketService {
    List<PlayVo> findRoomInfoByFilmId(Integer film_id);
}
