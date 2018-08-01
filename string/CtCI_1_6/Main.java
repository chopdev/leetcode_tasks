import java.util.Arrays;

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

        char[] ch0 = new char[] { 'a', 'b', 'c' };
        char[] ch1 = new char[] { 'a', 'a', 'a', 'b'};
        char[] ch2 = new char[] { 'a', 'a', 'a', 'b', 'b', 'b'};
        char[] ch3 = new char[] { 'a', 'b', 'b', 'a'};
        char[] ch4 = new char[] { 'a', 'a', 'a', 'a', 'a', 'a','a', 'a', 'a'};
        char[] ch5 = new char[] { 'a', 'a', 'a', 'b', 'b', 'b', 'c'};
        s.compress(ch0);
        s.compress(ch1);
        s.compress(ch2);
        s.compress(ch3);
        s.compress(ch4);
        s.compress(ch5);
        System.out.println(Arrays.toString(ch0));
        System.out.println(Arrays.toString(ch1));
        System.out.println(Arrays.toString(ch2));
        System.out.println(Arrays.toString(ch3));
        System.out.println(Arrays.toString(ch4));
        System.out.println(Arrays.toString(ch5));
    }
}
