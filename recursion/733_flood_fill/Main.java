public class Main {

    public static void main(String[] args) {
        Solution sol = new Solution();
        int[][] image = {{1,1,1}, {1, 1, 0}, {1, 0, 1}};
        sol.floodFill(image, 1, 1, 2);
    }
}
