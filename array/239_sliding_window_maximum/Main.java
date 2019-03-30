public class Main {

    public static void main(String[] args) {
	    Solution sol = new Solution();
        int[] res = sol.maxSlidingWindow2222(new int[] {1,3,-1,-3,5,3,6,7}, 3);

        int[] res1 = sol.maxSlidingWindow2222(new int[] {1,3,-1,-3,5,3,6,7}, 1);
        int[] res2 = sol.maxSlidingWindow2222(new int[] {1,3,-1,-3,5,3,6,7}, 8);


        int[] res3 = sol.maxSlidingWindow2222(new int[] {1,3,1,2,0,5}, 3);  // [3,3,2,5]
    }
}
