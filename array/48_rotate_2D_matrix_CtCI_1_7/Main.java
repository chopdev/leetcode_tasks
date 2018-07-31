import java.util.Arrays;

public class Main {

    public static void main(String[] args) {
	    Solution s = new Solution();

	    int[][] arr1 = new int[][] { {1, 2, 3}, {4,5,6}, {7, 8, 9}};
        int[][] arr2 = new int[][] { {1, 2, 3, 4}, {5,6,7,8}, {9,10,11,12}, {13,14,15,16}};
        int[][] arr3 = new int[][] { {1} };

        s.rotate(arr1);
        s.rotate(arr2);
        s.rotate(arr3);

	    System.out.println(Arrays.deepToString(arr1));
        System.out.println(Arrays.deepToString(arr2));
        System.out.println(Arrays.deepToString(arr3));
    }
}
