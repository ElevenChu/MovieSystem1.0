package com.movie.controller;

import com.movie.bean.FilmCategory;
import com.movie.bean.Screenwriter;
import com.movie.service.ScreenwriterService;
import com.movie.utils.CommonResult;
import com.movie.utils.PageUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @BelongsProject: MovieSystem
 * @BelongsPackage: com.movie.web
 * @CreateTime: 2020-10-20 14:23
 * @Description: TODO
 */
@Controller
public class ScreenwriterController {

    @Autowired
    ScreenwriterService screenwriterService;

    /**
     * 04-更新电电影编剧
     */
    @ResponseBody
    @RequestMapping(value = "/screenwriter_update",method = RequestMethod.POST)
    public CommonResult update(Screenwriter screenwriter){
        System.out.println("要更新的对象是:"+screenwriter);
        CommonResult result;
        int count = screenwriterService.updateScreenwriter(screenwriter);
        if(count>0){
            result=new CommonResult(200,"电影编剧更新成功");
        }else{
            result=new CommonResult(500,"电影编剧更新失败");
        }
        return result;
    }

    /**
     * 根据ID查询
     * @param screenwriter_id
     * @return
     */
    @ResponseBody
    @RequestMapping("/screenwriter_toupdate")
    public Screenwriter screenwriter_toupdate(Integer screenwriter_id){
        return screenwriterService.findById(screenwriter_id);
    }

    /**
     * 删除
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/delete_screenwriter",method = RequestMethod.GET)
    public CommonResult delete_FilmCategory(Integer screenwriter_id){
        System.out.println("删除ID："+screenwriter_id);
        CommonResult result;
        int count = screenwriterService.deleteScreenwriter(screenwriter_id);
        if(count>0){
            result=new CommonResult(200,"电影编剧删除成功");
        }else{
            result=new CommonResult(500,"电影编剧删除失败");
        }
        return result;
    }

    /**
     * 02-新增电影编剧
     */
    @ResponseBody
    @RequestMapping(value = "/screenwriter_add",method = RequestMethod.POST)
    public CommonResult screenwriter_add(Screenwriter screenwriter){
        System.out.println("要新增的对象是:"+screenwriter);
        CommonResult result;
        int count = screenwriterService.addScreenwriter(screenwriter);
        if(count>0){
            result=new CommonResult(200,"电影编剧添加成功");
        }else{
            result=new CommonResult(500,"电影编剧添加失败");
        }
        return result;
    }

    /**
     * 01-数据分页
     * @return
     */
    @RequestMapping("/screenwriter_page")
    public String page(@RequestParam(name ="pageIndex",defaultValue = "1") Integer pageIndex,
                       @RequestParam(name ="pageSize",defaultValue = "5") Integer pageSize, Model model){
        //总数
        int totalCount = screenwriterService.totalCount();
        Map<String,Object> map=new HashMap<>();
        map.put("startPage",(pageIndex-1)*pageSize);
        map.put("pageSize",pageSize);
        //每页数据
        List<Screenwriter> screenwriters = screenwriterService.getScreenwriters(map);
        //封装一个分页工具类
        PageUtil<Screenwriter> pageUtil=new PageUtil<Screenwriter>(pageIndex,pageSize,totalCount,screenwriters);
        //存值
        model.addAttribute("pageUtil",pageUtil);
        return "admin_movie_screenwriters";
    }
}
