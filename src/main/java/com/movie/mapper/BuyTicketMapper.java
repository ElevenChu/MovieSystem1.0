package com.movie.mapper;

import com.movie.vo.FilmVo;
import com.movie.vo.PlayVo;

import java.util.List;

public interface BuyTicketMapper {
    List<PlayVo> findRoomInfoByFilmId(Integer film_id);

    PlayVo findPlayVoById(Integer play_id);

    /**
     * 查询某个场次已经售出的座位
     * @param play_id
     * @return
     */
    List<String> findSelledSeatByPlayId(Integer play_id);
}
