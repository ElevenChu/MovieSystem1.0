package com.movie.controller;

import com.movie.bean.FilmCategory;
import com.movie.bean.Performer;
import com.movie.bean.Screenwriter;
import com.movie.service.FilmCategoryService;
import com.movie.service.FilmVoService;
import com.movie.service.PerformerService;
import com.movie.service.ScreenwriterService;
import com.movie.utils.CommonResult;
import com.movie.utils.PageUtil;
import com.movie.vo.FilmVo;
import com.sun.xml.internal.stream.buffer.sax.SAXBufferCreator;
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
public class FilmVoController {

    @Autowired
    FilmVoService filmVoService;

    @Autowired
    FilmCategoryService filmCategoryService;
    @Autowired
    PerformerService performerService;
    @Autowired
    ScreenwriterService screenwriterService;

    /**
     * 01-数据分页
     * @return
     */
    @RequestMapping("/filmvo_page")
    public String page(@RequestParam(name ="pageIndex",defaultValue = "1") Integer pageIndex,
                       @RequestParam(name ="pageSize",defaultValue = "5") Integer pageSize, Model model){
        //总数
        int totalCount = filmVoService.totalcount();
        Map<String,Object> map=new HashMap<>();
        map.put("pageStart",(pageIndex-1)*pageSize);
        map.put("pageSize",pageSize);
        //每页数据
        List<FilmVo> filmCategorys = filmVoService.findFilmsVo(map);
        //封装一个分页工具类
        PageUtil<FilmVo> pageUtil=new PageUtil<FilmVo>(pageIndex,pageSize,totalCount,filmCategorys);
        //存值
        model.addAttribute("pageUtil",pageUtil);
        return "admin_film_list";
    }


    /**
     * 下拉框数据1
     * @return
     */
    @ResponseBody
    @RequestMapping("/getFilmCategorys")
    public List<FilmCategory> getFilmCategorys(){
        return filmCategoryService.getAllFilmCategorys();
    }

    @ResponseBody
    @RequestMapping("/film_add")
    public CommonResult add(FilmVo filmVo){
        CommonResult result;
        try {
            //要新增的对象是：FilmVo(film_id=null, film_name=1111, poster_url=2917bb6e-3476-4441-9c9a-ead70e1faf3e.jpg, category_id=1, film_category=null, director=2222, screenwriter_id=1, screenwriter_name=null, performer_id=1, performer_name=null, film_time=2020-10-20, description=4444, play_time=3333, is_delete=null)
            System.out.println("要新增的对象是："+filmVo);
            int count = filmVoService.addFilmVo(filmVo);
            if(count==4){
                result=new CommonResult(200,"电影添加成功");
            }else{
                result=new CommonResult(500,"电影添加失败");
            }
        } catch (Exception e) {
            result=new CommonResult(500,"电影添加失败");
            e.printStackTrace();
        }
        return result;
    }
    @ResponseBody
    @RequestMapping("/getPerformers")
    public List<Performer> getPerformers(){
        return performerService.findPerformers();
    }

    @ResponseBody
    @RequestMapping("/getScreenwriter")
    public List<Screenwriter> getScreenwriter(){
        return screenwriterService.findScreenwriter();
    }

    /**
     * 根据ID查询电影详情数据
     * @param film_id
     * @return
     */
    @RequestMapping("/film_findFilmVoById")
    public String  findFilmVoById(int film_id,Model model){
        FilmVo filmVo = filmVoService.findFilmVoById(film_id);
        model.addAttribute("filmVo",filmVo);
        return "user_film_detail";
    }

    @ResponseBody
    @RequestMapping("/film_changeState")
    public CommonResult film_changeState(Integer film_id,Integer is_delete){
    CommonResult result;
        int count = filmVoService.updateState(film_id, is_delete);
        if (count>0){
            result=new CommonResult(200,"操作成功");

        }else {
            result=new CommonResult(500,"操作失败");
        }
        return result;
    }
}
