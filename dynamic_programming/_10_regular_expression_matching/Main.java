public class Main {
    public static void main(String[] args) {
        Solution sol = new Solution();
        System.out.println(sol.isMatch22222("aa", "aa")); // true
        System.out.println(sol.isMatch22222("aa", ".a")); // true
        System.out.println(sol.isMatch22222("aa", "a*")); // true
        System.out.println(sol.isMatch22222("ab", ".*")); // true
        System.out.println(sol.isMatch22222("abc", ".*c")); // true
        System.out.println(sol.isMatch22222("c", ".*c")); // true
        System.out.println(sol.isMatch22222("abc", "a*b*c*")); // true
        System.out.println(sol.isMatch22222("dk", "da*b*c*.")); // true
        System.out.println(sol.isMatch22222("dk", "da*b*c*.")); // true
        System.out.println(sol.isMatch22222("aaaaaak", "a*b*c*.")); // true

        System.out.println(sol.isMatch22222("ab", "ac")); // false
        System.out.println(sol.isMatch22222("l", "*c")); // false
        System.out.println(sol.isMatch22222("aaaaaakb", "a*b*c*.")); // false

        System.out.println(sol.isMatch22222("aabcbcbcaccbcaabc", ".*a*aa*.*b*.c*.*a*"));
    }
}
