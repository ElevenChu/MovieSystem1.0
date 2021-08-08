package com.movie.controller;

import com.movie.bean.Film;
import com.movie.bean.FilmCategory;
import com.movie.bean.Room;
import com.movie.service.FilmCategoryService;
import com.movie.service.FilmService;
import com.movie.service.PlayVoService;
import com.movie.service.RoomService;
import com.movie.utils.PageUtil;
import com.movie.vo.PlayVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class PlayVoController {

    @Autowired
    PlayVoService playVoService;

    @Autowired
    RoomService roomService;

    @Autowired
    FilmService filmService;

    @Autowired
    FilmCategoryService filmCategoryService;
    /**
     * 下拉框绑定1
     * @return
     */
    @ResponseBody
    @RequestMapping("/playvo_findRooms")
    public List<Room> findRooms(){
        return roomService.findRooms();
    }

    /**
     * 下拉框绑定2
     * @return
     */
    @ResponseBody
    @RequestMapping("/playvo_findFilmsCategorys")
    public List<FilmCategory> findFilmsCategorys(){
        return filmCategoryService.getAllFilmCategorys();
    }

    /**
     * 下拉框绑定3
     * @return
     */
    @ResponseBody
    @RequestMapping("/playvo_findFilmsByCategoryId")
    public List<Film> findFilmsByCategoryId(Integer category_id){
        return filmService.findFilmsByFilmCategory(category_id);
    }
    /**
     * 01-数据分页
     * @return
     */
    @RequestMapping("/playvo_page")
    public String page(@RequestParam(name ="pageIndex",defaultValue = "1") Integer pageIndex,
                       @RequestParam(name ="pageSize",defaultValue = "5") Integer pageSize, Model model){
        //总数
        int totalCount = playVoService.totalcount();
        Map<String,Object> map=new HashMap<>();
        map.put("startPage",(pageIndex-1)*pageSize);
        map.put("pageSize",pageSize);
        //每页数据
        List<PlayVo> playVos = playVoService.findPlayVo(map);
        //封装一个分页工具类
        PageUtil<PlayVo> pageUtil=new PageUtil<PlayVo>(pageIndex,pageSize,totalCount,playVos);
        //存值
        model.addAttribute("pageUtil",pageUtil);
        return "admin_film_room";
    }
}
