package com.movie.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class TicketVo {

    private String order_id;
    private String film_name;
    private String room_name;
    private String seat;
    private Double money;
    private String play_time;
    private String buying_time;

}
