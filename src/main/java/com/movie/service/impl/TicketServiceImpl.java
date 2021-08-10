package com.movie.service.impl;

import com.movie.mapper.TicketMapper;
import com.movie.service.TicketService;
import com.movie.vo.TicketVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class TicketServiceImpl implements TicketService {

    @Autowired
    TicketMapper ticketMapper;

    @Override
    public int getSeatIdBySeatName(String seatName) {
        return ticketMapper.getSeatIdBySeatName(seatName);
    }

    @Override
    public int addTicket(String order_id,int play_id, int user_id, int seat_id) {
        return ticketMapper.addTicket(order_id,play_id,user_id,seat_id);
    }

    @Override
    public List<TicketVo> findMyTickets(int user_id) {
        return ticketMapper.findMyTickets(user_id);
    }
}
