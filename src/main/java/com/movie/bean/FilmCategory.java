package com.movie.bean;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class FilmCategory {
    private Integer category_id;
    private String film_category;
    private Integer is_delete;




}
