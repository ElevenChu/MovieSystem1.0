package com.movie.mapper;

import com.movie.bean.Users;

public interface UsersMapper {
    Users user_login(Users u);
    int user_registry(Users u);
}
