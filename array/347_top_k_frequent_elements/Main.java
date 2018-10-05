import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;

public class Main {

    public static void main(String[] args) {


        Solution s = new Solution();
        List<Integer> res = s.topKFrequent(new int[] {1, 1, 1, 3,3,3,3,3,3,3, 4,4,4,4,2,5,6}, 2);

        for (int l : res) {
            System.out.println(l);
        }



       /* PriorityQueue<Integer> priorityQueue = new PriorityQueue<>(new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                return Integer.compare(o2, o1);
            }
        });
        priorityQueue.add(5);
        priorityQueue.add(4);
        priorityQueue.add(10);
        priorityQueue.add(20);

        int t1 = priorityQueue.poll();
        int t2 = priorityQueue.poll();*/
    }
}
