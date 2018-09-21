public class Main {

    public static void main(String[] args) {
	    Solution s = new Solution();
        int res0 = s.lengthOfLongestSubstring("pwwkew");
        int res1 = s.lengthOfLongestSubstring("abcabcbb"); // abc, 3
        int res2 = s.lengthOfLongestSubstring("www"); // w, 1
        int res3 = s.lengthOfLongestSubstring("abcabcbb"); // abc, 3
        int res4 = s.lengthOfLongestSubstring("pwwkew"); // wke, 3
    }
}
