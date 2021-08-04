package com.movie.bean;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;



@Data
@AllArgsConstructor
@NoArgsConstructor
public class Users {

    private Integer user_id;
    private String user_name;
    private String user_account;
    private String user_password;
    private Integer sex;
    private String user_emli;
    private String user_phone;
    private String img_url;
    private String create_time;
    private String update_time;
    private String last_ip;  //最后一次登录的ID
    private Integer is_delete;

    public Users(String user_name, String user_account, String user_password, Integer sex, String user_emli, String user_phone) {
        this.user_name = user_name;
        this.user_account = user_account;
        this.user_password = user_password;
        this.sex = sex;
        this.user_emli = user_emli;
        this.user_phone = user_phone;
    }
}
