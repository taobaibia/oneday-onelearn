package com.shenzhijie.loguserinfo;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.conditions.query.LambdaQueryChainWrapper;
import com.shenzhijie.loguserinfo.web.base.entity.other.Registered;
import com.shenzhijie.loguserinfo.web.base.entity.other.ShenTestTable;
import com.shenzhijie.loguserinfo.web.mapper.ShenTestTableMapper;
import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.Observer;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import reactor.core.publisher.Flux;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.BiPredicate;
import java.util.stream.Stream;

//@SpringBootTest
@Slf4j
class LoguserinfoApplicationTests {
    @Autowired
    private ShenTestTableMapper shenTestTableMapper;

    @Test
    public void ARMybatisPlus() {
        Registered registered = new Registered();
        registered.insertOrUpdate();
        Registered result = registered.selectById(1);
        System.out.println(result);
    }

    @Test
    public void stringUtilsNUll() {
        String s = "";
        String s1 = " ";
        String s2 = " a";
        String s3 = "abc";
        String s4 = null;
        System.out.println("StringUtils.isBlank(s):" + StringUtils.isBlank(s));       //true
        System.out.println("StringUtils.isBlank(s1):" + StringUtils.isBlank(s1));     //true
        System.out.println("StringUtils.isBlank(s2):" + StringUtils.isBlank(s2));     //false
        System.out.println("StringUtils.isBlank(s3):" + StringUtils.isBlank(s3));     //false
        System.out.println("StringUtils.isBlank(s4):" + StringUtils.isBlank(s4));     //true
        System.out.println("StringUtils.isNoneBlank(s):" + StringUtils.isNoneBlank(s));
        System.out.println("StringUtils.isNoneBlank(s1):" + StringUtils.isNoneBlank(s1));
        System.out.println("StringUtils.isNoneBlank(s2):" + StringUtils.isNoneBlank(s2));
        System.out.println("StringUtils.isNoneBlank(s3):" + StringUtils.isNoneBlank(s3));
        System.out.println("StringUtils.isNoneBlank(s4):" + StringUtils.isNoneBlank(s4));
    }

    @Test
    public void QueryWrapperTest() {
        String id = "12";
        Map<String, Object> map = new HashMap<>();
        ShenTestTable shenTestTable = new ShenTestTable();
        shenTestTable.setBoardId(1L);
        shenTestTable.setName("张三");
        map.put("name", shenTestTable.getName());
//        map.put("id",shenTestTable.getBoardId());
        QueryWrapper<ShenTestTable> wrapper = new QueryWrapper<>();
        wrapper.allEq(map);

        List<ShenTestTable> selectList = shenTestTableMapper.selectList(wrapper);
        selectList.forEach(System.out::println);


    }

    @Test
    public void selectCourseClassificationList() {

        Map<String, Object> maps = new HashMap<>();
        ShenTestTable shenTestTable = new ShenTestTable();
        shenTestTable.setName("张三");

        maps.put("name", shenTestTable.getName());
        QueryWrapper<ShenTestTable> wrapper = new QueryWrapper<>();
        //只有当Key值不等于“name”的时候才放行
        BiPredicate<String, Object> filter = (s, o) -> !s.equals("name");
        //根据map参数进行条件查询
        wrapper.allEq(filter, maps, false);
        String name = (String) maps.get("name");
        //模糊查询
        if (name != null) wrapper.like("name", name);
        List<ShenTestTable> list = shenTestTableMapper.selectList(wrapper);
        list.forEach(System.out::println);

    }

    @Test
    public void queryWapperLike() {
        /**模糊查询*/
        QueryWrapper<ShenTestTable> wrapper = new QueryWrapper<>();
        ShenTestTable shenTestTable = new ShenTestTable();
        shenTestTable.setName("三");
        wrapper.lambda()
                .like(ShenTestTable::getName, shenTestTable.getName())
        ;
        List<ShenTestTable> selectList = shenTestTableMapper.selectList(wrapper);
        System.out.println("================模糊查询====================");
        System.out.println(selectList.size());
        selectList.forEach(System.out::println);
        /**模糊右查询*/
        QueryWrapper<ShenTestTable> wrapperRight = new QueryWrapper<>();
        wrapperRight.lambda()
                .likeRight(ShenTestTable::getName, shenTestTable.getName())
        ;
        List<ShenTestTable> selectListRight = shenTestTableMapper.selectList(wrapperRight);
        System.out.println("===================右查询=================");
        System.out.println(selectListRight.size());
        selectListRight.forEach(System.out::println);
        /**模糊左查询*/
        QueryWrapper<ShenTestTable> wrapperLift = new QueryWrapper<>();
        wrapperLift.lambda()
                .likeLeft(ShenTestTable::getName, shenTestTable.getName())
        ;
        List<ShenTestTable> selectListLift = shenTestTableMapper.selectList(wrapperLift);
        System.out.println("==================左查询==================");
        System.out.println(selectListRight.size());
        selectListLift.forEach(System.out::println);
    }

