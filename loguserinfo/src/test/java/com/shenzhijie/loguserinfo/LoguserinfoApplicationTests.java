package com.shenzhijie.loguserinfo;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.conditions.query.LambdaQueryChainWrapper;
import com.shenzhijie.loguserinfo.web.base.entity.other.Registered;
import com.shenzhijie.loguserinfo.web.base.entity.other.ShenTestTable;
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
}
