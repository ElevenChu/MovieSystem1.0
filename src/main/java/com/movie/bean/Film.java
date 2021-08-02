package com.movie.bean;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class Film {

    private Integer film_id;
    private String film_name;
    private String film_time;
    private String director;
    private Integer play_time;
    private String poster_url;
    private Integer is_delete;
    private String description;


}
