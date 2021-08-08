package com.movie.service.impl;

import com.movie.mapper.BuyTicketMapper;
import com.movie.service.BuyTicketService;
import com.movie.vo.PlayVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class BuyTicketServiceImpl implements BuyTicketService {
    @Autowired
    BuyTicketMapper buyTicketMapper;
    @Override
    public List<PlayVo> findRoomInfoByFilmId(Integer film_id) {

        return buyTicketMapper.findRoomInfoByFilmId(film_id);
    }
}
