package leetcode;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * @date: 2022-05-17
 * @author: yangniuhaojiang
 * @title: First
 * @version: 1.0
 * @description： update_version: update_date: update_author: update_note:
 */
public class First {
    public static void main(String[] args) {
        int[] nums = new int[]{2, 7, 11, 15};
        int target = 9;
        System.out.println(Arrays.toString(twoSum1(nums, target)));
    }

    public static int[] twoSum(int[] nums, int target) {
        for (int i = 0; i < nums.length; i++) {
            for (int j = 0; j < nums.length; j++) {
                if (nums[i] + nums[j] == target) {
                    return new int[]{i, j};
                }
            }
        }
        return new int[]{};
    }

    public static int[] twoSum1(int[] nums, int target) {
        Map<Integer, Integer> map = new HashMap();
        for (int i = 0; i < nums.length; i++) {
            int otherNum = target - nums[i];
            if (map.containsKey(otherNum)) {
                return new int[]{map.get(otherNum), i};
            } else {
                map.put(nums[i], i);
            }

        }
        return new int[]{};
    }
}
