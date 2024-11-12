package csu.web.petstore.web.servlet;

import javax.imageio.ImageIO;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;


public class CheckCodeServlet extends HttpServlet {
    private static final int WIDTH = 100;
    private static final int HEIGHT = 30;
    private static final int CODE_LENGTH = 4;
    private static final int LINE_COUNT = 4;
    private static final int NOISE_POINT_COUNT = 100;
    private static final String[] FONT_NAMES = {"宋体", "黑体", "微软雅黑", "楷体"};

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //创建一对象，在内存中代表图片（验证码图片对象）
        BufferedImage image = new BufferedImage(WIDTH, HEIGHT, BufferedImage.TYPE_INT_BGR);

        //美化图片
        //填充背景色
        Graphics g = image.getGraphics();//画笔对象
        g.setColor(Color.white);
        g.fillRect(0, 0, WIDTH, HEIGHT);
        //画边框
        g.setColor(Color.black);
        g.drawRect(0, 0, WIDTH - 1, HEIGHT - 1);

        String checkcode_session = generateCheckCode(g, ThreadLocalRandom.current(), FONT_NAMES);

        //将验证码存入session
        req.getSession().setAttribute("checkcode_session", checkcode_session);

        //画干扰线
        for (int i = 0; i < LINE_COUNT; i++) {
            g.setColor(new Color(ThreadLocalRandom.current().nextInt(256), ThreadLocalRandom.current().nextInt(256), ThreadLocalRandom.current().nextInt(256)));
            g.drawLine(ThreadLocalRandom.current().nextInt(WIDTH), ThreadLocalRandom.current().nextInt(HEIGHT), ThreadLocalRandom.current().nextInt(WIDTH), ThreadLocalRandom.current().nextInt(HEIGHT));
        }

        //使用画圆来加入噪点
        for (int i = 0; i < NOISE_POINT_COUNT; i++) {
            g.setColor(new Color(ThreadLocalRandom.current().nextInt(256), ThreadLocalRandom.current().nextInt(256), ThreadLocalRandom.current().nextInt(256)));
            g.drawOval(ThreadLocalRandom.current().nextInt(WIDTH), ThreadLocalRandom.current().nextInt(HEIGHT), 1, 1);
        }

        //将图片输出到页面展示
        ImageIO.write(image, "jpg", resp.getOutputStream());
    }

    private String generateCheckCode(Graphics g, Random ran, String[] fontNames) {
        StringBuilder str = new StringBuilder();
        for (int i = 1; i <= CODE_LENGTH; i++) {
            int charNum = ran.nextInt(27)+96;
            char a = (char) charNum;
            g.setFont(new Font(fontNames[ran.nextInt(fontNames.length)], Font.BOLD, ran.nextInt(20)+25));
            Color color = new Color(ran.nextInt(255), ran.nextInt(255), ran.nextInt(255), 255);
            g.setColor(color);
            str.append(a);
            g.drawString(a + "", (WIDTH/5 * i)-WIDTH/10, 3 * HEIGHT/4);
        }
        return str.toString();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doPost(req, resp);
    }
}

