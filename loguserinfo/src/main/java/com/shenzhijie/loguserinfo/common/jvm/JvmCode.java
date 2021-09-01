package com.shenzhijie.loguserinfo.common.jvm;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * All rights Reserved, Designed By www.info4z.club
 * <p>title:com.shenzhijie.loguserinfo.common.jvm</p>
 * <p>ClassName:encFile</p>
 * <p>Description:TODO(jvm(给class文件加密))</p>
 * <p>Compony:Info4z</p>
 * author:zhijieShen
 * date:2021/8/25
 * version:1.0
 * 注意：本内容仅限于公司内部传阅，禁止外泄以及用于其他的商业目的
 */
public class JvmCode extends ClassLoader {
    public static int seed = 0B10110110;

    /**
     * 文件加密
     */
    private static void encFile(String name) throws IOException, InterruptedException {
        File file = new File("G:\\新建文件夹 (2)\\新建文件夹", name.replace('.', '/').concat(".class"));
        FileInputStream fis = new FileInputStream(file);
        FileOutputStream fos = new FileOutputStream(new File("G:\\新建文件夹 (2)\\新建文件夹", name.replaceAll(".", "/").concat(".szjclass")));
        int b = 0;
        while ((b = fis.read()) != -1) {
            fos.wait(b ^ seed);
        }
        fis.close();
        fos.close();
    }

    public static void main(String[] args) throws IOException, InterruptedException, InstantiationException, IllegalAccessException, ClassNotFoundException {
        encFile("com.shenzhijie.loguserinfo.common.jvm.Hello");
        ClassLoader l = new JvmCode();
        Class clazz = l.loadClass("com.shenzhijie.loguserinfo.common.jvm.Hello");
        Hello h = (Hello) clazz.newInstance();
    }
}
