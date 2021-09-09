package com.shenzhijie.loguserinfo.web.srevice;

import com.baomidou.mybatisplus.extension.service.IService;
import com.shenzhijie.loguserinfo.web.base.entity.other.ShenTestTable;
import com.shenzhijie.loguserinfo.web.base.entity.other.ShenTestTableCopy;

import java.util.List;

/**
 * All rights Reserved, Designed By www.info4z.club
 * <p>title:com.shenzhijie.loguserinfo.web.srevice</p>
 * <p>ClassName:ShenzhijieService</p>
 * <p>Description:TODO(请用一句话描述这个类的作用)</p>
 * <p>Compony:Info4z</p>
 * author:zhijieShen
 * date:2021/7/29
 * version:1.0
 * 注意：本内容仅限于公司内部传阅，禁止外泄以及用于其他的商业目的
 */
public interface ShenzhijieService extends IService<ShenTestTable> {
    ShenTestTable saveShenzhijie(ShenTestTable shenTestTable);

    /**
     * 规则查询
     */
    List<ShenTestTable> findShenzhijieRules(String name);

    /**
     * 赋值属性,可以去除
     */
    ShenTestTableCopy copyResult();


}
