package com.movie.service.impl;

import com.movie.mapper.PlayVoMapper;
import com.movie.service.PlayVoService;
import com.movie.vo.PlayVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class PlayVoServiceImpl implements PlayVoService {

    @Autowired
    PlayVoMapper playVoMapper;

    @Override
    public int totalcount() {
        return playVoMapper.totalcount();
    }

    @Override
    public List<PlayVo> findPlayVo(Map<String, Object> map) {
        return playVoMapper.findPlayVo(map);
    }

    @Override
    public int addPlayVo(PlayVo playVo) {
        return playVoMapper.addPlayVo(playVo);
    }

    @Override
    public int changeState(Integer play_id, Integer is_delete) {
        return playVoMapper.changeState(play_id,is_delete);
    }
}
