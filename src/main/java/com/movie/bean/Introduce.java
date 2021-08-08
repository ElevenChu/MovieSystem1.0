package com.movie.bean;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class Introduce {

    private Integer id;
    private String address;
    private String tel;
    private String qq;
    private String weixin;
    private String content;
    private String map;

}
