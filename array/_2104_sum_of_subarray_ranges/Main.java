public class Main {
    public static void main(String[] args) {
        Solution solution = new Solution();
        System.out.println(solution.subArrayRanges(new int[] {1,2,3})); // 4
        System.out.println(solution.subArrayRanges(new int[] {1,3,3})); // 4
        System.out.println(solution.subArrayRanges(new int[] {4,-2,-3,4,1})); // 59
    }
}
