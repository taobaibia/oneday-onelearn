package com.shenzhijie.loguserinfo.web.base.entity.rxJava;

import lombok.Builder;
import lombok.Data;
import lombok.experimental.Tolerate;

/**
 * @description: 响应式实体类
 * @author: Jay
 * @create: 2021-09-09 15:27
 **/
@Data
@Builder
public class Dog {
    @Tolerate
    public Dog() {
    }

    private Integer id;
    private String name;
    private String color;
    private Integer age;
}
