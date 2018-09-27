import java.util.PriorityQueue;

/**
 * Median is the middle value in an ordered integer list. If the size of the list is even, there is no middle value. So the median is the mean of the two middle value.
 *
 * For example,
 * [2,3,4], the median is 3
 * [2,3], the median is (2 + 3) / 2 = 2.5
 *
 * Design a data structure that supports the following two operations:

 void addNum(int num) - Add a integer number from the data stream to the data structure.
 double findMedian() - Return the median of all elements so far.

 Follow up:

 If all integer numbers from the stream are between 0 and 100, how would you optimize it?
 If 99% of all integer numbers from the stream are between 0 and 100, how would you optimize it?
 */
public class MedianFinder {

    // Not mine solution, but I was close to it
    // Time O(logN) for adding and O(1) for find median
    private PriorityQueue<Integer> maxHeap;
    private PriorityQueue<Integer> minHeap;

    public MedianFinder() {
        this.maxHeap = new PriorityQueue<>((o1, o2) -> o1 < o2 ? 1 : -1);
        this.minHeap = new PriorityQueue<>();
    }

    public void addNum(int num) {
        maxHeap.add(num);
        minHeap.add(maxHeap.remove());

        if(minHeap.size() > maxHeap.size())
            maxHeap.add(minHeap.remove());
    }

    public double findMedian() {
        if(maxHeap.size() == minHeap.size()) {
            double res = (maxHeap.peek() + minHeap.peek()) / 2.0;
            return res;
        }

        return maxHeap.peek();
    }
}
