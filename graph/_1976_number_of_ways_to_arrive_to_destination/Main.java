public class Main {
    public static void main(String[] args) {
        int[][] roads = new int[][] {{0,6,7},{0,1,2},{1,2,3},{1,3,3},{6,3,3},{3,5,1},{6,5,1},{2,5,1},{0,4,5},{4,6,2}};
        Solution solution = new Solution();

        System.out.println(solution.countPaths(7, roads));
    }
}