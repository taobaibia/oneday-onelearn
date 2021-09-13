package com.shenzhijie.loguserinfo.web.srevice;

import com.baomidou.dynamic.datasource.annotation.DS;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.shenzhijie.loguserinfo.web.base.entity.other.IsipProjectBoard;
import com.shenzhijie.loguserinfo.web.base.entity.other.ShenTestTable;
import com.shenzhijie.loguserinfo.web.base.entity.other.ShenTestTableReq;
import com.shenzhijie.loguserinfo.web.base.entity.other.tbItemCat;
import com.shenzhijie.loguserinfo.web.mapper.IsipProjectBoardMapper;
import com.shenzhijie.loguserinfo.web.mapper.ShenTestTableMapper;
import com.shenzhijie.loguserinfo.web.mapper.tbItemCatMapper;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * All rights Reserved, Designed By www.info4z.club
 * <p>title:com.shenzhijie.loguserinfo.web.srevice</p>
 * <p>ClassName:LeetCodeService</p>
 * <p>Description:TODO(leetCode两数之和)</p>
 * <p>Compony:Info4z</p>
 * author:zhijieShen
 * date:2021/8/17
 * version:1.0
 * 注意：本内容仅限于公司内部传阅，禁止外泄以及用于其他的商业目的
 */
@Service
public class LeetCodeService extends ServiceImpl<IsipProjectBoardMapper, IsipProjectBoard> {
    @Autowired
    IsipProjectBoardMapper isipProjectBoardMapper;
    @Autowired
    ShenTestTableMapper shenTestTableMapper;
    @Autowired
    tbItemCatMapper tbItemCatMapper;

    /**
     * 两数之和
     */
    public int[] twoSum(int[] nums, int target) {
//        nums= new int[]{1,2,3,4,5,6,7,8};
//        target=13;
        Map<Integer, Integer> hashTable = new HashMap<>(nums.length - 1);
        for (int i = 0; i < nums.length; ++i) {
            int sum = target - nums[i];
            if (hashTable.containsKey(sum)) {
                return new int[]{hashTable.get(sum), i};
            }
            hashTable.put(nums[i], i);
        }
        return new int[0];
    }

    /**
     * 两数相加
     */
    @DS("slave_1")
    public List<IsipProjectBoard> findBoardAll(String name) {
        QueryWrapper<IsipProjectBoard> wrapper = new QueryWrapper<>();
        wrapper.lambda().eq(IsipProjectBoard::getName, name);
        return isipProjectBoardMapper.selectList(wrapper);
    }

    @DS("master")
    public List<ShenTestTableReq> findShenJayTable() {
        QueryWrapper<ShenTestTable> wrapper = new QueryWrapper<>();
        wrapper.lambda()
                .like(ShenTestTable::getName, "申")
        ;
        ShenTestTableReq req = new ShenTestTableReq();
        List<ShenTestTableReq> tableReqs = new ArrayList<>();
        List<ShenTestTable> shenTestTables = shenTestTableMapper.selectList(wrapper);
        for (ShenTestTable table : shenTestTables) {
            BeanUtils.copyProperties(table, req);
            tableReqs.add(req);
        }
        return tableReqs;
    }

    @DS("slave_2")
    public List<tbItemCat> findtbItemCat() {
        QueryWrapper<tbItemCat> wrapper = new QueryWrapper<>();
        wrapper.lambda().like(tbItemCat::getName, "包");
        return tbItemCatMapper.selectList(wrapper);
    }

}
