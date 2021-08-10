package com.movie.web;

import com.movie.bean.Users;
import com.movie.service.BuyTicketService;
import com.movie.service.TicketService;
import com.movie.service.WeixinPayService;
import com.movie.utils.CommonResult;
import com.movie.utils.IdWorker;
import com.movie.vo.PlayVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Map;

/**
 * @BelongsProject: MovieSystem
 * @BelongsPackage: com.movie.web
 * @CreateTime: 2020-10-22 17:37
 * @Description: TODO
 */
@Controller
public class BuyTicketController {

    @Autowired
    BuyTicketService buyTicketService;

    @Autowired
    WeixinPayService weixinPayService;

    @Autowired
    TicketService ticketService;

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

    /**
     * 跳转支付页面,生成二维码
     *
     * @param play_id
     * @param seats
     * @return
     */
    @RequestMapping(value = "/buy_toPay", method = RequestMethod.POST)
    public String toPay(Integer play_id, String seats, Model model, HttpSession session) {
        //要购买的电影的座位号
        session.setAttribute("seats",seats);
        System.out.println("支付的play_id：" + play_id);
        System.out.println("走买的座位是：" + seats);
        PlayVo vo = buyTicketService.findPlayVoById(play_id);
        //一张票的价格
        Double money = vo.getMoney();
        StringBuffer stringBuffer = new StringBuffer();
        String[] arrays = seats.split(",");
        //待支付金额
        Double sumMoney = money * arrays.length;
        for (String s : arrays) {
            s = s.replace("-", "排") + "座";
            stringBuffer.append(s + " ");
        }
        model.addAttribute("seats", stringBuffer.toString());
        model.addAttribute("vo", vo);
        model.addAttribute("sumMoney", sumMoney);

        //2.生成一个永远不会重复的订单编号
        IdWorker worker = new IdWorker();
        long id = worker.nextId();
        String orderId = id+"";

        System.out.println("生成的唯一的订单号ID是:" + orderId);
        System.out.println("调用微信接口，获取二维码支付地址");

        //调用微信接口，获取二维码支付地址
        Map<String, String> map = weixinPayService.createNative(orderId, (int)(sumMoney*100)+""); //微信接口支付金额默认是以分为单位
        //微信要支付的地址，把这个地址生成二维码
        String code_url = map.get("code_url");
        model.addAttribute("code_url", code_url);
        //用户支付的订单号
        model.addAttribute("orderId", orderId);
        return "user_film_pay";
    }

    /**
     * 根据订单号查询订单支付的状态--轮询订单支付状态接口
     *
     * @param out_trade_no
     * @return
     */
    @ResponseBody
    @RequestMapping("/pay_queryPayStatus")
    public CommonResult queryPayStatus(String out_trade_no,Integer play_id,HttpSession session) {
        CommonResult commonResult = null;
        try {
            //查询支付的订单号是:1320615983324332000
            System.out.println("查询支付的订单号是:"+out_trade_no);
            System.out.println("查询支付的订单play_id:"+play_id);
            String seats=(String)session.getAttribute("seats");// 3-4,5-6,7-8
            Users loginUser=(Users) session.getAttribute("loginUser");
            commonResult = null;
            int x = 0;
            while (true) {
                //查询订单支付状态
                Map map = weixinPayService.queryPayStatus(out_trade_no);
                if (map != null) {
                    if (map.get("trade_state").equals("SUCCESS")) {// 如果成功
                        //支付成功,把支付成功的数据，存入数据中，后期可以查看订单
                        String[] seatNumers = seats.split(",");
                        for (String seatName : seatNumers) {
                            int seat_id = ticketService.getSeatIdBySeatName(seatName);
                            //插入到订单表 ticket表中
                            ticketService.addTicket(out_trade_no,play_id,loginUser.getUser_id(),seat_id);
                        }
                        commonResult = new CommonResult(200, "支付成功");
                        break;
                    }
                }
                if (map == null) {
                    commonResult = new CommonResult(500, "支付出现异常！");
                    break;
                }
                try {
                    Thread.sleep(3000);//睡眠3秒钟
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                //为了不让循环无休止地运行，我们定义一个循环变量，如果这个变量超过了这个值则退出循环，设置时间为5分钟
                x++;
                if (x >= 60) {
                    commonResult = new CommonResult(502, "支付超时！");
                    break;
                }
            }
        } catch (Exception e) {
            commonResult = new CommonResult(500, "支付异常！");
            e.printStackTrace();
        } finally {
        }
        return commonResult;
    }

}
