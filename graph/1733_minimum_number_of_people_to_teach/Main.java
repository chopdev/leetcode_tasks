public class Main {
    public static void main(String[] args) {
        Solution sol = new Solution();
        int res = sol.minimumTeachings(3,
                new int[][] {new int[] {2}, new int[] {1, 3}, new int[] {1, 2}, new int[] {3} },
                new int[][] {new int[] {1, 4}, new int[] {1, 2}, new int[] {3, 4}, new int[]{2, 3}});
        System.out.println(res);
    }
}
