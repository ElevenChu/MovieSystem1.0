package com.movie.service.impl;

import com.movie.bean.Admin;
import com.movie.mapper.AdminMapper;
import com.movie.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AdminServiceImpl implements AdminService {
    @Autowired
    private AdminMapper adminMapper;

    @Override
    public Admin login(Admin admin) {
        return adminMapper.login(admin);
    }
}
