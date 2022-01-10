public class Main {

    public static void main(String[] args) {
        Solution sol = new Solution();
        int[][] grid = {{1,0,0,0},{0,0,0,0},{0,0,2,-1}};

        System.out.println(sol.uniquePathsIII(grid)); // 2

        System.out.println(sol.uniquePathsIII(new int[][] {{1,0,0,0},{0,0,0,0},{0,0,0,2}})); // 4
    }
}
