package com.movie.service.impl;

import com.movie.bean.Introduce;
import com.movie.mapper.IntroduceMapper;
import com.movie.service.IntroduceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class IntroduceServiceImpl implements IntroduceService {
   @Autowired
    IntroduceMapper introduceMapper;
    @Override
    public List<Introduce> findIntroduce() {
        return introduceMapper.findIntroduce();
    }
}
