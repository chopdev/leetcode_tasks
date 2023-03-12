public class Main {
    public static void main(String[] args) {
        Solution sol = new Solution();

        System.out.println(sol.longestCommonSubsequence("abcde", "ace")); // 3
        System.out.println(sol.longestCommonSubsequence("abc", "aaabc")); // 3
        System.out.println(sol.longestCommonSubsequence("abc", "def")); // 0

    }
}
