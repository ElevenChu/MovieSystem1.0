package com.movie.service.impl;

import com.movie.bean.Performer;
import com.movie.mapper.PerformerMapper;
import com.movie.service.PerformerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PerformerServiceImpl implements PerformerService {
    @Autowired
    PerformerMapper performerMapper;

    @Override
    public List<Performer> findPerformers() {
        return performerMapper.findPerformers();
    }
}
