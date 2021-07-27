package com.movie.utils;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CommonResult {
    private Integer code;//状态码200 成功 ；404找不到
    private String msg;//消息内容
    private Object object;//响应体

    public CommonResult(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }
}
