package com.movie.mapper;

import com.movie.bean.Users;
import org.apache.ibatis.annotations.Param;

public interface UsersMapper {
    Users user_login(Users u);

    int user_register(Users u);

    int user_updateInfomation(Users users);

    Users findById(int user_id);

    int updatePhoto(@Param("user_id") Integer user_id, @Param("newPhoto") String newPhoto);
}
