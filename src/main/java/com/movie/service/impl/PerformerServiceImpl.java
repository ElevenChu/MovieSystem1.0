package com.movie.service.impl;

import com.movie.bean.Performer;
import com.movie.mapper.PerformerMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PerformerServiceImpl {
    @Autowired
    PerformerMapper performerMapper;
    List<Performer> findPerformers(){
        return performerMapper.findPerformers();
    }
}
