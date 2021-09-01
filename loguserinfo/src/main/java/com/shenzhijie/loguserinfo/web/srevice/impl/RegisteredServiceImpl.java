package com.shenzhijie.loguserinfo.web.srevice.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.shenzhijie.loguserinfo.web.base.entity.LoginINFO;
import com.shenzhijie.loguserinfo.web.base.entity.Registered;
import com.shenzhijie.loguserinfo.web.base.result.ResultWrapper;
import com.shenzhijie.loguserinfo.web.mapper.LoginMapper;
import com.shenzhijie.loguserinfo.web.mapper.RegisteredMapper;
import com.shenzhijie.loguserinfo.web.srevice.RegisteredService;
import com.shenzhijie.loguserinfo.common.utils.JwtUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

/**
 * All rights Reserved, Designed By www.info4z.club
 * <p>title:com.shenzhijie.loginopddd.srevice.impl</p>
 * <p>ClassName:LogServiceImpl</p>
 * <p>Description:TODO(请用一句话描述这个类的作用)</p>
 * <p>Compony:Info4z</p>
 * author:zhijieShen
 * date:2021/6/25
 * version:1.0
 * //=我下 我看你的依赖有没有问题  ok了  你的依赖冲突了...你的外层pom是2.5.2版本的boot,内部模块是2.3x版本,
 * 注意：本内容仅限于公司内部传阅，禁止外泄以及用于其他的商业目的
 */
@Service
public class RegisteredServiceImpl extends ServiceImpl<RegisteredMapper, Registered> implements RegisteredService {

    @Autowired
    private LoginMapper loginMapper;

    @Override
    public ResultWrapper registered(Registered registered) {
        /**
         * 密码加密
         * */
        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
        String password = bCryptPasswordEncoder.encode(registered.getPassword());
        registered.setPassword(password);
        save(registered);
        return ResultWrapper.getSuccessBuilder().build();
    }

    @Override
    public ResultWrapper login(LoginINFO loginINFO) {
        if (ObjectUtils.isEmpty(loginINFO)) {
            return ResultWrapper.getSuccessBuilder().build();
        }
        QueryWrapper<Registered> wrapper = new QueryWrapper<Registered>();
        wrapper.lambda()
                .eq(Registered::getName, loginINFO.getName());
        Registered selectOne = loginMapper.selectOne(wrapper);
        if (loginINFO.getName().equals(selectOne.getName())) {
            String registeredPassword = selectOne.getPassword();
            BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
            if (!bCryptPasswordEncoder.matches(loginINFO.getPassword(), registeredPassword)) {
                return ResultWrapper.getSuccessBuilder().build();
            }
        }
        String token = JwtUtils.createToken(loginINFO.getName());

        return ResultWrapper.getSuccessBuilder().data(token).build();
    }

    @Override
    public void selectInsert() {
        Registered registered = new Registered();
        registered.setName("项目管理");
        registered.setPassword("123456");
        registered.setEmail("31238977@qq.com");
        registered.setAddress("这颗地球");
        loginMapper.insert(registered);
        System.out.println(registered.getId());
        registered.setName("商务");
        loginMapper.insert(registered);
        System.out.println(registered.getId());
        registered.setName("保险产品");
        loginMapper.insert(registered);
        System.out.println(registered.getId());
    }
}
