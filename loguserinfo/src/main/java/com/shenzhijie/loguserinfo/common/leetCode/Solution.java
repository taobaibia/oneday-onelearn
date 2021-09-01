package com.shenzhijie.loguserinfo.common.leetCode;

import java.util.HashMap;
import java.util.Map;

/**
 * All rights Reserved, Designed By www.info4z.club
 * <p>title:com.shenzhijie.loguserinfo.common.leetCode</p>
 * <p>ClassName:Solution</p>
 * <p>Description:TODO(leetCode两数之和)</p>
 * <p>Compony:Info4z</p>
 * author:zhijieShen
 * date:2021/8/17
 * version:1.0
 * 注意：本内容仅限于公司内部传阅，禁止外泄以及用于其他的商业目的
 */
public class Solution {
    public int[] twoSum(int[] nums, int target) {
        nums = new int[]{1, 2, 3, 4, 5, 6, 7, 8};
        target = 13;
        Map<Integer, Integer> hashTable = new HashMap<>();
        for (int i = 0; i < nums.length; ++i) {
            if (hashTable.containsKey(target - nums[i])) {
                return new int[]{hashTable.get(target - nums[i]), i};
            }
            hashTable.put(nums[i], i);
        }
        return new int[0];
    }
}