    @Test
    public void selectLike() {
        ShenTestTable shenTestTable = new ShenTestTable();
        QueryWrapper<ShenTestTable> wrapper = new QueryWrapper<>();
        wrapper.lambda()
                .gt(ShenTestTable::getId, "2")
                .like(ShenTestTable::getName, "亮")
        ;

        List<ShenTestTable> employeeList = new LambdaQueryChainWrapper<ShenTestTable>(shenTestTableMapper)
                .gt(ShenTestTable::getId, "222")
                .like(ShenTestTable::getName, "亮")
                .list();
        System.out.println("employeeList:   " + employeeList);
        employeeList.forEach(System.out::println);
    }

    public static void main(String[] args) {
        class T {
            T i = new T();
        }
        System.out.println(T.class.getClassLoader());
        System.out.println(T.class.getClassLoader().getParent());
        System.out.println(T.class.getClassLoader().getParent().getParent());
    }

    /*同步*/
    @Test
    public void testRxjava1() {
        /**Observable被观察者*/
        Observable<String> dog = Observable.create(new ObservableOnSubscribe<String>() {
            /**observableEmitter发射器,发射体*/
            @Override
            public void subscribe(@NonNull ObservableEmitter<String> observableEmitter) throws Exception {
                /**无限次使用onNext*/
                System.out.println(Thread.currentThread().getName());
                observableEmitter.onNext("1");
                Thread.sleep(3000);
                System.out.println(Thread.currentThread().getName());
                observableEmitter.onNext("2");
                Thread.sleep(3000);
                System.out.println(Thread.currentThread().getName());
                observableEmitter.onNext("3");
                Thread.sleep(3000);
                System.out.println(Thread.currentThread().getName());
                observableEmitter.onNext("4");
                Thread.sleep(3000);
                System.out.println(Thread.currentThread().getName());
                observableEmitter.onComplete();
                System.out.println(Thread.currentThread().getName());
            }
        });
        /**观察者Observer*/
        Observer<String> cat = new Observer<String>() {

            @Override
            public void onSubscribe(@NonNull Disposable disposable) {
                System.out.println("onSubscribe" + disposable);
            }

            @Override
            public void onNext(@NonNull String s) {
                System.out.println("onNext" + s);
            }

            @Override
            public void onError(@NonNull Throwable throwable) {
                System.out.println("onError" + throwable);
            }

            @Override
            public void onComplete() {
                System.out.println("onComplete");
            }
        };
        /**关联起来,观察者和被观察者*/
        dog.subscribe(cat);
    }

    /*异步*/
    @Test
    public void testRxjava2() throws InterruptedException {
        /*被观察者*/
        Observable.create(new ObservableOnSubscribe<String>() {
                    @Override
                    public void subscribe(@NonNull ObservableEmitter<String> observableEmitter) throws Exception {
                        observableEmitter.onNext("1");
                        observableEmitter.onNext("2");
                        observableEmitter.onNext("3");
                        observableEmitter.onNext("4");
                        observableEmitter.onNext("5");
                        observableEmitter.onComplete();
                    }
                })
                /*哪个线程是观察者*/
                .observeOn(
                        Schedulers.computation()
                )
                .subscribeOn(Schedulers.computation())
                .subscribe(new Observer<String>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable disposable) {
                        System.out.println("onSubscribe..." + disposable);
                    }

                    @Override
                    public void onNext(@NonNull String s) {
                        System.out.println("onNext" + s);
                    }

                    @Override
                    public void onError(@NonNull Throwable throwable) {
                        System.out.println("onError" + throwable);
                    }

                    @Override
                    public void onComplete() {
                        System.out.println("onComplete");
                    }
                })
        ;
        /*要在main中测试*/
//        Thread.sleep(10000);
    }

    /*消息和订阅*/
    @Test
    public void ReactorTest() throws Exception {
        String[] s = {"xx", "00"};
        /**flux1发布者 被观察者*/
        Flux<String> flux1 = Flux.just(s);
        /**println订阅者 观察者*/
        flux1.subscribe(System.out::println);
        System.out.println("-------------");

        /**flux1发布者 被观察者*/
        Flux<String> flux2 = Flux.just("xx", "00", "yy");
        /**println订阅者 观察者*/
        flux2.subscribe(System.out::println);
        System.out.println("--------------");

        List<String> list = new ArrayList<>();
        list.add("hello");
        list.add("world");
        Flux<String> flux3 = Flux.fromIterable(list);
        flux3.subscribe(System.out::println);
        System.out.println("---------------");

        Stream<String> stream = Stream.of("hi", "java");
        Flux<String> flux4 = Flux.fromStream(stream);
        flux4.subscribe(System.out::println);
        System.out.println("---------------");

        Flux<Integer> range = Flux.range(1, 5);
        range.subscribe(System.out::println);
        System.out.println("---------------");

        /*合并*/
        Flux<String> mergeWith = flux2.mergeWith(flux3);
        mergeWith.subscribe(System.out::println);
        System.out.println("-------同步动态创建,nest只能调用一次--------");

        /*同步动态创建,nest只能调用一次*/
        Flux.generate(sink -> {
            sink.next("xx");
            sink.complete();
        }).subscribe(System.out::println);

        System.out.println("-------可以多次--------");

        /*异步动态创建,nest可以多次*/
        Flux.create(sink -> {
            for (int i = 0; i < 1000; i++) {
                sink.next("xx" + i);
            }
            sink.complete();
        }).subscribe(System.out::println);
    }
}
