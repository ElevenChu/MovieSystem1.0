package com.movie.service;

import com.movie.vo.TicketVo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface TicketService {

    int getSeatIdBySeatName(String seatName);

    int addTicket( String order_id,int play_id, int user_id,@Param("seat_id") int seat_id);

    List<TicketVo> findMyTickets(int user_id);
}
