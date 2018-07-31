import java.lang.reflect.Array;
import java.util.Arrays;

public class Main {

    public static void main(String[] args) {
	    int[][] m1 = new int[][] { {0, 1 , 1},  { 1, 1 , 1}, {1, 1, 1}};
        int[][] m2 = new int[][] { {0, 1 , 1}, { 1, 0 , 1}, {1, 1, 1}};
        int[][] m3 = new int[][] { {0, 1 , 1} };
        int[][] m4 = new int[][] { {0} };
        int[][] m5 = new int[][] { {1} };

        Solution s = new Solution();
        s.setZeroes(m1);
        s.setZeroes(m2);
        s.setZeroes(m3);
        s.setZeroes(m4);
        s.setZeroes(m5);


        System.out.println(Arrays.deepToString(m1));
        System.out.println(Arrays.deepToString(m2));
        System.out.println(Arrays.deepToString(m3));
        System.out.println(Arrays.deepToString(m4));
        System.out.println(Arrays.deepToString(m5));

    }
}
