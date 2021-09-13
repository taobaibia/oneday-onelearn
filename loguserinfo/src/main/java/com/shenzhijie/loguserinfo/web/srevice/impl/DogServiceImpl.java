package com.shenzhijie.loguserinfo.web.srevice.impl;

import com.shenzhijie.loguserinfo.web.base.entity.rxJava.Dog;
import com.shenzhijie.loguserinfo.web.srevice.DogService;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;

import java.util.HashMap;
import java.util.Map;

/**
 * @description: 响应式service
 * @author: Jay
 * @create: 2021-09-09 18:41
 **/
@Service
public class DogServiceImpl implements DogService {
    @Override
    public Map<Integer, Flux<Dog>> factoryDog() {
        Map<Integer, Dog> map1 = new HashMap<>();
        Map<Integer, Flux<Dog>> map2 = new HashMap<>();
        for (int i = 0; i < 10; i++) {
            Dog dog = new Dog();
            dog.setId(1);
            dog.setName("摩纳哥");
            dog.setAge(14);
            dog.setColor("red");
            map1.put(i, dog);
        }
        Flux<Dog> dogs = getDogs(map1);
        map2.put(1, dogs);
        return map2;
    }

    public Flux<Dog> getDogs(Map map) {
        return Flux.fromIterable(map.values());
    }
}
