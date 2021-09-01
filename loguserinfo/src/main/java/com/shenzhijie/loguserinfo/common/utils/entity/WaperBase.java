package com.shenzhijie.loguserinfo.common.utils.entity;

import java.io.Serializable;

/**
 * /**
 *
 * @description: 返回工具类
 * @author: Jay
 * @create: 2021-08-30 13:03
 **/
public class WaperBase<T> implements Serializable {

    private static final long serialVersionUID = -3697393669249699653L;

    private int code = 0;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public WaperBase() {
    }

    public WaperBase(int code, String message, T data) {
        this.code = code;
        this.message = message;
        this.data = data;
    }

    private String message = "success";

    private T data;

    public WaperBase<T> error(int code) {
        this.code = code;
        this.message = message;
        return this;
    }

    public Object ok(T t) {
        return t;
    }
}
