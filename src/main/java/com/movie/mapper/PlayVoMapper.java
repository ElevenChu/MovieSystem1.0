package com.movie.mapper;

import com.movie.vo.PlayVo;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface PlayVoMapper {

    /**
     * 查询总数
     * @return
     */
    int totalcount();

    /**
     * 查询每页数据
     * @param map
     * @return
     */
    List<PlayVo> findPlayVo(Map<String,Object> map);

    //新增
    int addPlayVo(PlayVo playVo);

    int changeState(@Param("play_id") Integer play_id, @Param("is_delete") Integer is_delete);
}
