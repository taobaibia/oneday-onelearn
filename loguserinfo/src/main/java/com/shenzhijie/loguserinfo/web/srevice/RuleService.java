package com.shenzhijie.loguserinfo.web.srevice;

import com.shenzhijie.loguserinfo.web.base.entity.ShenTestTable;
import org.kie.api.KieBase;
import org.kie.api.runtime.KieSession;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @description: 规则接口
 * @author: Jay
 * @create: 2021-09-02 15:08
 **/
@Service
public class RuleService {
    @Resource
    private KieBase kieBase;

    public void rule() {
        KieSession kieSession = kieBase.newKieSession();
        ShenTestTable shenTestTable = new ShenTestTable();
        shenTestTable.setName("申智杰");
        shenTestTable.setBoardId(18L);
        kieSession.insert(shenTestTable);
        kieSession.fireAllRules();
        kieSession.dispose();
    }
}
