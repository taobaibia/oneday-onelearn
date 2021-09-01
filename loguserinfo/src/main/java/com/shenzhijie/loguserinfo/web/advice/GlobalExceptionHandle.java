package com.shenzhijie.loguserinfo.web.advice;

import com.baomidou.kaptcha.exception.KaptchaException;
import com.baomidou.kaptcha.exception.KaptchaIncorrectException;
import com.baomidou.kaptcha.exception.KaptchaNotFoundException;
import com.baomidou.kaptcha.exception.KaptchaTimeoutException;
import com.shenzhijie.loguserinfo.web.base.result.ResultWrapper;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.security.auth.login.LoginException;

/**
 * All rights Reserved, Designed By www.info4z.club
 * <p>title:com.shenzhijie.loguserinfo.web.advice</p>
 * <p>ClassName:GlobalExceptionHandle</p>
 * <p>Description:TODO(统一异常处理)</p>
 * <p>Compony:Info4z</p>
 * author:zhijieShen
 * date:2021/6/29
 * version:1.0
 * 注意：本内容仅限于公司内部传阅，禁止外泄以及用于其他的商业目的
 */
@RestControllerAdvice
public class GlobalExceptionHandle {

    @ExceptionHandler(ArithmeticException.class)
    public ResultWrapper customException() {
        return ResultWrapper.builder().code(301).msg("计算异常").build();
    }

    /**
     * 自定义 token异常处理
     */
    @ExceptionHandler(LoginException.class)
    public ResultWrapper LoginException(Exception e) {
        return ResultWrapper.getErrorBuilder().code(501).msg(e.getMessage()).build();
    }

    /**
     * 自定义验证码返回异常
     * kcaptcha
     */
    @ExceptionHandler(KaptchaException.class)
    public String kcaptchaException(KaptchaException e) {
        if (e instanceof KaptchaTimeoutException) {
            return "超时";
        } else if (e instanceof KaptchaIncorrectException) {
            return "不正确";
        } else if (e instanceof KaptchaNotFoundException) {
            return "没找到";
        } else {
            return "反正错了";
        }
    }
}
