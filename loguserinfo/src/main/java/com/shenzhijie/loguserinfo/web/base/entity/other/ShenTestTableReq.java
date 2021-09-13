package com.shenzhijie.loguserinfo.web.base.entity.other;

import lombok.Data;

import java.io.Serializable;

@Data
public class ShenTestTableReq implements Serializable {
    private Long boardId;

    private String name;

    private static final long serialVersionUID = 1L;
}
