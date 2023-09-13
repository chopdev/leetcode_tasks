import java.util.Deque;
import java.util.LinkedList;
import java.util.NoSuchElementException;
import java.util.Queue;

/**
 * Interview question
 *
 * Design a Data Structure which supports following operations enqueue(), deque() and getMax()
 * where getMax() operation takes O(1) time.
 * */
public class MaxQueue {
    // queue that keeps standard order of items
    Queue<Integer> queue = new LinkedList<>();
    // queue that keeps max elements. Max element is at the start
    Deque<Integer> maxDequeue = new LinkedList<>();

    // amortized O(1),
    public void enqueue(int item) {
        queue.add(item);
        if (queue.size() == 0) {
            maxDequeue.addLast(item);
        } else {
            while (!maxDequeue.isEmpty() && maxDequeue.getLast() < item) maxDequeue.removeLast(); // the last entry is gonna be bigger than first entered iteam

            maxDequeue.addLast(item);
        }
    }

    public int deque() {
        if (queue.isEmpty()) {
            throw new NoSuchElementException("queue is empty");
        }
        if (queue.peek() == maxDequeue.getFirst()) {
            maxDequeue.removeFirst();
        }
        return queue.remove();
    }

    public int getMax() {
        if (queue.isEmpty()) {
            throw new NoSuchElementException("queue is empty");
        }
        return maxDequeue.peekFirst();
    }
}
