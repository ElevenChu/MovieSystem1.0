package com.movie.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.Date;
import java.util.Random;

/**
 * @BelongsProject: MovieSystem
 * @BelongsPackage: com.movie.web
 * @CreateTime: 2020-10-14 17:28
 * @Description: TODO
 */
@Controller
public class CodeController {

    private static final char[] CH = "ABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890".toCharArray();
    private static final int IMAGE_WIDTH = 73;
    private static final int IMAGE_HEIGHT = 28;
    private static final int LINE_NUM = 30;
    private static final int RANDOM_NUM = 4;
    Random random = new Random();

    /**
     * 生成随机验证码
     */
    @RequestMapping("/admin_createImage")
    public void createImage(HttpServletResponse response, HttpServletRequest request) throws Exception{

        response.setContentType("image/jpg");//设置相应类型,告诉浏览器输出的内容为图片
        response.setHeader("Pragma", "No-cache");//设置响应头信息，告诉浏览器不要缓存此内容
        response.setHeader("Cache-Control", "no-cache");
        response.setDateHeader("Expire", new Date().getTime());

        BufferedImage bi = new BufferedImage(IMAGE_WIDTH, IMAGE_HEIGHT, BufferedImage.TYPE_INT_BGR);
        Graphics g = bi.getGraphics();
        g.fillRect(0, 0, IMAGE_WIDTH, IMAGE_HEIGHT);
        g.setColor(getRandomColor(110, 133));
        g.setFont(new Font("Times New Roman", Font.ROMAN_BASELINE, 18));
        // 绘制干扰线
        for (int i = 1; i <= LINE_NUM; i++) {
            int x = random.nextInt(IMAGE_WIDTH);
            int y = random.nextInt(IMAGE_HEIGHT);
            int xl = random.nextInt(13);
            int yl = random.nextInt(15);
            g.drawLine(x, y, x + xl, y + yl);
        }

        // 绘制随机字符
        StringBuilder sb = new StringBuilder();
        String str = null;
        for (int i = 0; i < RANDOM_NUM; i++) {
            g.setFont(new Font("Fixedsys", Font.CENTER_BASELINE, 18));
            g.setColor(new Color(random.nextInt(101), random.nextInt(111), random.nextInt(121)));
            str = CH[random.nextInt(CH.length)] + "";
            g.drawString(str, 13 * i, 16);
            g.translate(random.nextInt(3), random.nextInt(3));
            sb.append(str);
        }
        g.dispose();
        //存贮Session
        System.out.println("系统验证码："+sb.toString());
        request.getSession().setAttribute("safeCode", sb.toString());
        ImageIO.write(bi, "JPG", response.getOutputStream());
    }

    /**
     * 获得颜色
     */
    private Color getRandomColor(int fc, int bc) {
        if (fc > 255)
            fc = 255;
        if (bc > 255)
            bc = 255;
        int r = fc + random.nextInt(bc - fc - 16);
        int g = fc + random.nextInt(bc - fc - 14);
        int b = fc + random.nextInt(bc - fc - 18);
        return new Color(r, g, b);
    }
}
