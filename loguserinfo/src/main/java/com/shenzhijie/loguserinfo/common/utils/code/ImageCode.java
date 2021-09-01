package com.shenzhijie.loguserinfo.common.utils.code;

import com.sun.xml.internal.messaging.saaj.util.ByteOutputStream;
import lombok.Data;

import javax.imageio.ImageIO;
import javax.imageio.stream.ImageOutputStream;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.Random;

/**
 * All rights Reserved, Designed By www.info4z.club
 * <p>title:com.shenzhijie.loguserinfo.common.utils.code</p>
 * <p>ClassName:ImageCode</p>
 * <p>Description:TODO(自定义生成图片验证码)</p>
 * <p>Compony:Info4z</p>
 * author:zhijieShen
 * date:2021/7/6
 * version:1.0
 * 注意：本内容仅限于公司内部传阅，禁止外泄以及用于其他的商业目的
 */
@Data
public class ImageCode {
    /**
     * 图形中的内容
     */
    private String code;
    /**
     * 图片
     */
    private ByteArrayInputStream image;
    /**
     * 图片的宽高
     */
    private int width = 400;

    private int height = 100;

    public ImageCode(String code, ByteArrayInputStream image, int width, int height) {
        this.code = code;
        this.image = image;
        this.width = width;
        this.height = height;
    }

    //    单例未写完 下课后补
    public static ImageCode getInstance() throws IOException {

        return new ImageCode();
    }

    public ImageCode() throws IOException {
//        图形缓冲区,给个画板
        BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
//        再给一支笔
        Graphics graphics = image.getGraphics();
//        拿笔 涂色,画图形
        graphics.setColor(new Color(255, 255, 255));
//        画矩形 大小
        graphics.fillRect(0, 0, width, height);
//        字体
        graphics.setFont(new Font("黑体", Font.PLAIN, 30));
//        随机数6位
        Random random = new Random();
        code = "";
        int num = 6;
        for (int i = 0; i < num; i++) {
            String s = String.valueOf(random.nextInt(10));
            code += s;

            graphics.setColor(new Color(0x000000));
            graphics.drawString(s, (width / 6) * i, 40);

//        划线
            graphics.setColor(new Color(0x7B7B7A));
            graphics.drawLine((width / 6) * i, 40, (width / 6) * i + 25, 40 - 20);
        }
        graphics.setColor(new Color(0x7B7B7A));
        for (int i = 0; i < 100; i++) {
            int x = random.nextInt(width);
            int y = random.nextInt(height);
            int x1 = random.nextInt(20);
            int y1 = random.nextInt(20);
            graphics.drawLine(x, y, x + x1, y + y1);
        }

//        收笔
        graphics.dispose();
        /**
         * 图片输出,创建一个文件的输出流
         * 文件的输出流->转到一个字节的输出流到 controller
         * 赋值给byteArrayInputStream
         * */
        ByteArrayInputStream inputStream = null;
        ByteOutputStream outputStream = new ByteOutputStream();


        ImageOutputStream imageOutputStream = null;
        try {
            //赋值给byteArrayInputStream
            imageOutputStream = ImageIO.createImageOutputStream(outputStream);
            ImageIO.write(image, "jpeg", imageOutputStream);

            inputStream = new ByteArrayInputStream(outputStream.toByteArray());
            //这个地方原来没有赋值.
            this.image = inputStream;
        } catch (IOException e) {
            System.out.println("生成验证码失败");
            e.printStackTrace();
        }

    }
}
