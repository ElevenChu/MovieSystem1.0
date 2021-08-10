package com.movie.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * @BelongsProject: MovieSystem
 * @BelongsPackage: com.movie.vo
 * @CreateTime:
 * @Description: 封装微信支付的信息
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Component
public class WeiXin {

    @Value("${appid}")
    private String appid;

    @Value("${partner}")
    private String partner;

    @Value("${partnerkey}")
    private String partnerkey;

    @Value("${notifyurl}")
    private String notifyurl;
}
