public class Main {

    public static void main(String[] args) {
	    Solution s = new Solution();

	    String t2 = s.shorthand("a");
        String t3 = s.shorthand("Aa");
        String t4 = s.shorthand("aabbbcC");
        String t5 = s.shorthand("");
        String t6 = s.shorthand(null);
        String t7 = s.shorthand("abcpdba");

        System.out.println(t2);
        System.out.println(t3);
        System.out.println(t4);
        System.out.println(t5);
        System.out.println(t6);
        System.out.println(t7);

    }
}
