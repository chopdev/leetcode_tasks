import java.util.Arrays;

public class Main {

    public static void main(String[] args) {
	    Solution s = new Solution();
	    int res = s.lengthOfLIS1(new int[] {10, 3, 4, 1, 5});
	    System.out.println(res);


	    int[] temp = new int[] {50, 10, 4};

		int r1 = Arrays.binarySearch(temp, 7);
		int r2 = Arrays.binarySearch(temp, 0);
		int r3 = Arrays.binarySearch(temp, -2);
		int r4 = Arrays.binarySearch(temp, 70);
		int r5 = Arrays.binarySearch(temp, 3);
		int r11 = Arrays.binarySearch(temp, 4);
		int r6 = Arrays.binarySearch(temp, 5);
		int r7 = Arrays.binarySearch(temp, 6);


	}
}
