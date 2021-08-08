package com.movie.web;

import com.movie.bean.Film;
import com.movie.bean.FilmCategory;
import com.movie.bean.Room;
import com.movie.service.FilmCategoryService;
import com.movie.service.FilmService;
import com.movie.service.PlayVoService;
import com.movie.service.RoomService;
import com.movie.utils.CommonResult;
import com.movie.utils.PageUtil;
import com.movie.vo.FilmVo;
import com.movie.vo.PlayVo;
import org.springframework.beans.factory.annotation.Autowired;
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

    /**
     * 新增大厅
     * @return
     */
    @ResponseBody
    @RequestMapping("/playvo_add")
    public CommonResult add(PlayVo playVo){
        CommonResult result;
        try {
            System.out.println("要新增的对象是："+playVo);
            int count = playVoService.addPlayVo(playVo);
            if(count>0){
                result=new CommonResult(200,"大厅添加成功");
            }else{
                result=new CommonResult(500,"大厅添加失败");
            }
        } catch (Exception e) {
            result=new CommonResult(500,"大厅添加失败");
            e.printStackTrace();
        }
        return result;
    }

    /**
     * 上映或下档
     * @return
     */
    @ResponseBody
    @RequestMapping("/play_changeState")
    public CommonResult play_changeState(Integer play_id,Integer is_delete){
        CommonResult result;
        try {
            int count = playVoService.changeState(play_id,is_delete);
            if(count>0){
                result=new CommonResult(200,"操作成功");
            }else{
                result=new CommonResult(500,"操作失败");
            }
        } catch (Exception e) {
            result=new CommonResult(500,"操作失败");
            e.printStackTrace();
        }
        return result;
    }
}
