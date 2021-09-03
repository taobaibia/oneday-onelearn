package com.shenzhijie.loguserinfo.web.srevice;

import com.shenzhijie.loguserinfo.web.base.entity.DroolsPojo;
import lombok.extern.slf4j.Slf4j;
import org.kie.api.KieBase;
import org.kie.api.KieServices;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
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
        KieServices kieServices = KieServices.Factory.get();
        KieContainer kieClasspathContainer = kieServices.getKieClasspathContainer();
        KieSession kieSession = kieClasspathContainer.newKieSession("rules-two");

        DroolsPojo droolsPojo = new DroolsPojo();
        droolsPojo.setName(name);
        List<String> list = new ArrayList<String>();
        list.add("tom");
        list.add("jay");
        Map<String, String> map = new HashMap<>();
        map.put("key1", "var1");
        map.put("key2", "var2");
        droolsPojo.setList(list);
        kieSession.insert(droolsPojo);
        kieSession.insert(map);
        System.out.println("执行前--------" + map);
        //通过规则过滤器实现只执行指定规则
        kieSession.fireAllRules();
        System.out.println("执行后--------" + map);
        kieSession.dispose();
    }
}
