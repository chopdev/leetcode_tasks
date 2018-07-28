import java.util.Arrays;

public class Main {

    public static void main(String[] args) {
	    Solution s = new Solution();

	    int[][] arr1 = new int[][] { {1, 2, 3}, {4,5,6}, {7, 8, 9}};
        int[][] arr2 = new int[][] { {1, 2, 3}, {4,5,6} };
        int[][] arr3 = new int[][] { {1, 2, 3} };
        int[][] arr4 = new int[][] { {1} };

        int[][] t1 = s.rotateClockwise(arr1);
        int[][] t2 = s.rotateClockwise(arr2);
        int[][] t3 = s.rotateClockwise(arr3);
        int[][] t4 = s.rotateClockwise(arr4);

	    System.out.println(Arrays.deepToString(t1));
        System.out.println(Arrays.deepToString(t2));
        System.out.println(Arrays.deepToString(t3));
        System.out.println(Arrays.deepToString(t4));
    }
}
