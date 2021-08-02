package com.movie.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class FilmVo {

    private Integer film_id;
    private String film_name;
    private String film_category;
    private String poster_url;
    private String director;
    private String screenwriter_name;
    private String performer_name;
    private String film_time;
    private Integer play_time;
    private Integer is_delete;
    private String description;
    private Integer category_id;
    private Integer screenwriter_id;
    private Integer performer_id;
}
