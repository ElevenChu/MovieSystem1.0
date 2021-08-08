package com.movie.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PlayVo {
    private Integer play_id;
    private String play_time;
    private Integer film_id;
    private String film_name;  //扩展字段
    private Integer room_id;
    private String room_name; //扩展字段
    private Double money;
    private String create_time;
    private String update_time;
    private Integer is_delete;
}
