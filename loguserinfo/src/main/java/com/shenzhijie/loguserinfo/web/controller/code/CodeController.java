package com.shenzhijie.loguserinfo.web.controller.code;

import com.baomidou.kaptcha.Kaptcha;
import com.ramostear.captcha.HappyCaptcha;
import com.ramostear.captcha.support.CaptchaStyle;
import com.ramostear.captcha.support.CaptchaType;
import com.shenzhijie.loguserinfo.web.config.annotations.TokenCheck;
import com.wf.captcha.ArithmeticCaptcha;
import com.wf.captcha.SpecCaptcha;
import com.wf.captcha.utils.CaptchaUtil;
import io.swagger.annotations.Api;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * All rights Reserved, Designed By www.info4z.club
 * <p>title:com.shenzhijie.loguserinfo.web.controller</p>
 * <p>ClassName:UtilsCodeController</p>
 * <p>Description:TODO(最简单的验证码工具类happy-Captcha)</p>
 * <p>Compony:Info4z</p>
 * author:zhijieShen
 * date:2021/7/7
 * version:1.0
 * 注意：本内容仅限于公司内部传阅，禁止外泄以及用于其他的商业目的
 */
@RestController
@Api(tags = "全自动生成校验验证码")
@RequestMapping("/check-code")
@SuppressWarnings({"dep-ann", "unused"})
public class CodeController {

    //    redis
    @Autowired
    private StringRedisTemplate stringRedisTemplate;
    //    baomidou Keycaptcha验证码
    @Autowired
    private Kaptcha kaptcha;

    @Operation(summary = "redis多个设备验证码")
    @GetMapping("/generatorCodeRedis")
    @TokenCheck(required = false)
    public void generatorCodeRedis(HttpServletRequest request, HttpServletResponse response) {
        try {
            SpecCaptcha specCaptcha = new SpecCaptcha(100, 50);
            String text = specCaptcha.text();
            System.out.println("验证码:" + text);
            /**
             * 存入redis,键值对
             * */
            stringRedisTemplate.opsForValue().set("c", text);

            CaptchaUtil.out(specCaptcha, request, response);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @GetMapping("verify-Redis")
    @Operation(summary = "redis校验验证码")
    public String verifyRedis(String verifyCode, HttpServletRequest request) {
        /**
         * 取出redis,键
         * */
        String c = stringRedisTemplate.opsForValue().get("c");

        if (verifyCode.equals(c)) {
            /**
             * 校验之后移除
             * */
            HappyCaptcha.remove(request);
            return "校验通过";
        }
        return "校验不通过";
    }

    @Operation(summary = "happyCaptcha生成验证码")
    @GetMapping("/generatorCode-happyCaptcha")
    @TokenCheck(required = false)
    public void generatorCodeHappyCaptcha(HttpServletRequest request, HttpServletResponse response) {
        HappyCaptcha.require(request, response)
//                动态验证码
                .style(CaptchaStyle.ANIM)
//                数字算法
                .type(CaptchaType.ARITHMETIC_ZH)
                .build().finish();
    }

    @GetMapping("verify-HappyCaptcha")
    @Operation(summary = "happyCaptcha校验验证码")
    public String verifyHappyCaptcha(String verifyCode, HttpServletRequest request) {

        Boolean aBoolean = HappyCaptcha.verification(request, verifyCode, true);
        if (aBoolean) {
            /**
             * 校验之后移除
             * */
            HappyCaptcha.remove(request);
            return "校验通过";
        }
        return "校验不通过";
    }

    @Operation(summary = "easyCaptcha生成验证码")
    @GetMapping("/generatorCode-easyCaptcha")
    @TokenCheck(required = false)
    public void generatorCodeEasyCaptcha(HttpServletRequest request, HttpServletResponse response) {
        try {
            /**基础*/
//            CaptchaUtil.out(request, response);
            /**算数* */
            ArithmeticCaptcha arithmeticCaptcha = new ArithmeticCaptcha(200, 50);
            /**几个数运算*/
            arithmeticCaptcha.setLen(3);
            /**得数结果*/
            String text = arithmeticCaptcha.text();

            CaptchaUtil.out(arithmeticCaptcha, request, response);
            /**中文*/
//            ChineseCaptcha chineseCaptcha = new ChineseCaptcha(200, 50);
//            CaptchaUtil.out(chineseCaptcha,request,response);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @GetMapping("verify-EasyCaptcha")
    @Operation(summary = "EasyCaptcha校验验证码")
    public String verifyEasyCaptcha(String verifyCode, HttpServletRequest request) {

        Boolean aBoolean = CaptchaUtil.ver(verifyCode, request);
        if (aBoolean) {
            /**
             * 校验之后移除
             * */
            CaptchaUtil.clear(request);
            return "校验通过";
        }
        return "校验不通过";
    }

    @Operation(summary = "keyCaptcha生成验证码")
    @GetMapping("/generatorCode-keyCaptcha")
    public void generatorCodeKeyCaptcha(HttpServletRequest request, HttpServletResponse response) {
        kaptcha.render();

    }

    @Operation(summary = "KeyCaptcha验证码校验")
    @GetMapping("/verify-KeyCaptcha")
    public String verifyKeyCaptcha(String verifyCode, HttpServletRequest request) {
        Boolean aBoolean = kaptcha.validate(verifyCode, 10);
        if (aBoolean) {
            return "通过";
        }
        return "不通过";
    }
}
