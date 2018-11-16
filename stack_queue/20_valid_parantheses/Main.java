public class Main {

    public static void main(String[] args) {
        Solution s = new Solution();
        boolean t1 = s.isValid("{[]}");
        boolean t2 = s.isValid("([)]");
        boolean t3 = s.isValid("))");
    }
}
