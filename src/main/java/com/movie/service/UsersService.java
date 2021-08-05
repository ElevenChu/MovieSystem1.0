package com.movie.service;

import com.movie.bean.Users;
import org.apache.ibatis.annotations.Param;

public interface UsersService {
    Users user_login(Users u);
    int user_registry(Users u);
    int user_updateInfomation(Users users);

    Users findById(int user_id);
    int updatePhoto(Integer user_id, String newPhoto);
}
