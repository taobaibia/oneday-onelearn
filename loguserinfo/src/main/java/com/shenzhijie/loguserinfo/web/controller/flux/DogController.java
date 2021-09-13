package com.shenzhijie.loguserinfo.web.controller.flux;

import com.shenzhijie.loguserinfo.web.base.entity.rxJava.Dog;
import com.shenzhijie.loguserinfo.web.srevice.DogService;
import io.swagger.annotations.Api;
import io.swagger.v3.oas.annotations.Operation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.Map;

/**
 * @description: 响应式访问请求   注解式 | 函数式
 * @author: Jay
 * @create: 2021-09-09 17:33
 **/
@Slf4j
@Api(tags = "响应式web接口")
@RequestMapping("/flux-web")
@RestController
public class DogController {

    @Autowired
    private DogService dogService;

    @Operation(summary = "响应式")
    @GetMapping("/accomplishFluxWeb2")
    public Mono<Map<Integer, Flux<Dog>>> accomplishFluxWeb2() {
        System.out.println("开始");
        Mono<Map<Integer, Flux<Dog>>> mono = Mono.create(sink -> {
            sink.success(dogService.factoryDog());
        });
        System.out.println("结束");
        return mono;
    }
}
