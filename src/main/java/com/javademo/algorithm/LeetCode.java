package com.javademo.algorithm;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.springframework.util.CollectionUtils;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

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

    /**
     * 两数相加
     * 给你两个 非空 的链表，表示两个非负的整数。它们每位数字都是按照 逆序 的方式存储的，并且每个节点只能存储 一位 数字。
     * 请你将两个数相加，并以相同形式返回一个表示和的链表。
     * 你可以假设除了数字 0 之外，这两个数都不会以 0 开头。
     */
    @Test
    public void addTwoNumbers() {
        ListNode l1 = new ListNode(2, new ListNode(4, new ListNode(3)));
        ListNode l2 = new ListNode(5, new ListNode(6, new ListNode(4)));
        ListNode dummyHead = new ListNode(0);
        ListNode current = dummyHead;
        int carry = 0;

        while (l1 != null || l2 != null) {
            int sum = carry;
            if (l1 != null) {
                sum += l1.val;
                l1 = l1.next;
            }
            if (l2 != null) {
                sum += l2.val;
                l2 = l2.next;
            }
            current.next = new ListNode(sum % 10);
            carry = sum / 10;
            current = current.next;
        }

        if (carry > 0) {
            current.next = new ListNode(carry);
        }

        ListNode listNode = dummyHead.next;
        while (listNode != null) {
            log.info(listNode.val + " ");
            listNode = listNode.next;
        }
    }

    public static class ListNode {
        int val;
        ListNode next;
        ListNode() {

        }
        ListNode(int val) {
            this.val = val;
        }
        ListNode(int val, ListNode next) {
            this.val = val;
            this.next = next;
        }
    }

    /**
     * 字母异位词分组
     * 给你一个字符串数组，请你将 字母异位词 组合在一起。可以按任意顺序返回结果列表。
     * 字母异位词 是由重新排列源单词的所有字母得到的一个新单词。
     */
    @Test
    public void groupAnagrams() {
        String[] strs = {"eat", "tea", "tan", "ate", "nat", "bat"};

        Map<String, List<String>> map = new HashMap<>();
        for (String str : strs) {
            char[] charArray = str.toCharArray();
            Arrays.sort(charArray);
            String key = String.valueOf(charArray);
            if (map.containsKey(key)) {
                List<String> list = map.get(key);
                list.add(str);
                map.put(key, list);
            } else {
                LinkedList<String> list = new LinkedList<>();
                list.add(str);
                map.put(key, list);
            }
        }

        List<List<String>> res = new ArrayList<>();
        map.forEach((k, v) -> res.add(v));


        System.out.println(res);

//        new ArrayList<>(Arrays.stream(strs).collect(Collectors.groupingBy(s -> s.chars().sorted().collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append).toString())).values());

    }


    /**
     * 最长连续序列
     * 给定一个未排序的整数数组 nums ，找出数字连续的最长序列（不要求序列元素在原数组中连续）的长度。
     * 请你设计并实现时间复杂度为 O(n) 的算法解决此问题。
     * 1. 将所有元素放入一个哈希集合中，以便快速地进行查找。
     * 2. 遍历数组中的每个元素，对于每个元素 num：
     *  2.1. 如果 num-1 不在哈希集合中，说明 num 可以作为一个序列的起始点。
     *  2.2. 从 num 开始，依次检查 num+1, num+2, ... 是否在哈希集合中，直到找到一个不在集合中的元素，记录当前序列的长度。
     *  2.3. 更新最长连续序列的长度。
     * 3. 返回最长连续序列的长度。
     */
    @Test
    public void longestConsecutive() {
//        int[] nums = {0,3,7,2,5,8,4,6,0,1};
        int[] nums = {9,1,4,7,3,-1,0,5,8,-1,6};

        HashSet<Integer> numSet = new HashSet<>();
        for (int num : nums) {
            numSet.add(num);
        }

        int longestStreak = 0;

        for (int num : numSet) {
            if (!numSet.contains(num - 1)) {
                int currentNum = num;
                int currentStreak = 1;

                while (numSet.contains(currentNum + 1)) {
                    currentNum++;
                    currentStreak++;
                }

                longestStreak = Math.max(longestStreak, currentStreak);
            }
        }


        System.out.println(longestStreak);
    }

    /**
     * 移动零
     * 给定一个数组 nums，编写一个函数将所有 0 移动到数组的末尾，同时保持非零元素的相对顺序。
     * 请注意 ，必须在不复制数组的情况下原地对数组进行操作。
     * 移动零问题是一个常见的数组操作问题，要求将数组中所有的零移动到数组的末尾，同时保持非零元素的相对顺序不变。
     * 一种简单的方法是使用双指针技巧。具体步骤如下：
     * 定义两个指针 left 和 right，初始时都指向数组的第一个元素。
     * 遍历数组，当遇到非零元素时，将其移到数组的左边（即指针 left 所指的位置），并且指针 left 向右移动一位。
     * 遍历结束后，所有的非零元素都被移动到了数组的前面，而指针 left 所指的位置及其之后的位置都应该填充零。
     *
     */
    @Test
    public void moveZeroes() {
        int[] nums = {0, 1, 0, 3, 12};
        int left = 0; // 左指针，指向非零元素应该放置的位置
        for (int right = 0; right < nums.length; right++) { // 右指针，遍历数组中的每个元素
            if (nums[right] != 0) { // 如果当前元素不为零
                // 将当前非零元素移到左指针所指的位置，并且左指针右移一位
                nums[left] = nums[right];
                left++;
            }
        }
        // 左指针及其之后的位置填充零
        for (int i = left; i < nums.length; i++) {
            nums[i] = 0;
        }
        System.out.println(Arrays.toString(nums));
    }

    /**
     * 盛最多水的容器
     * 给定一个长度为 n 的整数数组 height 。有 n 条垂线，第 i 条线的两个端点是 (i, 0) 和 (i, height[i]) 。
     * 找出其中的两条线，使得它们与 x 轴共同构成的容器可以容纳最多的水。
     * 返回容器可以储存的最大水量。
     * 说明：你不能倾斜容器。
     * 解题思路：
     * 1. 定义两个指针 left 和 right，分别指向数组的起始位置和结束位置。
     * 2. 计算当前容器的容量，即 min(height[left], height[right]) * (right - left)，其中 height[left] 和 height[right] 分别是指针 left 和 right 所指向的元素的高度，(right - left) 是两个指针之间的距离。
     * 3. 如果当前容器的容量大于之前记录的最大容量，则更新最大容量。
     * 4. 移动指针：如果 height[left] < height[right]，则向右移动指针 left；否则向左移动指针 right。
     * 5. 重复步骤 2-4，直到两个指针相遇。
     */
    @Test
    public void maxArea() {
        int[] height = {1, 8, 6, 2, 5, 4, 8, 3, 7};
        int maxArea = 0; // 初始化最大容量
        int left = 0; // 左指针，指向数组的起始位置
        int right = height.length - 1; // 右指针，指向数组的结束位置

        while (left < right) { // 当左指针小于右指针时循环
            int currentArea = Math.min(height[left], height[right]) * (right - left); // 计算当前容器的容量
            maxArea = Math.max(maxArea, currentArea); // 更新最大容量
            // 移动指针：如果 height[left] < height[right]，则向右移动左指针；否则向左移动右指针
            if (height[left] < height[right]) {
                left++;
            } else {
                right--;
            }
        }

        System.out.println(maxArea);
    }

    /**
     * 三数之和
     * 给你一个整数数组 nums ，判断是否存在三元组 [nums[i], nums[j], nums[k]] 满足 i != j、i != k 且 j != k ，同时还满足 nums[i] + nums[j] + nums[k] == 0 。请
     * 你返回所有和为 0 且不重复的三元组。
     * 注意：答案中不可以包含重复的三元组。
     *
     * 解题思路：
     * 1. 首先对数组进行排序，这样可以使得相同的数字相邻，便于处理。
     * 2. 遍历数组，对于每个数字 nums[i]，将其作为三个数中的第一个数，并定义左右两个指针分别指向 i+1 和数组末尾。
     * 3. 在固定了第一个数后，使用双指针技巧在剩余的区间内寻找另外两个数，使得三个数之和为零。
     *   3.1. 如果三个数之和等于零，则将这个三元组添加到结果中。
     *   3.2. 如果三个数之和小于零，则左指针右移。
     *   3.3. 如果三个数之和大于零，则右指针左移。
     * 4. 在移动指针时，要注意跳过重复的数字，以避免产生重复的三元组。
     */
    @Test
    public void threeSum() {
        int[] nums = {-2,0,0,2,2};
        List<List<Integer>> result = new ArrayList<>();
        Arrays.sort(nums); // 首先对数组进行排序

        for (int i = 0; i < nums.length - 2; i++) {
            // 跳过重复的数字
            if (i > 0 && nums[i] == nums[i - 1]) {
                continue;
            }
            int left = i + 1;
            int right = nums.length - 1;
            while (left < right) {
                int sum = nums[i] + nums[left] + nums[right];
                if (sum == 0) {
                    result.add(Arrays.asList(nums[i], nums[left], nums[right]));
                    // 跳过重复的数字
                    while (left < right && nums[left] == nums[left + 1]) {
                        left++;
                    }
                    while (left < right && nums[right] == nums[right - 1]) {
                        right--;
                    }
                    left++;
                    right--;
                } else if (sum < 0) {
                    left++;
                } else {
                    right--;
                }
            }
        }

        System.out.println(result);
    }

}
