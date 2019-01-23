public class Main {

    public static void main(String[] args) {

        char[][] arr1 = new char[3][];

        arr1[0] = new char[]{'1', '1', '1'};
        arr1[1] = new char[]{'1', '1', '1'};
        arr1[2] = new char[]{'1', '0', '1'};

        Solution solution1 = new Solution();
        int countss = solution1.numIslands2222(arr1);


	    char[][] arr = new char[4][];

	    arr[0] = new char[] {'1', '1','1','1','0'};
        arr[1] = new char[] {'1', '1','0','1','0'};
        arr[2] = new char[] {'1', '1','0','0','0'};
        arr[3] = new char[] {'0', '0','0','0','0'};

        Solution sol = new Solution();
        int count1 = sol.numIslands2222(arr);
    }
}
