public class Main {
    public static void main(String[] args) {
        Solution sol = new Solution();
        System.out.println(sol.subarrayGCD(new int[] {18,9,9,3,1,2,6,3}, 3)); // 18,9,9,3  | 9,9,3 | 9,3 | 3 | 6,3 | 3
    }
}
