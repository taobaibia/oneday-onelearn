package com.shenzhijie.loguserinfo.web.srevice;

import com.baomidou.mybatisplus.extension.service.IService;
import com.shenzhijie.loguserinfo.web.base.entity.UmsMember;
import com.shenzhijie.loguserinfo.web.base.result.ResultWrapper;

/**
 * All rights Reserved, Designed By www.info4z.club
 * <p>title:com.shenzhijie.loguserinfo.web.srevice</p>
 * <p>ClassName:UmsMemberService</p>
 * <p>Description:TODO(请用一句话描述这个类的作用)</p>
 * <p>Compony:Info4z</p>
 * author:zhijieShen
 * date:2021/7/1
 * version:1.0
 * 注意：本内容仅限于公司内部传阅，禁止外泄以及用于其他的商业目的
 */
public interface UmsMemberService extends IService<UmsMember> {
    ResultWrapper edit(UmsMember umsMember);
}
