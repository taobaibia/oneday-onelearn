package com.shenzhijie.loguserinfo.web.advice;

import com.shenzhijie.loguserinfo.web.base.result.ResultWrapper;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

/**
 * All rights Reserved, Designed By www.info4z.club
 * <p>title:com.shenzhijie.loguserinfo.web.advice</p>
 * <p>ClassName:ValidateHandler</p>
 * <p>Description:TODO(统一返回数据格式)</p>
 * <p>Compony:Info4z</p>
 * author:zhijieShen
 * date:2021/6/29
 * version:1.0
 * 注意：本内容仅限于公司内部传阅，禁止外泄以及用于其他的商业目的
 */
@ControllerAdvice
public class ValidateHandler extends ResponseEntityExceptionHandler {

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
                                                                  HttpHeaders headers,
                                                                  HttpStatus status,
                                                                  WebRequest request) {
        StringBuilder stringBuilder = new StringBuilder();
        ex.getBindingResult().getFieldErrors();
        for (FieldError fieldError : ex.getBindingResult().getFieldErrors()) {
            String defaultMessage = fieldError.getDefaultMessage();
            stringBuilder.append("      " + defaultMessage);
            break;
        }
        return new ResponseEntity(ResultWrapper.builder().code(102).msg(stringBuilder.toString()).build(),
                HttpStatus.OK);
    }
}
