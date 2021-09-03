package com.shenzhijie.loguserinfo.web.srevice;

import com.shenzhijie.loguserinfo.web.base.entity.DroolsPojo;
import lombok.extern.slf4j.Slf4j;
import org.kie.api.KieBase;
import org.kie.api.KieServices;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;

/**
 * @description: 规则接口
 * @author: Jay
 * @create: 2021-09-02 15:08
 **/
@Service
@Slf4j
public class RuleService {
    @Resource
    private KieBase kieBase;

    public void rule(String name) {

        KieServices ks = KieServices.Factory.get();
        KieContainer kContainer = ks.getKieClasspathContainer();
        KieSession kieSession = kContainer.newKieSession("rules-two");
//        StatelessKieSession kieSession = kieBase.newStatelessKieSession(kContainer.getKieSessionConfiguration("rules-two"));

        Map<String, String> map = new HashMap<>();
        map.put("key1", "value1");
        map.put("key2", "value2");
        DroolsPojo droolsPojo = new DroolsPojo();
        droolsPojo.setName(name);
        kieSession.insert(droolsPojo);
        kieSession.insert(map);
        log.info("查询所有规则.....");
        System.out.println("执行规则文件前:" + map);
        kieSession.fireAllRules();
        /**执行指定的规则文件
         kieSession.fireAllRules(new RuleNameEqualsAgendaFilter("rules-two"));*/
        System.out.println("执行规则文件后:" + map);
        kieSession.dispose();
    }
}
