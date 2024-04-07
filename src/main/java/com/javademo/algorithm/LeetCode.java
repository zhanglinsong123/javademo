package com.javademo.algorithm;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

/**
 * @Program: javademo
 * @Description: 力扣算法题
 * @Author: zls
 * @Date: 2024-04-07 10:18
 **/
@Slf4j
public class LeetCode {

    /**
     * 两数之和
     * 给定一个整数数组 nums 和一个整数目标值 target，请你在该数组中找出 和为目标值 target  的那 两个 整数，并返回它们的数组下标。
     */
    @Test
    public void twoSum() {
        int[] nums = {1, 2, 3};
        int target = 5;
        int[] res = new int[2];

        // 创建一个哈希表，用于存储每个元素的值及其对应的索引
        HashMap<Integer, Integer> map = new HashMap<>();

        // 遍历数组
        for (int i = 0; i < nums.length; i++) {
            // 计算当前元素与目标值的差值
            int complement = target - nums[i];

            // 检查差值是否在哈希表中
            if (map.containsKey(complement)) {
                // 如果存在，则返回差值对应的索引和当前元素的索引
                res = new int[] { map.get(complement), i };
            }

            // 将当前元素及其索引加入哈希表中
            map.put(nums[i], i);
        }

        //双层循环暴力解法
//        for (int i = 0; i < nums.length; i++) {
//            for (int j = i + 1; j < nums.length; j++) {
//                if (nums[i] + nums[j] == target) {
//                    res[0] = i;
//                    res[1] = j;
//                }
//            }
//        }
        log.info("res: {}", res);
    }

    /**
     * 全排列
     * 给定一个不含重复数字的数组 nums ，返回其 所有可能的全排列 。你可以 按任意顺序 返回答案。
     * 这段代码定义了一个 permute方法（用于生成数组的所有可能的全排列的方法）来生成所有可能的全排列。在 permute 方法中，使用了回溯算法。
     * 回溯算法的核心是在尝试所有可能的选择之后进行回退，以便继续探索其他可能性。
     * 通过递归调用 backtrack 方法，不断尝试每个元素的放置位置，并记录已经使用过的元素，直到生成完整的排列。
     */
    @Test
    public void permute() {
        int[] nums = {1, 2, 3};
        List<List<Integer>> result = new ArrayList<>();
        LinkedList<Integer> permutation = new LinkedList<>();
        boolean[] used = new boolean[nums.length];
        backtrack(nums,  permutation, used, result);
        log.info("result: {}", result);
    }

    private void backtrack(int[] nums, List<Integer> permutation, boolean[] used, List<List<Integer>> result) {
        // 如果当前排列的长度等于数组长度，则将其加入结果列表中
        if (permutation.size() == nums.length) {
            result.add(new ArrayList<>(permutation));
            return;
        }

        // 遍历数组中的每个元素
        for (int i = 0; i < nums.length; i++) {
            // 如果当前元素已经被使用过，则跳过
            if (used[i]) continue;

            // 将当前元素加入排列中，并标记为已使用
            permutation.add(nums[i]);
            used[i] = true;

            // 递归生成下一个排列
            backtrack(nums, permutation, used, result);

            // 回溯，将当前元素移出排列，并标记为未使用
            permutation.remove(permutation.size() - 1);
            used[i] = false;
        }
    }


}
