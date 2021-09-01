package com.shenzhijie.loguserinfo.common.designPatterns.factoryMode;

/**
 * All rights Reserved, Designed By www.info4z.club
 * <p>title:com.shenzhijie.loguserinfo.common.designPatterns.factoryMode</p>
 * <p>ClassName:YellowerHuman</p>
 * <p>Description:TODO(定义黄种人)</p>
 * <p>Compony:Info4z</p>
 * author:zhijieShen
 * date:2021/8/18
 * version:1.0
 * 注意：本内容仅限于公司内部传阅，禁止外泄以及用于其他的商业目的
 */
public class YellowerHuman implements Human {
    @Override
    public void laugh() {
        System.out.println("黄皮肤人.......哈哈哈笑");
    }

    @Override
    public void cry() {
        System.out.println("黄皮肤人.......呜呜哭");
    }

    @Override
    public void talk() {
        System.out.println("黄皮肤人.......交流");
    }
}
