public class Main {

    public static void main(String[] args) {
	    Solution solution = new Solution();
        int[][] pages = new int[5][];
        pages[0] = new int[] {1, 2, 3};
        pages[1] = new int[] {4, 5};
        pages[2] = new int[] {6, 7};
        pages[3] = new int[] {8, 9};
        pages[4] = new int[] {10, 11};
        boolean res1 = solution.siteIsGood(pages);
    }
}
