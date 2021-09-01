package com.shenzhijie.loguserinfo.common.utils;

import com.octo.captcha.CaptchaFactory;
import com.octo.captcha.component.image.backgroundgenerator.UniColorBackgroundGenerator;
import com.octo.captcha.component.image.color.RandomRangeColorGenerator;
import com.octo.captcha.component.image.fontgenerator.RandomFontGenerator;
import com.octo.captcha.component.image.textpaster.RandomTextPaster;
import com.octo.captcha.component.image.wordtoimage.ComposedWordToImage;
import com.octo.captcha.component.word.FileDictionary;
import com.octo.captcha.component.word.wordgenerator.ComposeDictionaryWordGenerator;
import com.octo.captcha.engine.GenericCaptchaEngine;
import com.octo.captcha.image.gimpy.GimpyFactory;
import com.octo.captcha.service.image.ImageCaptchaService;
import com.octo.captcha.service.multitype.GenericManageableCaptchaService;

import java.awt.*;

/**
 * All rights Reserved, Designed By www.info4z.club
 * <p>title:com.shenzhijie.loguserinfo.common.utils</p>
 * <p>ClassName:JCaptchaUtil</p>
 * <p>Description:TODO(验证码工具类)</p>
 * <p>Compony:Info4z</p>
 * author:zhijieShen
 * date:2021/7/7
 * version:1.0
 * 注意：本内容仅限于公司内部传阅，禁止外泄以及用于其他的商业目的
 */
public class JCaptchaUtil {
    /**
     * 单例
     * 饿汉式_简单,不占用存储空间
     */
    private static final ImageCaptchaService service = imageCaptchaService();

    private static ImageCaptchaService getService() {
        return service;
    }

    /**
     * 工具生成验证码
     */
    public static ImageCaptchaService imageCaptchaService() {
//        背景
        UniColorBackgroundGenerator uniColorBackgroundGenerator = new UniColorBackgroundGenerator(100, 50);
//        字
        RandomRangeColorGenerator TextColor = new RandomRangeColorGenerator(new int[]{0, 255}, new int[]{0, 255}, new int[]{0, 255});
        RandomTextPaster randomTextPaster = new RandomTextPaster(4, 5, TextColor);
        RandomFontGenerator randomFontGenerator = new RandomFontGenerator(20, 30, new Font[]{new Font("Courie", Font.PLAIN, 20)});
//        组装图像
        ComposedWordToImage composedWordToImage = new ComposedWordToImage(randomFontGenerator, uniColorBackgroundGenerator, randomTextPaster);
//        从jar包获取数据字典
        ComposeDictionaryWordGenerator composeDictionaryWordGenerator =
                new ComposeDictionaryWordGenerator(new FileDictionary("toddList"));
        GimpyFactory gimpyFactory = new GimpyFactory(composeDictionaryWordGenerator, composedWordToImage);
//        生成一个captch引擎
        GenericCaptchaEngine genericCaptchaEngine = new GenericCaptchaEngine(new CaptchaFactory[]{gimpyFactory});

        return new GenericManageableCaptchaService(genericCaptchaEngine, 20, 2000, 2000);
    }
}
