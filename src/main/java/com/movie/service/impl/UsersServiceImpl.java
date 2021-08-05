package com.movie.service.impl;

import com.movie.bean.Users;
import com.movie.mapper.UsersMapper;
import com.movie.service.UsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UsersServiceImpl implements UsersService {
    @Autowired
    UsersMapper usersMapper;

    @Override
    public Users user_login(Users u) {
        return usersMapper.user_login(u);
    }

    @Override
    public int user_registry(Users u) {
        return usersMapper.user_register(u);
    }

    @Override
    public int user_updateInfomation(Users users) {
        return usersMapper.user_updateInfomation(users);
    }

    @Override
    public Users findById(int user_id) {
        return usersMapper.findById(user_id);
    }

    @Override
    public int updatePhoto(Integer user_id, String newPhoto) {
        return usersMapper.updatePhoto(user_id,newPhoto);
    }
}
