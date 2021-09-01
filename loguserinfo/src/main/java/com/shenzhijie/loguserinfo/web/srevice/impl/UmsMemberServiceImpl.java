package com.shenzhijie.loguserinfo.web.srevice.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.shenzhijie.loguserinfo.web.base.entity.UmsMember;
import com.shenzhijie.loguserinfo.web.base.result.ResultWrapper;
import com.shenzhijie.loguserinfo.web.mapper.UmsMemberMapper;
import com.shenzhijie.loguserinfo.web.srevice.UmsMemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * All rights Reserved, Designed By www.info4z.club
 * <p>title:com.shenzhijie.loguserinfo.web.srevice.impl</p>
 * <p>ClassName:UmsMemberServiceImpl</p>
 * <p>Description:TODO(请用一句话描述这个类的作用)</p>
 * <p>Compony:Info4z</p>
 * author:zhijieShen
 * date:2021/7/1
 * version:1.0
 * 注意：本内容仅限于公司内部传阅，禁止外泄以及用于其他的商业目的
 */
@Service
public class UmsMemberServiceImpl extends ServiceImpl<UmsMemberMapper, UmsMember> implements UmsMemberService {

    @Autowired
    private UmsMemberMapper umsMemberMapper;

    @Override
    public ResultWrapper edit(UmsMember umsMember) {
        int i = umsMemberMapper.updateById(umsMember);
        return ResultWrapper.getSuccessBuilder().data(i).build();
    }
}
