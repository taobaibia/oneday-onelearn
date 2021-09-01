package com.shenzhijie.loguserinfo;

import com.shenzhijie.loguserinfo.web.base.entity.ShenTestTable;
import org.junit.jupiter.api.Test;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;

/**
 * 需求
 * 计算额外积分金额 规则如下: 订单原价金额
 * 100以下, 不加分
 * 100-500 加100分
 * 500-1000 加500分
 * 1000 以上 加1000分
 */
@SpringBootTest
public class AddtessDroolsTests {
    @Resource
    private KieContainer kieContainer;
    @Resource
    private KieSession kieSession;


    @Test
    public void droolsOrderTest() {
        ShenTestTable shenTestTable = new ShenTestTable();
        shenTestTable.setBoardId(5L);
        shenTestTable.setName("测试1");
        kieSession.insert(shenTestTable);

        int ruleFiredCount = kieSession.fireAllRules();
        System.out.println("触发了" + ruleFiredCount + "条规则");

    }

    /**
     * 生成随机数
     *
     * @param num
     * @return
     */
    public String generateRandom(int num) {
        String chars = "0123456789";
        StringBuffer number = new StringBuffer();
        for (int i = 0; i < num; i++) {
            int rand = (int) (Math.random() * 10);
            number = number.append(chars.charAt(rand));
        }
        return number.toString();
    }


}
