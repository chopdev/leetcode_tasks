public class Main {

    public static void main(String[] args) {
        Solution solution = new Solution();
        int res = solution.reverseBits(0b0000_0000_0000_0000_0000_0000_0000_1101);

        System.out.println(res);
        System.out.println(Integer.toBinaryString(res));
    }
}
