package com.shenzhijie.loguserinfo;

import com.shenzhijie.loguserinfo.web.base.entity.ShenTestTable;
import org.drools.core.base.RuleNameEqualsAgendaFilter;
import org.junit.jupiter.api.Test;
import org.kie.api.KieServices;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.core.annotation.Order;

import javax.annotation.Resource;
import java.util.ArrayList;

/**
 * @description: 规则
 * @author: Jay
 * @create: 2021-08-30 15:26
 **/
public class DroolsTest {

    @Resource
    private KieContainer kieContainer;
    @Resource
    private KieSession kieSession;

    @Test
    public void droolsOrderTest() {

        ShenTestTable shenTestTable = new ShenTestTable();
        shenTestTable.setName("小明");
        shenTestTable.setBoardId(14L);
        kieSession.insert(shenTestTable);
        int ruleFiredCount = kieSession.fireAllRules();
        System.out.println("触发了" + ruleFiredCount + "条规则");

    }

    @Test

    public void test1() {
        /*规则*/
        KieServices kieServices = KieServices.Factory.get();
        KieContainer kieClasspathContainer = kieServices.getKieClasspathContainer();
        //会话对象，用于和规则引擎交互
        KieSession kieSession = kieClasspathContainer.newKieSession("passOne-shanghai-one");

        //构造对象，设置原始价格，由规则引擎根据优惠规则计算优惠后的价格
        ShenTestTable shenTestTable = new ShenTestTable();
        //将数据提供给规则引擎，规则引擎会根据提供的数据进行规则匹配
        shenTestTable.setName("小明");
        shenTestTable.setBoardId(14L);
        kieSession.insert(shenTestTable);

        kieSession.fireAllRules(new RuleNameEqualsAgendaFilter("rules.passOne.pasone.passOne-shanghai-one.drl"));
        //激活规则引擎，如果规则匹配成功则执行规则
        kieSession.fireAllRules();
        //关闭会话
        kieSession.dispose();
    }

    @Test
    @Order(1)
    public void test2() {
        new ClassPathXmlApplicationContext();
        ShenTestTable table = new ShenTestTable();
        ArrayList<Object> list = new ArrayList<>();
        list.contains("1");
    }

}
