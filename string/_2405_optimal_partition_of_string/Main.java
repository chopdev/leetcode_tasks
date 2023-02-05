public class Main {
    public static void main(String[] args) {
        Solution sol = new Solution();

        System.out.println(sol.partitionString("abcdabklm")); // 2
        System.out.println(sol.partitionString("abca")); // 2
        System.out.println(sol.partitionString("acababc")); // 3

        System.out.println(sol.partitionString("abacaba")); // 4
    }
}
