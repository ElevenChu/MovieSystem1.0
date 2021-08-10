package com.movie.mapper;

import com.movie.vo.PlayVo;
import com.movie.vo.TicketVo;
import org.apache.ibatis.annotations.Param;

import java.util.List;


public interface TicketMapper {

    int getSeatIdBySeatName(@Param("seatName") String seatName);

    int addTicket(@Param("order_id") String order_id,@Param("play_id") int play_id,@Param("user_id") int user_id,@Param("seat_id") int seat_id);

    List<TicketVo> findMyTickets(int user_id);
}
