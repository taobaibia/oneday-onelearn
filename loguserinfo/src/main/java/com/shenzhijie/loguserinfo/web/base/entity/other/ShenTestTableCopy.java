package com.shenzhijie.loguserinfo.web.base.entity.other;

import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * All rights Reserved, Designed By www.info4z.club
 * <p>title:com.shenzhijie.loguserinfo.web.base.entity</p>
 * <p>ClassName:ShenTestTable</p>
 * <p>Description:TODO(请用一句话描述这个类的作用)</p>
 * <p>Compony:Info4z</p>
 * author:zhijieShen
 * date:2021/7/28
 * version:1.0
 * 注意：本内容仅限于公司内部传阅，禁止外泄以及用于其他的商业目的
 */
@TableName(value = "shen_test_table_copy")
@Data
public class ShenTestTableCopy implements Serializable {
    /**
     * id 描述:主键id
     */
    @TableId(type = IdType.AUTO)
    private Long id;

    /**
     * board_id 描述:看板id
     */
    private Long boardId;

    /**
     * name 描述:看板名称
     */
    private String name;

    /**
     * title 描述:主题
     */
    private String title;

    /**
     * text 描述:内容
     */
    private String text;

    /**
     * type 描述:分类
     */
    private String type;

    /**
     * is_delete 描述:是否删除（Y：删除，N：不删除）
     */
    private String isDelete;

    /**
     * remark 描述:备注
     */
    private String remark;

    /**
     * remark1 描述:备用字段1
     */
    private String remark1;

    /**
     * remark2 描述:备用字段2
     */
    private String remark2;

    /**
     * remark3 描述:备用字段3
     */
    private String remark3;

    /**
     * remark4 描述:备用字段4
     */
    private String remark4;

    /**
     * remark5 描述:备用字段5
     */
    private String remark5;

    /**
     * create_time 描述:创建时间
     */
    @TableField(fill = FieldFill.INSERT)
    private Date createTime;

    /**
     * create_user 描述:创建人
     */
    private String createUser;

    /**
     * update_time 描述:更新时间
     */
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private Date updateTime;

    /**
     * update_user 描述:更新人
     */
    private String updateUser;

    private static final long serialVersionUID = 1L;
}
