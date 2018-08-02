public class Main {

    public static void main(String[] args) {
	    Solution s = new Solution();

	    boolean t1 = s.IsPolindrome("Tact coA"); // true
        boolean t2 = s.IsPolindrome("Tact coAo"); // true
        boolean t3 = s.IsPolindrome("Tact coAa"); // false
        boolean t4 = s.IsPolindrome("Tact coAoooo"); // true
        boolean t5 = s.IsPolindrome("T act co Ab"); // false

        boolean t6 = s.IsPolindrome2("Tact coA"); // true
        boolean t7 = s.IsPolindrome2("Tact coAo"); // true
        boolean t8 = s.IsPolindrome2("Tact coAa"); // false
        boolean t9 = s.IsPolindrome2("Tact coAoooo"); // true
        boolean t10 = s.IsPolindrome2("T act co Ab"); // false
        boolean t11 = s.IsPolindrome2("cccab"); // false

	    System.out.println(t1);
        System.out.println(t2);
        System.out.println(t3);
        System.out.println(t4);
        System.out.println(t5);

        System.out.println("");

        System.out.println(t6);
        System.out.println(t7);
        System.out.println(t8);
        System.out.println(t9);
        System.out.println(t10);
        System.out.println(t11);

        System.out.println("");
        System.out.println(1 % 2);
    }
}
