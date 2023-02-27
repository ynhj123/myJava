package leetcode;

import java.util.HashMap;
import java.util.Map;

/**
 * @date: 2022-05-18
 * @author: yangniuhaojiang
 * @title: LengthOfLongestSubstring
 * @version: 1.0
 * @description： update_version: update_date: update_author: update_note:
 */
public class LengthOfLongestSubstring {
    public static void main(String[] args) {
        System.out.println(lengthOfLongestSubstring("abcd"));
    }

    public static int lengthOfLongestSubstring(String s) {
        if (s.length() == 0) return 0;
        int n = s.length();
        Map<Character, Integer> map = new HashMap<>(n);
        int max = 0;
        int left = 0;
        for (int i = 0; i < n; i++) {
            if (map.containsKey(s.charAt(i))) {
                left = Math.max(left, map.get(s.charAt(i)) + 1);
            }
            map.put(s.charAt(i), i);
            max = Math.max(max, i - left + 1);
            if (max == n) {
                break;
            }
        }
        return max;

//        // 哈希集合，记录每个字符是否出现过
//        Set<Character> occ = new HashSet<>();
//        int n = s.length();
//        // 右指针，初始值为 -1，相当于我们在字符串的左边界的左侧，还没有开始移动
//        int rk = -1, ans = 0;
//        for (int i = 0; i < n; ++i) {
//            if (i != 0) {
//                // 左指针向右移动一格，移除一个字符
//                occ.remove(s.charAt(i - 1));
//            }
//            while (rk + 1 < n && !occ.contains(s.charAt(rk + 1))) {
//                // 不断地移动右指针
//                occ.add(s.charAt(rk + 1));
//                ++rk;
//            }
//            // 第 i 到 rk 个字符是一个极长的无重复字符子串
//            ans = Math.max(ans, rk - i + 1);
//            // 当字符串完全不重复时直接返回
//            if (ans == n) {
//                break;
//            }
//        }
//        return ans;

    }
}
