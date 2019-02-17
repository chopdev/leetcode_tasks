public class Main {

    public static void main(String[] args) {
        Solution sol = new Solution();

        /*
        [
              ['A','B','C','E'],
              ['S','F','C','S'],
              ['A','D','E','E']
            ]
        * */

        char[][] arr = new char[3][];
        arr[0] = new char[] {'A','B','C','E'};
        arr[1] = new char[] {'S','F','C','S'};
        arr[2] = new char[] {'A','D','E','E'};

        boolean res1 = sol.exist(arr, "ABCCED");
        boolean res2 = sol.exist(arr, "SEE");
        boolean res3 = sol.exist(arr, "ABCB");


        // failed test
        char[][] arr2 = new char[3][];
        arr2[0] = new char[] {'A','B','C','E'};
        arr2[1] = new char[] {'S','F','E','S'};
        arr2[2] = new char[] {'A','D','E','E'};

        boolean res4 = sol.exist(arr2, "ABCESEEEFS");
    }
}
