public class Main {

    public static void main(String[] args) {
	    Solution sol =  new Solution();
        int res = sol.trap(new int[] {0,1,0,2,1,0,1,3,2,1,2,1});

        // failed tests
        int res2 = sol.trap(new int[] {5, 4, 1, 2});
        int res3 = sol.trap(new int[] {5,2,1,2,1,5});
    }
}
