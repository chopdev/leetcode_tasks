import java.util.*;

public class Main {

    public static void main(String[] args) {
	    Solution sol = new Solution();

	    int[] arr1 = new int[] {1,1,2};
        int[] arr2 = new int[] {0,0,1,1,1,2,2,3,3,4};
        int[] arr3 = new int[] {1,2,4, 5, 6, 10};

        int res0 = sol.removeDuplicates(arr1);
	    int res1 = sol.removeDuplicates(arr2);
        int res2 = sol.removeDuplicates(arr3);


        // tests
        PriorityQueue<Integer> q = new PriorityQueue<>(Comparator.reverseOrder());
        PriorityQueue<Integer> qq = new PriorityQueue<>();
        qq.add(10);
        qq.add(15);
        qq.add(5);
        int lol1= qq.peek();

        List<int[]> temp = new ArrayList<>();
        temp.add(new int[] {1, 12});
        temp.add(new int[] {2, 10});
        Collections.sort(temp, new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                return Integer.compare(o1[1], o2[1]);
            }
        });


        Character.cha
    }
}
