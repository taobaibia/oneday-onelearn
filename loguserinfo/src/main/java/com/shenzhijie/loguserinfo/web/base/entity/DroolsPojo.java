package com.shenzhijie.loguserinfo.web.base.entity;

import lombok.Data;

import java.util.List;

/**
 * @description: drools规则实体类
 * @author: Jay
 * @create: 2021-09-03 15:30
 **/
@Data
public class DroolsPojo {
    private Long id;
    private String name;
    private List<String> list;
}
