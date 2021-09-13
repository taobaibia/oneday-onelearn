package com.shenzhijie.loguserinfo.web.controller.flux;

import io.swagger.annotations.Api;
import io.swagger.v3.oas.annotations.Operation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

/**
 * @description: 响应式访问请求
 * @author: Jay
 * @create: 2021-09-09 17:33
 **/
@Slf4j
@Api(tags = "Demo响应式web接口")
@RequestMapping("/demo/flux-web")
@RestController
public class FluxController {

    @Operation(summary = "阻塞式")
    @GetMapping("/accomplishFluxWeb1")
    public String accomplishFluxWeb1() {
        System.out.println("开始");
        String result = getResult();
        System.out.println("结束");
        return result;
    }

    @Operation(summary = "响应式")
    @GetMapping("/accomplishFluxWeb2")
    public Mono<String> accomplishFluxWeb2() {
        System.out.println("开始");
        Mono result = Mono.create(sink -> getResult());/**create先出来再响应*/
        System.out.println("结束");
        return result;
    }

    private String getResult() {
        try {
            Thread.sleep(4000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return "左手一杯~咖啡!";
    }
}
