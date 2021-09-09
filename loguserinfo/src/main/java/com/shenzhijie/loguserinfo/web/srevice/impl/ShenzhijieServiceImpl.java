package com.shenzhijie.loguserinfo.web.srevice.impl;

import com.baomidou.dynamic.datasource.annotation.DS;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.shenzhijie.loguserinfo.web.base.entity.other.ShenTestTable;
import com.shenzhijie.loguserinfo.web.base.entity.other.ShenTestTableCopy;
import com.shenzhijie.loguserinfo.web.mapper.ShenTestTableMapper;
import com.shenzhijie.loguserinfo.web.srevice.ShenzhijieService;
import org.kie.api.KieServices;
import org.kie.api.runtime.KieSession;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class ShenzhijieServiceImpl extends ServiceImpl<ShenTestTableMapper, ShenTestTable> implements ShenzhijieService {
    @Autowired
    private ShenTestTableMapper shenTestTableMapper;
    @Autowired
    private com.shenzhijie.loguserinfo.web.mapper.ShenTestTableCopyMapper ShenTestTableCopyMapper;

    @Override
    public ShenTestTable saveShenzhijie(ShenTestTable shenTestTable) {
//        shenTestTable.setName("申");
//        shenTestTable.setTitle("这个是标题");
        save(shenTestTable);
        return shenTestTable;
    }

    @Override
    @DS("master")
    @Transactional
    public List<ShenTestTable> findShenzhijieRules(String name) {
        QueryWrapper<ShenTestTable> wrapper = new QueryWrapper<>();
        wrapper.lambda()
                .like(ShenTestTable::getName, name);
        List<ShenTestTable> selectList = shenTestTableMapper.selectList(wrapper);
        /*规则*/
        KieSession kieSession = KieServices.Factory.get().getKieClasspathContainer().newKieSession("");
        //会话对象，用于和规则引擎交互
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

    @Override
    public ShenTestTableCopy copyResult() {
        ShenTestTable shenTestTable = new ShenTestTable();
        shenTestTable.setId(1L);
        shenTestTable.setBoardId(1L);
        shenTestTable.setName("jay");
        shenTestTable.setTitle("dajiahao");
        ShenTestTableCopy shenTestTableCopy = new ShenTestTableCopy();
        shenTestTableCopy.setName("bom");
        BeanUtils.copyProperties(shenTestTable, shenTestTableCopy, new String[]{"title"});
        ShenTestTableCopyMapper.insert(shenTestTableCopy);

        return null;
    }
}
