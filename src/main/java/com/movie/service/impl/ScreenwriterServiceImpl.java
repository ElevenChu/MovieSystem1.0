package com.movie.service.impl;

import com.movie.bean.Screenwriter;
import com.movie.mapper.ScreenwriterMapper;
import com.movie.service.ScreenwriterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;


@Service
public class ScreenwriterServiceImpl implements ScreenwriterService {

    @Autowired
    ScreenwriterMapper screenwriterMapper;

    @Override
    public List<Screenwriter> findScreenwriter() {
        return screenwriterMapper.findScreenwriter();
    }

    @Override
    public int totalCount() {
        return screenwriterMapper.totalCount();
    }

    @Override
    public List<Screenwriter> getScreenwriters(Map<String, Object> map) {
        return screenwriterMapper.getScreenwriters(map);
    }

    @Override
    public Screenwriter findById(Integer screenwriter_id) {
        return screenwriterMapper.findById(screenwriter_id);
    }

    @Override
    public int addScreenwriter(Screenwriter screenwriter) {
        return screenwriterMapper.addScreenwriter(screenwriter);
    }

    @Override
    public int updateScreenwriter(Screenwriter screenwriter) {
        return screenwriterMapper.updateScreenwriter(screenwriter);
    }

    @Override
    public int deleteScreenwriter(Integer screenwriter_id) {
        return screenwriterMapper.deleteScreenwriter(screenwriter_id);
    }
}
