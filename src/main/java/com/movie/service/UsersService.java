package com.movie.service;

import com.movie.bean.Users;

public interface UsersService {
    Users user_login(Users u);
    int user_registry(Users u);
}
