package com.movie.controller;

import com.movie.bean.Introduce;
import com.movie.service.IntroduceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
public class IntroduceController {


@Autowired
IntroduceService introduceService;


    /**
     * 查询电影院介绍
     * @param model
     * @return
     */
    @RequestMapping("/cinema_Introduce")
    public String findIntroduce(Model model){
        List<Introduce> introduces = introduceService.findIntroduce();
        Introduce introduce = introduces.get(0);
        model.addAttribute("introduce",introduce);
        return "user_cinema_introduce";
    }
}
