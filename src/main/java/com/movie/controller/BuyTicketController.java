package com.movie.controller;

import com.movie.service.BuyTicketService;
import com.movie.utils.CommonResult;
import com.movie.vo.PlayVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
public class BuyTicketController {
    @Autowired
    BuyTicketService buyTicketService;
    /**
     * 根据选中的电影查询电影场次信息
     *
     * @param film_id
     * @param model
     * @return
     */
    @RequestMapping("/buy_findRoomInfoByFilmId")
    public String findRoomInfoByFilmId(Integer film_id, Model model) {
        List<PlayVo> playVos = buyTicketService.findRoomInfoByFilmId(film_id);
        model.addAttribute("playVos", playVos);
        return "user_buy_film";
    }
    /**
     * 返回电影名字和ROOM
     *
     * @param play_id
     * @param film_id
     * @return
     */
    @ResponseBody
    @RequestMapping("/buy_getFilmInfoAndRoomInfo")
    public CommonResult getFilmInfoAndRoomInfo(Integer play_id, Integer film_id) {
        PlayVo playVo = null;
        List<PlayVo> playVos = buyTicketService.findRoomInfoByFilmId(film_id);
        for (PlayVo p : playVos) {
            if (p.getPlay_id().toString().equals(play_id.toString())) {
                playVo = p;
                break;
            }
        }
        return new CommonResult(200, "", playVo);
    }
    /**
     * 查询已经售出的座位
     *
     * @param play_id
     * @return
     */
    @ResponseBody
    @RequestMapping("/buy_findSelledSeatByPlayId")
    public List<String> findSelledSeatByPlayId(Integer play_id) {
        return buyTicketService.findSelledSeatByPlayId(play_id);
    }
}
