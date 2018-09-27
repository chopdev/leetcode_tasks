public class Solution {
    public int getGCD(int[] numbers) {
        if(numbers == null || numbers.length == 0) return -1;

        int res = numbers[0];
        for (int i = 1; i < numbers.length; i++) {
            gcd(numbers[i], res);
        }

        return res;
    }

    // we do not check which one is bigger, cause after 1 level of recursion - a always smaller than b
    public int gcd(int a, int b) {
        if(a == 0) {
            return b;
        }

        return gcd(b%a, a);
    }
}
