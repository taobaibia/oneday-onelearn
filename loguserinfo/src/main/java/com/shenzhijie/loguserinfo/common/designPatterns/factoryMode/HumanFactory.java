package com.shenzhijie.loguserinfo.common.designPatterns.factoryMode;


import java.util.HashMap;
import java.util.List;
import java.util.Random;

/**
 * All rights Reserved, Designed By www.info4z.club
 * <p>title:com.shenzhijie.loguserinfo.common.designPatterns.factoryMode</p>
 * <p>ClassName:HumanFactory</p>
 * <p>Description:TODO(人类工厂)</p>
 * <p>Compony:Info4z</p>
 * author:zhijieShen
 * date:2021/8/18
 * version:1.0
 * 注意：本内容仅限于公司内部传阅，禁止外泄以及用于其他的商业目的
 */
public class HumanFactory {

    //女娲生气了，把一团泥巴塞到八卦炉，哎产生啥人类就啥人类
    public static Human createHuman() {
        Human human = null; //定义一个类型的人类

        //首先是获得有多少个实现类，多少个人类
        List<Class> concreteHumanList =
                ClassUtils.getAllClassByInterface(Human.class); //定义了多少人类
        //八卦炉自己开始想烧出什么人就什么人
        Random random = new Random();
        int rand = random.nextInt(concreteHumanList.size());

        human = createHuman(concreteHumanList.get(rand));

        return human;
    }


    /**
     * 定义一个Map,初始化的过的Human对象都放在这里
     */
    private static HashMap<String, Human> humanHashMap = new HashMap<>();

    /**
     * 定义烤箱,扔进去自动就出来
     */
    public static Human createHuman(Class c) {
        //定义人类
        Human human = null;
        /**原先*/
        try {
/*            产生一个人类
            human= (Human) Class.forName(c.getName()).newInstance();*/

            //如果MAP中有，则直接从取出，不用初始化了
            if (humanHashMap.containsKey(c.getSimpleName())) {
                human = humanHashMap.get(c.getSimpleName());
            } else {
                human = (Human) Class.forName(c.getName()).newInstance();
                //放到MAP中
                humanHashMap.put(c.getSimpleName(), human);
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            System.out.println("找不到指定的人类");
        } catch (InstantiationException e) {
            e.printStackTrace();
            System.out.println("必须指定人类颜色");
        } catch (IllegalAccessException e) {
            e.printStackTrace();
            System.out.println("人类定义错误");
        }
        return human;
    }
}
