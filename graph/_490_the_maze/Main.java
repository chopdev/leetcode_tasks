public class Main {
    public static void main(String[] args) {
        Solution sol = new Solution();

       // int[][] maze = {{0,0,1,0,0}, {0,0,0,0,0}, {0,0,0,1,0}, {1,1,0,1,1}, {0,0,0,0,0}};
        //System.out.println(sol.hasPath(maze, new int[] {0, 4}, new int[] {4,4}));

        int[][] maze = {{0,0,0,0,1,0,0}, {0,0,1,0,0,0,0}, {0,0,0,0,0,0,0}, {0,0,0,0,0,0,1}, {0,1,0,0,0,0,0}, {0,0,0,1,0,0,0}, {0,0,0,0,0,0,0}, {0,0,1,0,0,0,1}, {0,0,0,0,1,0,0}};
        System.out.println(sol.hasPath(maze, new int[] {0, 0}, new int[] {8, 6}));
    }
}
