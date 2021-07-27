package com.movie.bean;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Admin {
    private Integer admin_id;
    private String admin_name;
    private String account;
    private String admin_pwd;
    private String img_url;
    private String create_time;
    private String update_time;
    private Integer is_delete;

}
