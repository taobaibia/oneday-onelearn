package com.shenzhijie.loguserinfo.common.designPatterns.factoryMode;

/**
 * All rights Reserved, Designed By www.info4z.club
 * <p>title:com.shenzhijie.loguserinfo.common.designPatterns.factoryMode</p>
 * <p>ClassName:NvWa</p>
 * <p>Description:TODO(女娲造人)</p>
 * <p>Compony:Info4z</p>
 * author:zhijieShen
 * date:2021/8/18
 * version:1.0
 * 注意：本内容仅限于公司内部传阅，禁止外泄以及用于其他的商业目的
 */
public class NvWa {
    public static void main(String[] args) {
        System.out.println("第一次造人======================");
        Human whiteHuman = HumanFactory.createHuman(WhiteHuman.class);
        whiteHuman.cry();
        whiteHuman.laugh();
        whiteHuman.talk();
        System.out.println("第二次造人======================");
        Human BlackHuman = HumanFactory.createHuman(BlackHuman.class);
        BlackHuman.cry();
        BlackHuman.talk();
        BlackHuman.laugh();
        System.out.println("第三次造人======================");
        Human YellowerHuman = HumanFactory.createHuman(YellowerHuman.class);
        YellowerHuman.cry();
        YellowerHuman.talk();
        YellowerHuman.laugh();
        System.out.println(Human.class.getPackage().getName());

        for (int i = 0; i < 100; i++) {
            System.out.println("\n\n------------随机产生人类了-----------------" + i);
            Human human = HumanFactory.createHuman();
            human.cry();
            human.laugh();
            human.talk();
        }
    }
}
