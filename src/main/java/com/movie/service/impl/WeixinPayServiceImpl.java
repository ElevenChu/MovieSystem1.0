package com.movie.service.impl;

import com.github.wxpay.sdk.WXPayUtil;
import com.movie.service.WeixinPayService;
import com.movie.utils.HttpClientUtils;
import com.movie.vo.WeiXin;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sun.net.www.http.HttpClient;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;


@Service
public class WeixinPayServiceImpl implements WeixinPayService {

    @Resource
    WeiXin weiXin;

    /**
     * 调用微信接口获取支付的地址
     *
     * @param out_trade_no 订单号
     * @param total_fee    金额(分)
     * @return
     */
    @Override
    public Map createNative(String out_trade_no, String total_fee) {
        // 1.创建参数
        Map<String, String> param = new HashMap();// 创建参数
        //调用微信支付统一下单的接口，必须转的参数
        param.put("appid", weiXin.getAppid());// 公众号
        param.put("mch_id", weiXin.getPartner());// 商户号
        param.put("nonce_str", WXPayUtil.generateNonceStr());// 随机字符串
        param.put("body", "XXX影城电影票");// 商品描述
        param.put("out_trade_no", out_trade_no);// 商户订单号
        param.put("total_fee", total_fee);// 总金额（分）
        param.put("spbill_create_ip", "127.0.0.1");// IP
        param.put("notify_url", "http://test.easyshop.cn");// 回调地址(随便写)
        param.put("trade_type", "NATIVE");// 交易类型
        try {
            // 2.生成要发送的xml(将Map集合转为XML格式)
            String xmlParam = WXPayUtil.generateSignedXml(param, weiXin.getPartnerkey());
            //使用HttpClient远程调用工具
            HttpClientUtils client = new HttpClientUtils("https://api.mch.weixin.qq.com/pay/unifiedorder");
            client.setHttps(true);
            client.setXmlParam(xmlParam);
            client.post();
            // 3.获得结果
            String result = client.getContent();
            System.out.println(result);
            //将服务器返回的结果转为Map
            Map<String, String> resultMap = WXPayUtil.xmlToMap(result);
            //封装返回值
            Map<String, String> map = new HashMap<>();
            map.put("code_url", resultMap.get("code_url"));// 支付地址
            map.put("total_fee", total_fee);// 总金额
            map.put("out_trade_no", out_trade_no);// 订单号
            return map;
        } catch (Exception e) {
            e.printStackTrace();
            return new HashMap<>();
        }
    }

    /**
     * 根据订单号查询订单的支付状态
     * @param out_trade_no
     * @return
     */
    @Override
    public Map queryPayStatus(String out_trade_no) {
        Map param = new HashMap();
        param.put("appid", weiXin.getAppid());// 公众账号ID
        param.put("mch_id", weiXin.getPartner());// 商户号
        param.put("out_trade_no", out_trade_no);// ----->订单号
        param.put("nonce_str", WXPayUtil.generateNonceStr());// 随机字符串
        //查询订单支付状态
        String url = "https://api.mch.weixin.qq.com/pay/orderquery";
        try {
            String xmlParam = WXPayUtil.generateSignedXml(param, weiXin.getPartnerkey());
            HttpClientUtils client = new HttpClientUtils(url);
            client.setHttps(true);
            client.setXmlParam(xmlParam);
            //发送POS请求
            client.post();

            //调用接口返回的结果
            String result = client.getContent();
            //把返回的XML文件格式转为Map
            Map<String, String> map = WXPayUtil.xmlToMap(result);
            System.out.println("---------------服务器端接口返回的支付结果:-----------------------");
            if(map!=null){
                Set<Map.Entry<String, String>> entries = map.entrySet();
                for (Map.Entry<String, String> entry : entries) {
                    System.out.println(entry.getKey()+"--->"+entry.getValue());
                }
            }
            return map;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
