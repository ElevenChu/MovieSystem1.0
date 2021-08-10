package com.movie.service;

import java.util.Map;

public interface WeixinPayService {
    /**
     * 01-生成微信支付二维码
     * @param out_trade_no
     *            订单号
     * @param total_fee
     *            金额(分)
     * @return
     */
    public Map createNative(String out_trade_no, String total_fee);


    /**
     * 02-查询支付状态：根据订单号查询订单支付的状态
     * @param out_trade_no
     */
    public Map queryPayStatus(String out_trade_no);
}
