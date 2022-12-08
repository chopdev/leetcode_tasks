public class Main {
    public static void main(String[] args) {
        Solution solution = new Solution();
        System.out.println(solution.longestDiverseString(1, 1, 7));
        System.out.println(solution.longestDiverseString(1, 1, 1));
        System.out.println(solution.longestDiverseString(3, 2, 1));
        System.out.println(solution.longestDiverseString(100, 2, 1));
        System.out.println(solution.longestDiverseString(100, 10, 1));
    }
}
