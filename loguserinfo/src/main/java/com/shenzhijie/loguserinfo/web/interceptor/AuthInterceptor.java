package com.shenzhijie.loguserinfo.web.interceptor;

import com.shenzhijie.loguserinfo.web.advice.TokenException;
import com.shenzhijie.loguserinfo.web.config.annotations.TokenCheck;
import com.shenzhijie.loguserinfo.common.utils.JwtUtils;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.lang.reflect.Method;

/**
 * All rights Reserved, Designed By www.info4z.club
 * <p>title:com.shenzhijie.loguserinfo.web.interceptor</p>
 * <p>ClassName:AuthInterceptor</p>
 * <p>Description:TODO(请用一句话描述这个类的作用)</p>
 * <p>Compony:Info4z</p>
 * author:zhijieShen
 * date:2021/7/1
 * version:1.0
 * 注意：本内容仅限于公司内部传阅，禁止外泄以及用于其他的商业目的
 */
@Slf4j
public class AuthInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        if (handler instanceof HandlerMethod) {
            log.info("拦截器进入");
            String token = request.getHeader("token");
            if (StringUtils.isBlank(token)) {
//                throw new TokenException("token 为空");
                log.info("token 为空");
            }
            HandlerMethod handlerMethod = (HandlerMethod) handler;
            Method method = handlerMethod.getMethod();

            if (method.isAnnotationPresent(TokenCheck.class)) {
                TokenCheck annotation = method.getAnnotation(TokenCheck.class);
                if (annotation.required()) {
                    // 校验token
                    try {
                        JwtUtils.parseToken(token);
                        return true;
                    } catch (Exception e) {
                        throw new TokenException("解析token 异常");
                    }

                }
            }

        }
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {

    }
}
