public class Main {

    public static void main(String[] args) {
	    Solution s = new Solution();
	    int res1 = s.getLongestSequence(0b11);
        int res2 = s.getLongestSequence(0b11010111);
        int res3 = s.getLongestSequence(0b1101000001111);
        int res4 = s.getLongestSequence(0b1101100000111);
        System.out.println(res1);
        System.out.println(res2);
        System.out.println(res3);
        System.out.println(res4);

        System.out.println("");
        System.out.println("");

        int res5 = s.getLongestSequence(0b11);
        int res6 = s.getLongestSequence(0b11010111);
        int res7 = s.getLongestSequence(0b1101000001111);
        int res8 = s.getLongestSequence(0b1101100000111);
        System.out.println(res5);
        System.out.println(res6);
        System.out.println(res7);
        System.out.println(res8);

    }
}
