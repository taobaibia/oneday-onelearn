package com.shenzhijie.loguserinfo.web.controller;


import com.shenzhijie.loguserinfo.common.utils.JwtUtils;
import com.shenzhijie.loguserinfo.web.base.entity.*;
import com.shenzhijie.loguserinfo.web.base.result.ResultWrapper;
import com.shenzhijie.loguserinfo.web.config.annotations.TokenCheck;
import com.shenzhijie.loguserinfo.web.srevice.*;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.v3.oas.annotations.Operation;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * All rights Reserved, Designed By www.info4z.club
 * <p>title:com.shenzhijie.loginopddd.controller</p>
 * <p>ClassName:LogPassWord</p>
 * <p>Description:TODO(用户登录接口)</p>
 * <p>Compony:Info4z</p>
 * author:zhijieShen
 * date:2021/6/25
 * version:1.0
 * 注意：本内容仅限于公司内部传阅，禁止外泄以及用于其他的商业目的
 */
@Slf4j
@RestController
@Api(tags = "用户接口")
@RequestMapping("/log")

public class RegisteredController {

    @Autowired
    private RegisteredService registeredService;
    @Autowired
    private UmsMemberService umsMemberService;
    @Autowired
    private ShenzhijieService shenzhijieService;
    @Autowired
    private LeetCodeService leetCodeService;
    @Autowired
    private RuleService ruleService;

    @Operation(summary = "注册login接口")
    @PostMapping("/Registered")
    public ResultWrapper Registered(@RequestBody @Valid Registered registered) {

        return registeredService.registered(registered);
    }

    @Operation(summary = "登录login接口")
    @PostMapping("/login")
    public ResultWrapper<LoginINFO> login(@Valid LoginINFO loginINFO) {
        System.out.println("登录接口:");
        return registeredService.login(loginINFO);
    }

    @Operation(summary = "解密token接口")
    @GetMapping("/test-verify")
    public String verify(String token) {
        String s = JwtUtils.parseToken(token);
        System.out.println(s);
        return s;
    }

    @Operation(summary = "退出")
    @PostMapping("/edit")
    @TokenCheck
    public ResultWrapper edit(@RequestBody UmsMember umsMember) {
        System.out.println("edit");
        return umsMemberService.edit(umsMember);
    }

    @Operation(summary = "测试接口")
    @GetMapping("/test")
    public void test() {
        registeredService.selectInsert();
    }

    @Operation(summary = "测试plus插件")
    @PostMapping("/test-plus")
    public ShenTestTable test_plus(@RequestBody ShenTestTable shenTestTable) {
        ShenTestTable result = shenzhijieService.saveShenzhijie(shenTestTable);
        System.out.println(result);
        return result;
    }

    @Operation(summary = "测试leetCode代码")
    @GetMapping("/leetCode")
    @ApiImplicitParam(name = "nums", dataTypeClass = List.class, value = "nums", allowMultiple = true)
    public int[] leetCode(@RequestParam() int[] nums, @RequestParam() int target) {
//        @RequestParam int[] nums, @RequestParam int target
        System.out.println("==========================");
//        int[] nums = {1, 2, 3, 4, 5, 6, 7, 8};
//        int target = 15;
        int[] sum = leetCodeService.twoSum(nums, target);
        System.out.println("sum:" + sum);
        System.out.println(ToStringBuilder.reflectionToString(sum));
        return sum;
    }


    @Operation(summary = "多数据源的接口测试查询")
    @GetMapping("/selectAll")
    public Map<String, Object> selectAll(@RequestParam String name) {
        Map<String, Object> map = new HashMap<>();
        List<IsipProjectBoard> boardAll = leetCodeService.findBoardAll(name);
        List<ShenTestTable> shenJayTable = leetCodeService.findShenJayTable();
        List<tbItemCat> tbItemCats = leetCodeService.findtbItemCat();
        map.put("boardResult-isip-master", boardAll);
        map.put("JayTableResult-local-slave_1", shenJayTable);
        map.put("tbItemCats-local-slave_2", tbItemCats);
        return map;
    }


    @Operation(summary = "规则查询接口")
    @GetMapping("/findRules")
    public List<ShenTestTable> findRules(@RequestParam String name) {
        List<ShenTestTable> shenzhijieRules = shenzhijieService.findShenzhijieRules(name);
        return shenzhijieRules;
    }

    @RequestMapping("/rule")
    public String rule() {
        ruleService.rule();
        return "OK";
    }

}
