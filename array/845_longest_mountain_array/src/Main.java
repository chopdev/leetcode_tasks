import java.util.Arrays;

public class Main {

    public static void main(String[] args) {
        Solution sol = new Solution();
	    int longestMountain = sol.longestMountain(new int[] {2,1,4,7,3,2,5});

	    //[2,1,4,7,3,2,5]

	    System.out.println(longestMountain);

	    System.out.println(sol.isMountain(Arrays.asList(new Integer[] {2,1,4,7,3})));
        System.out.println(sol.isMountain(Arrays.asList(new Integer[] {2,1,4})));

        System.out.println(sol.isMountain(Arrays.asList(new Integer[] {1,4,7,3,2})));
        System.out.println(sol.isMountain(Arrays.asList(new Integer[] {1,4,7})));
    }
}
