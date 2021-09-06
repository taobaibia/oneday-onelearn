package com.shenzhijie.loguserinfo.web.srevice;

import com.shenzhijie.loguserinfo.common.utils.KieSessionUtils;
import com.shenzhijie.loguserinfo.web.base.entity.DroolsPojo;
import lombok.extern.slf4j.Slf4j;
import org.drools.core.base.RuleNameStartsWithAgendaFilter;
import org.kie.api.KieBase;
import org.kie.api.KieServices;
import org.kie.api.runtime.KieSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
    @Autowired
    private KieBase kieBase;

    /*测试业务不一致*/
    public void rule(String name) {
        KieSession kieSession = KieServices.Factory.get().getKieClasspathContainer().newKieSession("rules-two");

        DroolsPojo droolsPojo = new DroolsPojo();
        droolsPojo.setName(name);

        List<String> list = new ArrayList<String>();
        list.add("tom");
        list.add("jay");
        Map<String, String> map = new HashMap<>();
        map.put("key1", "var1");
        map.put("key2", "var2");
        kieSession.insert(list);
//        droolsPojo.setList(list);
        kieSession.insert(droolsPojo);
        kieSession.insert(map);
        System.out.println("执行前--------" + map);
        //通过规则过滤器实现只执行指定规则
        kieSession.fireAllRules(new RuleNameStartsWithAgendaFilter("BoardThousand"));
        System.out.println("执行后--------" + map);
        System.out.println("销毁前kiesession-----" + kieSession);
        kieSession.dispose();
        System.out.println("销毁后kiesession-----" + kieSession);
    }

    /*字符串类型的规则*/
    public void rule_service(String name) throws Exception {
        KieSession kieSession = KieSessionUtils.createKieSessionFromDRL(
                "package rules.xuhui\n" +
                        "import com.shenzhijie.loguserinfo.web.base.entity.ShenTestTable\n" +
                        "import com.shenzhijie.loguserinfo.web.base.entity.DroolsPojo\n" +
                        "import java.util.Map " +
                        "rule \"BoardThousand\"\n" +
                        "    salience 8\n" +
                        "   when\n" +
                        "       $drools:DroolsPojo(name contains \"jay\")\n" +
                        "       DroolsPojo(list contains name)\n" +
                        "       $map:Map()\n" +
                        "    then\n" +
                        "       System.out.println(\"boardId为:\"+$drools.getName());\n" +
                        "       $map.put(\"board3\",\"1000\");\n" +
                        "       System.out.println($map.get(\"board3\"));\n" +
                        "    end");
        /*设置焦点*/
//        kieSession.getAgenda().getAgendaGroup("").setFocus();
        DroolsPojo droolsPojo = new DroolsPojo();
        droolsPojo.setName(name);
        List<String> list = new ArrayList<String>();
        list.add("tom");
        list.add("jay");
        droolsPojo.setList(list);
        Map<String, String> map = new HashMap<>();
        map.put("key1", "var1");
        map.put("key2", "var2");
        kieSession.insert(droolsPojo);
        kieSession.insert(map);
        System.out.println("执行前--------" + map);
        kieSession.fireAllRules();
        System.out.println("执行后--------" + map);
        kieSession.dispose();
    }

    /*spring_boot_test---rules*/
    public void rules_boot_test(String name) {
        KieSession kieSession = kieBase.newKieSession();
        DroolsPojo droolsPojo = new DroolsPojo();
        droolsPojo.setName(name);
        List<String> list = new ArrayList<String>();
        list.add("tom");
        list.add("jay");
        droolsPojo.setList(list);
        Map<String, String> map = new HashMap<>();
        map.put("key1", "var1");
        map.put("key2", "var2");
        kieSession.insert(droolsPojo);
        kieSession.insert(map);
        System.out.println("执行前--------" + map);
        kieSession.fireAllRules();
        System.out.println("执行后--------" + map);
        kieSession.dispose();
    }
}
