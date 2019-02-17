public class Main {

    public static void main(String[] args) {
	    Solution sol = new Solution();

        System.out.println(Integer.MAX_VALUE);
        System.out.println(Integer.MIN_VALUE);
        System.out.println(Integer.toString(Integer.MAX_VALUE).compareTo("2147483648"));
        System.out.println(Integer.toString(Integer.MIN_VALUE).compareTo("-2147483649"));


        int res0 = sol.myAtoi(" ");

	    int res1 = sol.myAtoi("23");
        int res2 = sol.myAtoi("023");
        int res3 = sol.myAtoi("  -23  ");
        int res33 = sol.myAtoi("+23");
        int res4 = sol.myAtoi(" w 23");
        int res5 = sol.myAtoi("- 23");
        int res55 = sol.myAtoi("--23");
        int res6 = sol.myAtoi("23m,nkn2");
        int res7 = sol.myAtoi("9999999999999999999999999999999999");
        int res8 = sol.myAtoi("-1111111111111111111111111111111");
        int res9 = sol.myAtoi("-2147483641");
    }
}
