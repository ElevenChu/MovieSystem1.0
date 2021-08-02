package com.movie.bean;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Screenwriter {
    private Integer screenwriter_id;
    private String screenwriter_name;
    private Integer is_delete;
}
