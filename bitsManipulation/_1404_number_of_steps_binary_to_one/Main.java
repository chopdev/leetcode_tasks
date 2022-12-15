public class Main {
    public static void main(String[] args) {
        Solution solution = new Solution();
        System.out.println(solution.numSteps("1101")); // 6
        System.out.println(solution.numSteps("10")); // 1
        System.out.println(solution.numSteps("0")); // 1

        // 110100
        // 11010
        // 1101
        // 1110
        // 111
        // 1000
        // 100
        // 10
        // 1
        System.out.println(solution.numSteps("110011")); // 9

    }
}
