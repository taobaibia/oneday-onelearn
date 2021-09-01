package com.shenzhijie.loguserinfo.web.base.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import javax.validation.constraints.NotEmpty;
import java.io.Serializable;
import java.util.Date;

/**
 * <p>
 * 项目看板表 isip_project_board
 * isip库
 * </p>
 *
 * @author zhijieShen 2021-07-14 15:27:16 547
 */
@TableName(value = "isip_project_board")
@Data
public class IsipProjectBoard implements Serializable {
    /**
     * id 描述:主键id
     */
    @TableId(type = IdType.AUTO)
    private Long id;

    /**
     * project_id 描述:项目编号
     */
    @NotEmpty(message = "项目编号不能为空")
    private String projectId;

    /**
     * name 描述:看板名称
     */
    @NotEmpty(message = "看板名称不能为空")
    private String name;

    /**
     * scale 描述:项目进度
     */
    private Double scale;

    /**
     * status 描述:状态
     */
    private String status;

    /**
     * is_top 描述:是否删除（Y：删除，N：不删除）
     */
    private String isDelete;

    /**
     * is_top 描述:是否固定（Y：固定，N：不固定）
     */
    private String isTop;

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
    private Date createTime;

    /**
     * create_user 描述:创建人
     */
    private String createUser;

    /**
     * update_time 描述:更新时间
     */
    private Date updateTime;

    /**
     * update_user 描述:更新人
     */
    private String updateUser;

    private static final long serialVersionUID = 1L;
}