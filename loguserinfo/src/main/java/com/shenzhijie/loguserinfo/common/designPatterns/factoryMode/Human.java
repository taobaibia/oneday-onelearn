package com.shenzhijie.loguserinfo.common.designPatterns.factoryMode;

/**
 * All rights Reserved, Designed By www.info4z.club
 * <p>title:com.shenzhijie.loguserinfo.common.designPatterns.factoryMode</p>
 * <p>ClassName:Human</p>
 * <p>Description:TODO(定义一个人类)</p>
 * <p>Compony:Info4z</p>
 * author:zhijieShen
 * date:2021/8/18
 * version:1.0
 * 注意：本内容仅限于公司内部传阅，禁止外泄以及用于其他的商业目的
 */
public interface Human {
    /**
     * 笑
     */
    public void laugh();

    /**
     * 哭
     */
    public void cry();

    /**
     * 交流
     */
    public void talk();
}
