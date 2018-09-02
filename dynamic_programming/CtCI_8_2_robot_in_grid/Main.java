import java.util.HashSet;

public class Main {

    public static void main(String[] args) {

        HashSet<int[]> hashSet = new HashSet<>();
        hashSet.add(new int[] {1, 2});
        hashSet.add(new int[] {1, 2});


	    Solution2 s = new Solution2();
	    int res1 = s.shortestPath(new int[][]{ {1,1,1,1}, {1,1,1,1}, {1,1,9,1}, {1,1,1,1} });

	    /*
          * 1 1 0 1
            1 0 1 0
            1 0 9 0
            1 1 1 1
	    * */
        int res2 = s.shortestPath(new int[][]{ {1,1,0,1}, {1,0,1,0}, {1,0,9,0}, {1,1,1,1} });

        /*
          * 1 1 1 1
            1 1 1 1
            1 0 0 0
            1 0 9 1
	    * */

        int res3 = s.shortestPath(new int[][]{ {1,1,1,1}, {1,1,1,1}, {1,0,0,0}, {1,0,9,1} });

        System.out.println(res1);
        System.out.println(res2);
        System.out.println(res3);
    }
}
