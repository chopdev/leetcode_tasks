public class Main {

    public static void main(String[] args) {
	    Solution s = new Solution();
        int res1 = s.getNextLargest(0b00100100);
        int res2 = s.getNextLargest(0b00001110);
        int res3 = s.getNextLargest(0b00001111);


        //// 0100 1110 -> bigger is 0101 0111
        /// 0001 0011 -> smaller is 0000 1011
    }
}
