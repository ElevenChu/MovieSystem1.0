package com.movie.bean;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Performer {
    private Integer performer_id;
    private String performer_name;
    private Integer is_delete;
}
