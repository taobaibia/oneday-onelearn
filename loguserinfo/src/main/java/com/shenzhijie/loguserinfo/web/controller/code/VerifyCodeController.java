package com.shenzhijie.loguserinfo.web.controller.code;

import com.shenzhijie.loguserinfo.web.config.annotations.TokenCheck;
import com.shenzhijie.loguserinfo.common.utils.JCaptchaUtil;
import com.shenzhijie.loguserinfo.common.utils.code.ImageCode;
import com.sun.image.codec.jpeg.JPEGCodec;
import com.sun.image.codec.jpeg.JPEGImageEncoder;
import io.swagger.annotations.Api;
import io.swagger.v3.oas.annotations.Operation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Base64;

/**
 * All rights Reserved, Designed By www.info4z.club
 * <p>title:com.shenzhijie.loguserinfo.web.controller</p>
 * <p>ClassName:VerifyCodeController</p>
 * <p>Description:TODO(请用一句话描述这个类的作用)</p>
 * <p>Compony:Info4z</p>
 * author:zhijieShen
 * date:2021/7/6
 * version:1.0
 * 注意：本内容仅限于公司内部传阅，禁止外泄以及用于其他的商业目的
 */
@Slf4j
@RestController
@Api(tags = "验证码接口")
@RequestMapping("/code")
public class VerifyCodeController {

    String attrName = "verifyCode";

    @GetMapping("/generator")
    @Operation(summary = "生成验证码")
    @TokenCheck(required = false)
    public void generatorCode(HttpServletRequest request, HttpServletResponse response) {
        try {
            ImageCode imageCode = ImageCode.getInstance();
//            验证码的值
            String code = imageCode.getCode();
            request.getSession().setAttribute(attrName, code);
//            验证码图片
            ByteArrayInputStream image = imageCode.getImage();
            response.setContentType("image/jpeg");
            byte[] bytes = new byte[1024];
            try {
                ServletOutputStream outputStream = response.getOutputStream();
                while (image.read(bytes) != -1) {
                    outputStream.write(bytes);
                }
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
            }
        } catch (IOException e) {
            log.info("验证码异常");
            e.printStackTrace();
        }
    }

    @GetMapping("/generator-base64")
    @Operation(summary = "生成base64验证码")
    @TokenCheck(required = false)
    public String generatorCodeBase64(HttpServletRequest request, HttpServletResponse response) {
        try {
            ImageCode imageCode = ImageCode.getInstance();
//            验证码的值
            String code = imageCode.getCode();
            request.getSession().setAttribute(attrName, code);
//            验证码图片
            ByteArrayInputStream image = imageCode.getImage();
            request.getSession().setAttribute(attrName, code);

            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            byte[] buff = new byte[1024];
            int r = 0;
            while ((r = image.read(buff, 0, 1024)) > 0) {
                byteArrayOutputStream.write(buff, 0, r);
            }
            /**
             * 图片转换为base64位字符串
             * */
            byte[] data = byteArrayOutputStream.toByteArray();
            return Base64.getEncoder().encodeToString(data);


        } catch (IOException e) {
            log.info("验证码异常");
//            e.printStackTrace();
            return "";
        }
    }

    @GetMapping("/JCaptcha")
    @Operation(summary = "JCaptcha工具生成验证码")
    @TokenCheck(required = false)
    public String JCaptcha(HttpServletRequest request, HttpServletResponse response) {
        try {
//        SessionId用于辨识验证码的唯一性
            String id = request.getSession().getId();
            BufferedImage bufferedImage = JCaptchaUtil.imageCaptchaService().getImageChallengeForID(id);
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            JPEGImageEncoder jpegEncoder = JPEGCodec.createJPEGEncoder(byteArrayOutputStream);
            jpegEncoder.encode(bufferedImage);

            Object attribute = request.getSession().getAttribute(attrName);
            System.out.println(attribute);

            response.setHeader("Cache-Control", "no-store");
            response.setContentType("image/jpeg");
            byte[] bytes = byteArrayOutputStream.toByteArray();
            ServletOutputStream servletOutputStream = response.getOutputStream();
            servletOutputStream.write(bytes);
            servletOutputStream.flush();
            servletOutputStream.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    @GetMapping("/verify-JCaptcha")
    @TokenCheck(required = false)
    @Operation(summary = "工具校验验证码")
    public String verifyJCaptchaCode(String verify, HttpServletRequest request) {
        Boolean aBoolean = JCaptchaUtil.imageCaptchaService().validateResponseForID(request.getSession().getId(), verify);
        if (aBoolean) {
            return "验证码校验通过";
        }
        return "验证码校验不通过";
    }

    @GetMapping("/verify")
    @TokenCheck(required = false)
    @Operation(summary = "校验验证码")
    public String verifyCode(String verify, HttpServletRequest request) {

        String s = request.getSession().getAttribute(attrName).toString();
        if (verify.equals(s)) {
            return "验证码校验通过";
        }
        return "验证码校验不通过";
    }
}
