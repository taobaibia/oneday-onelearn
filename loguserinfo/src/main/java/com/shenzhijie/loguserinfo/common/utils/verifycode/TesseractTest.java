package com.shenzhijie.loguserinfo.common.utils.verifycode;

import net.sourceforge.tess4j.Tesseract;
import net.sourceforge.tess4j.TesseractException;

import java.io.File;

/**
 * All rights Reserved, Designed By www.info4z.club
 * <p>title:com.shenzhijie.loguserinfo.common.utils.verifycode</p>
 * <p>ClassName:TesseractTest</p>
 * <p>Description:TODO(语言包_可自动识别验证码)</p>
 * <p>Compony:Info4z</p>
 * author:zhijieShen
 * date:2021/7/6
 * version:1.0
 * 注意：本内容仅限于公司内部传阅，禁止外泄以及用于其他的商业目的
 */
public class TesseractTest {
    public static void main(String[] args) throws TesseractException {
        Tesseract iTesseract = new Tesseract();
//        语言包加进来
        iTesseract.setDatapath("D:\\LangerCode");
        iTesseract.setLanguage("eng");
        File file = new File("C:\\Users\\user\\Pictures\\Saved Pictures");
        for (File f : file.listFiles()) {
            String s = iTesseract.doOCR(f);
            System.out.println(f.getName() + "识别后的验证码:  " + s);
        }

    }
}
