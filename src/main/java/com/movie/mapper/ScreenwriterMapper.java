package com.movie.mapper;

import com.movie.bean.Screenwriter;

import java.util.List;
import java.util.Map;

public interface ScreenwriterMapper {
    List<Screenwriter> findScreenwriter();
    int totalCount();

    List<Screenwriter> getScreenwriters(Map<String,Object> map);

    Screenwriter findById(Integer screenwriter_id);

    int addScreenwriter(Screenwriter screenwriter);

    int updateScreenwriter(Screenwriter screenwriter);

    int deleteScreenwriter(Integer screenwriter_id);
}
