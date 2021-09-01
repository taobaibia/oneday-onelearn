package com.shenzhijie.loguserinfo.common.surceCode.springBootCode.start;

import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * All rights Reserved, Designed By www.info4z.club
 * <p>title:com.shenzhijie.loguserinfo.common.surceCode.springBootCode.start</p>
 * <p>ClassName:Mystart</p>
 * <p>Description:TODO(自定义启动类的加载配置,需要配置--->(扫描路径,再同级如com目录下需要写)META-INF下的 spring.factories,org.springframework.boot.autoconfigure.EnableAutoConfiguration=\com.shenzhijie.loguserinfo.common.surceCode.springBootCode.start.MyStart)</p>
 * <p>Compony:Info4z</p>
 * author:zhijieShen
 * date:2021/8/19
 * version:1.0
 * 注意：本内容仅限于公司内部传阅，禁止外泄以及用于其他的商业目的
 */
@Configuration
@ConditionalOnProperty(name = "enable.autoConfiguration", matchIfMissing = true)
public class MyStart {
    static {
        System.out.println("myStart......init.............");
    }

    @Bean
    public Person person() {
        return new Person();
    }
}
