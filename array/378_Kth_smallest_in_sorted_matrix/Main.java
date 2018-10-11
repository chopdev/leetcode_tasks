public class Main {

    public static void main(String[] args) {
	    Solution s = new Solution();

        int[][] arr = new int[4][4];
        arr[0] = new int[] {1, 6, 9, 10};
        arr[1] = new int[] {5, 7, 11, 13};
        arr[2] = new int[] {12, 13, 15, 15};
        arr[3] = new int[] {15, 15, 16, 16};
        int res = s.kthSmallest3333(arr, 8);

        int[][] arr1 = new int[1][1];
        arr1[0] = new int[] {2000000000};
        int res1 = s.kthSmallest3333(arr1, 1);


        int[][] arr2 = new int[2][2];
        arr2[0] = new int[] {1,2};
        arr2[1] = new int[] {1,3};
        int res2 = s.kthSmallest3333(arr2, 3);
    }
}
