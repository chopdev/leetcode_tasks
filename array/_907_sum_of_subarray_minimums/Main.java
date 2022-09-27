public class Main {

    public static void main(String[] args) {
        Solution sol = new Solution();
        System.out.println(sol.sumSubarrayMins(new int[] {5})); // 5
        System.out.println(sol.sumSubarrayMins(new int[] {4, 5})); // 13
        System.out.println(sol.sumSubarrayMins(new int[] {5, 4}));
        System.out.println(sol.sumSubarrayMins(new int[] {3,1,2,4}));
    }
}
