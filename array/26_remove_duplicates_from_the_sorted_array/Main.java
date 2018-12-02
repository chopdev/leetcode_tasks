import java.util.Comparator;
import java.util.PriorityQueue;

public class Main {

    public static void main(String[] args) {
	    Solution sol = new Solution();

	    int[] arr1 = new int[] {1,1,2};
        int[] arr2 = new int[] {0,0,1,1,1,2,2,3,3,4};
        int[] arr3 = new int[] {1,2,4, 5, 6, 10};

        int res0 = sol.removeDuplicates(arr1);
	    int res1 = sol.removeDuplicates(arr2);
        int res2 = sol.removeDuplicates(arr3);

        PriorityQueue<Integer> q = new PriorityQueue<>(Comparator.reverseOrder());
    }
}
