package com.shenzhijie.loguserinfo.web.srevice.impl;

import com.baomidou.dynamic.datasource.annotation.DS;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.shenzhijie.loguserinfo.web.base.entity.ShenTestTable;
import com.shenzhijie.loguserinfo.web.mapper.ShenTestTableMapper;
import com.shenzhijie.loguserinfo.web.srevice.ShenzhijieService;
import org.kie.api.KieServices;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * All rights Reserved, Designed By www.info4z.club
 * <p>title:com.shenzhijie.loguserinfo.web.srevice.impl</p>
 * <p>ClassName:ShenzhijieServiceImpl</p>
 * <p>Description:TODO(请用一句话描述这个类的作用)</p>
 * <p>Compony:Info4z</p>
 * author:zhijieShen
 * date:2021/7/29
 * version:1.0
 * 注意：本内容仅限于公司内部传阅，禁止外泄以及用于其他的商业目的
 */
@Service
public class ShenzhijieServiceImpl extends ServiceImpl<ShenTestTableMapper, ShenTestTable> implements ShenzhijieService {
    @Autowired
    private ShenTestTableMapper shenTestTableMapper;

    @Override
    public ShenTestTable saveShenzhijie(ShenTestTable shenTestTable) {
//        shenTestTable.setName("申");
//        shenTestTable.setTitle("这个是标题");
        save(shenTestTable);
        return shenTestTable;
    }

    @Override
    @DS("master")
    public List<ShenTestTable> findShenzhijieRules(String name) {
        QueryWrapper<ShenTestTable> wrapper = new QueryWrapper<>();
        wrapper.lambda()
                .like(ShenTestTable::getName, name);
        List<ShenTestTable> selectList = shenTestTableMapper.selectList(wrapper);
        /*规则*/
        KieServices kieServices = KieServices.Factory.get();
        KieContainer kieClasspathContainer = kieServices.getKieClasspathContainer();
        //会话对象，用于和规则引擎交互
        KieSession kieSession = kieClasspathContainer.newKieSession("findName");
        //构造对象，设置原始价格，由规则引擎根据优惠规则计算优惠后的价格
//        Order order = new Order();
//        order.setOriginalPrice(210D);

        ShenTestTable shenTestTable = new ShenTestTable();
        //将数据提供给规则引擎，规则引擎会根据提供的数据进行规则匹配
        shenTestTable.setName("申智杰");
        shenTestTable.setBoardId(14L);
        kieSession.insert(shenTestTable);
        //激活规则引擎，如果规则匹配成功则执行规则
        kieSession.fireAllRules();
        //关闭会话
        kieSession.dispose();

        return selectList;
    }
}
