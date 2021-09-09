package com.shenzhijie.loguserinfo.web.base.entity.other;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;

/**
 * All rights Reserved, Designed By www.info4z.club
 * <p>title:com.shenzhijie.loguserinfo.web.base.entity</p>
 * <p>ClassName:ShenTestTable</p>
 * <p>Description:TODO(localhost-dongyimai库)</p>
 * <p>Compony:Info4z</p>
 * author:zhijieShen
 * date:2021/7/28
 * version:1.0
 * 注意：本内容仅限于公司内部传阅，禁止外泄以及用于其他的商业目的
 */
@TableName(value = "tb_item_cat")
@Data
public class tbItemCat implements Serializable {
    /**
     * id 描述:主键id
     */
    @TableId(type = IdType.AUTO)
    private Long id;

    /**
     * board_id 描述:看板parent_id
     */
    private Long parentId;

    /**
     * name 描述:看板名称
     */
    private String name;

    /**
     * title 描述:主题
     */
    private Long typeId;
}
