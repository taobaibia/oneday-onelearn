package com.shenzhijie.loguserinfo;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.conditions.query.LambdaQueryChainWrapper;
import com.shenzhijie.loguserinfo.web.base.entity.Registered;
import com.shenzhijie.loguserinfo.web.base.entity.ShenTestTable;
import com.shenzhijie.loguserinfo.web.mapper.ShenTestTableMapper;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.BiPredicate;

@SpringBootTest
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
        shenTestTable.setName("??????");
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
        shenTestTable.setName("??????");

        maps.put("name", shenTestTable.getName());
        QueryWrapper<ShenTestTable> wrapper = new QueryWrapper<>();
        //?????????Key???????????????name?????????????????????
        BiPredicate<String, Object> filter = (s, o) -> !s.equals("name");
        //??????map????????????????????????
        wrapper.allEq(filter, maps, false);
        String name = (String) maps.get("name");
        //????????????
        if (name != null) wrapper.like("name", name);
        List<ShenTestTable> list = shenTestTableMapper.selectList(wrapper);
        list.forEach(System.out::println);

    }

    @Test
    public void queryWapperLike() {
        /**????????????*/
        QueryWrapper<ShenTestTable> wrapper = new QueryWrapper<>();
        ShenTestTable shenTestTable = new ShenTestTable();
        shenTestTable.setName("???");
        wrapper.lambda()
                .like(ShenTestTable::getName, shenTestTable.getName())
        ;
        List<ShenTestTable> selectList = shenTestTableMapper.selectList(wrapper);
        System.out.println("================????????????====================");
        System.out.println(selectList.size());
        selectList.forEach(System.out::println);
        /**???????????????*/
        QueryWrapper<ShenTestTable> wrapperRight = new QueryWrapper<>();
        wrapperRight.lambda()
                .likeRight(ShenTestTable::getName, shenTestTable.getName())
        ;
        List<ShenTestTable> selectListRight = shenTestTableMapper.selectList(wrapperRight);
        System.out.println("===================?????????=================");
        System.out.println(selectListRight.size());
        selectListRight.forEach(System.out::println);
        /**???????????????*/
        QueryWrapper<ShenTestTable> wrapperLift = new QueryWrapper<>();
        wrapperLift.lambda()
                .likeLeft(ShenTestTable::getName, shenTestTable.getName())
        ;
        List<ShenTestTable> selectListLift = shenTestTableMapper.selectList(wrapperLift);
        System.out.println("==================?????????==================");
        System.out.println(selectListRight.size());
        selectListLift.forEach(System.out::println);
    }

    @Test
    public void selectLike() {
        ShenTestTable shenTestTable = new ShenTestTable();
        QueryWrapper<ShenTestTable> wrapper = new QueryWrapper<>();
        wrapper.lambda()
                .gt(ShenTestTable::getId, "2")
                .like(ShenTestTable::getName, "???")
        ;

        List<ShenTestTable> employeeList = new LambdaQueryChainWrapper<ShenTestTable>(shenTestTableMapper)
                .gt(ShenTestTable::getId, "222")
                .like(ShenTestTable::getName, "???")
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
}
