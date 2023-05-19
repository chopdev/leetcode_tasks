import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;

/*Not my
* https://leetcode.com/problems/first-unique-number/solutions/601098/c-java-queue-unordered-map-o-n-solution/
* */
public class FirstUnique2 {
    Map<Integer, Integer> freq = new HashMap<>();
    Queue<Integer> q = new LinkedList<>();
    public FirstUnique2(int[] nums) {
        for (int x : nums)
            add(x);
    }

    // Amotrized O(1), but not constant
    public int showFirstUnique() {
        while (!q.isEmpty() && freq.get(q.peek()) > 1)
            q.poll();
        return q.isEmpty() ? -1 : q.peek();
    }
    public void add(int value) {
        freq.put(value, freq.getOrDefault(value, 0) + 1);
        q.offer(value);
    }
}
