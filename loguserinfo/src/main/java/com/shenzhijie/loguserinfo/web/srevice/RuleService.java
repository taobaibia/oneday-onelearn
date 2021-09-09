package com.shenzhijie.loguserinfo.web.srevice;

import com.shenzhijie.loguserinfo.common.utils.KieSessionUtils;
import com.shenzhijie.loguserinfo.web.base.entity.rulePojo.DroolsPojo;
import lombok.extern.slf4j.Slf4j;
import org.drools.core.base.RuleNameStartsWithAgendaFilter;
import org.kie.api.KieBase;
import org.kie.api.KieServices;
import org.kie.api.runtime.KieSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
    @Autowired
    private KieBase kieBase;

    /*测试业务不一致*/
    public void rule(String name) {
        KieSession kieSession = KieServices.Factory.get().getKieClasspathContainer().newKieSession("rules-two");
        DroolsPojo droolsPojo = new DroolsPojo();
        droolsPojo.setName(name);
        Map<String, String> map = new HashMap<>();
        map.put("key1", "var1");
        map.put("key2", "var2");
        kieSession.insert(droolsPojo);
        kieSession.setGlobal("mapResult", map);
        System.out.println("执行前--------" + map);
        //通过规则过滤器实现只执行指定规则
        kieSession.fireAllRules(new RuleNameStartsWithAgendaFilter("BoardThousand"));
        System.out.println("执行后--------" + map);
        kieSession.dispose();
    }

    /*字符串类型的规则*/
    public void rule_service(String name) throws Exception {
        KieSession kieSession = KieSessionUtils.createKieSessionFromDRL(
                "package rules.xuhui\n" +
                        "import com.shenzhijie.loguserinfo.web.base.entity.other.ShenTestTable\n" +
                        "import com.shenzhijie.loguserinfo.web.base.entity.rulePojo.DroolsPojo\n" +
                        "global java.util.Map mapResult;\n" +
                        "rule \"BoardThousand\"\n" +
                        "    salience 8\n" +
                        "   when\n" +
                        "        $drools:DroolsPojo(name in (\"jay\",\"bom\"))\n" +
                        "    then\n" +
                        "       System.out.println(\"DroolsPojo.name为:\"+$drools.getName());\n" +
                        "       mapResult.put(\"name\",\"jay\");\n" +
                        "       System.out.println(mapResult.get(\"name\"));\n" +
                        "    end");
        /*设置焦点*/
//        kieSession.getAgenda().getAgendaGroup("").setFocus();
        DroolsPojo droolsPojo = new DroolsPojo();
        droolsPojo.setName(name);
        Map<String, String> map = new HashMap<>();
        map.put("key1", "var1");
        map.put("key2", "var2");
        kieSession.insert(droolsPojo);
        kieSession.setGlobal("mapResult", map);
        System.out.println("执行前--------" + map);
        kieSession.fireAllRules(new RuleNameStartsWithAgendaFilter("BoardThousand"));
        System.out.println("执行后--------" + map);
        kieSession.dispose();
    }

    /*spring_boot_test---rules*/
    public void rules_boot_test(String name) {
        KieSession kieSession = kieBase.newKieSession();
        DroolsPojo droolsPojo = new DroolsPojo();
        droolsPojo.setName(name);
        Map<String, String> map = new HashMap<>();
        map.put("key1", "var1");
        map.put("key2", "var2");
        kieSession.insert(droolsPojo);
        kieSession.setGlobal("mapResult", map);
        System.out.println("执行前--------" + map);
        kieSession.fireAllRules(new RuleNameStartsWithAgendaFilter("BoardThousand"));
        System.out.println("执行后--------" + map);
        kieSession.dispose();
    }
}
