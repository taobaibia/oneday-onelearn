package com.shenzhijie.loguserinfo.web.srevice;

import com.shenzhijie.loguserinfo.web.base.entity.rxJava.Dog;
import reactor.core.publisher.Flux;

import java.util.Map;

/**
 * @description:
 * @author: Jay
 * @create: 2021-09-09 18:39
 **/
public interface DogService {
    Map<Integer, Flux<Dog>> factoryDog();
}
