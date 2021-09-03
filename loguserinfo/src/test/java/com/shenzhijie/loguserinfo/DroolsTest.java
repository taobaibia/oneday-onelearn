package com.shenzhijie.loguserinfo;

import com.shenzhijie.loguserinfo.web.base.entity.ShenTestTable;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.kie.api.KieServices;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;

import java.util.HashMap;
import java.util.Map;

/**
 * @description: 规则
 * @author: Jay
 * @create: 2021-08-30 15:26
 **/
@Slf4j
public class DroolsTest {

    @Test
    public void droolsOrderTest() {
        KieServices ks = KieServices.Factory.get();
        KieContainer kContainer = ks.getKieClasspathContainer();
        KieSession kSession = kContainer.newKieSession("rules-two");
        Map<String, String> map = new HashMap<>();
        ShenTestTable shenTestTable = new ShenTestTable();
        shenTestTable.setBoardId(1000L);
        kSession.insert(shenTestTable);
        kSession.insert(map);
        kSession.fireAllRules();
        System.out.println(map);
        kSession.dispose();
    }

    @Test
    public void test1() {
        /*规则*/
        KieServices kieServices = KieServices.Factory.get();
        KieContainer kieClasspathContainer = kieServices.getKieClasspathContainer();
        //会话对象，用于和规则引擎交互
        KieSession kieSession = kieClasspathContainer.newKieSession("rules-two");
        //构造对象，设置原始价格，由规则引擎根据优惠规则计算优惠后的价格
        ShenTestTable shenTestTable = new ShenTestTable();
        //将数据提供给规则引擎，规则引擎会根据提供的数据进行规则匹配
        shenTestTable.setName("申智杰");
        kieSession.insert(shenTestTable);
        //激活规则引擎，如果规则匹配成功则执行规则
        kieSession.fireAllRules();
        //关闭会话
        kieSession.dispose();
    }

}
