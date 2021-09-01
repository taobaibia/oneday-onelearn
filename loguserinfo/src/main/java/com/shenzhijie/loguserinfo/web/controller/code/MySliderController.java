package com.shenzhijie.loguserinfo.web.controller.code;

import com.shenzhijie.loguserinfo.common.utils.SliderUtil;
import com.shenzhijie.loguserinfo.common.utils.VerificationVO;
import com.shenzhijie.loguserinfo.web.config.annotations.TokenCheck;
import io.swagger.annotations.Api;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * All rights Reserved, Designed By www.info4z.club
 * <p>title:com.shenzhijie.loguserinfo.web.controller</p>
 * <p>ClassName:MySliderController</p>
 * <p>Description:TODO(请用一句话描述这个类的作用)</p>
 * <p>Compony:Info4z</p>
 * author:zhijieShen
 * date:2021/7/8
 * version:1.0
 * 注意：本内容仅限于公司内部传阅，禁止外泄以及用于其他的商业目的
 */
@RestController
@Api(tags = "滑块验证码工具")
@RequestMapping("/Slider-image")
public class MySliderController {


    @Operation(summary = "Slider生成滑块验证")
    @GetMapping("/generatorSlider")
    @TokenCheck(required = false)
    public VerificationVO generatorSlider(HttpServletRequest request, HttpServletResponse response) {
        return SliderUtil.cut();
    }

    @GetMapping("verify-Slider")
    @Operation(summary = "Slider校验滑块")
    public String verifySlider(String verifyCode, HttpServletRequest request) {
        return "校验不通过";
    }
}
