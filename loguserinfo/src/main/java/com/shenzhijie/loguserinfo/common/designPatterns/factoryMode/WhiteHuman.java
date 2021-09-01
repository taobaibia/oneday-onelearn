package com.shenzhijie.loguserinfo.common.designPatterns.factoryMode;

/**
 * All rights Reserved, Designed By www.info4z.club
 * <p>title:com.shenzhijie.loguserinfo.common.designPatterns.factoryMode</p>
 * <p>ClassName:YellowerHuman</p>
 * <p>Description:TODO(定义白种人)</p>
 * <p>Compony:Info4z</p>
 * author:zhijieShen
 * date:2021/8/18
 * version:1.0
 * 注意：本内容仅限于公司内部传阅，禁止外泄以及用于其他的商业目的
 */
public class WhiteHuman implements Human {
    @Override
    public void laugh() {
        System.out.println("白皮肤人.......大笑");
    }

    @Override
    public void cry() {
        System.out.println("白皮肤人.......大哭");
    }

    @Override
    public void talk() {
        System.out.println("白皮肤人.......暴躁");
    }
}
