import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;

public class Main {

    public static void main(String[] args) {
        PriorityQueue<Integer> q = new PriorityQueue<>();
        q.add(5);
        q.add(10);
        q.add(2);
        q.add(12);
        q.add(1);
        q.add(0);
        q.add(100);

        int r1 = q.peek();
        q.offer(101);
        int r2 = q.poll();
        int r3 = q.element();


        Solution s = new Solution();
        int[][] res = s.getClosestHotels(new int[][] { {40, 40}, {1, 2}, {2, 2}, {30, 2}, {1, 90}, {5, 6}, {22, 22} }, 4);
        int[][] res2 = s.getClosestHotels2222(new int[][] { {40, 40}, {1, 2}, {2, 2}, {30, 2}, {1, 90}, {5, 6}, {22, 22} }, 4);
        System.out.println(Arrays.deepToString(res));
        System.out.println(Arrays.deepToString(res2));
    }
}
