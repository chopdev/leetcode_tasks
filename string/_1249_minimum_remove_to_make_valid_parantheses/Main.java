public class Main {
    public static void main(String[] args) {
        Solution sol = new Solution();
        System.out.println(sol.minRemoveToMakeValid("(a(b(c)d)"));
        System.out.println(sol.minRemoveToMakeValid("lee(t(c)o)de)"));
        System.out.println(sol.minRemoveToMakeValid("a)b(c)d"));
        System.out.println(sol.minRemoveToMakeValid("))(("));

        //Initially failed for "())()((("
        System.out.println(sol.minRemoveToMakeValid("())()((("));
    }
}
