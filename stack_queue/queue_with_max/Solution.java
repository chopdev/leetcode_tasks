public class Solution {
    public static void main(String[] args) {
        MaxQueue maxQueue = new MaxQueue();

        maxQueue.enqueue(1);
        maxQueue.enqueue(2);
        maxQueue.enqueue(3);

        System.out.println(maxQueue.getMax());
        maxQueue.deque();
        System.out.println(maxQueue.getMax());
        maxQueue.deque();
        System.out.println(maxQueue.getMax());
        maxQueue.deque();


        System.out.println();


        maxQueue.enqueue(3);
        maxQueue.enqueue(2);
        maxQueue.enqueue(1);
        maxQueue.enqueue(2);

        System.out.println(maxQueue.getMax());
        maxQueue.deque();
        System.out.println(maxQueue.getMax());
        maxQueue.deque();
        System.out.println(maxQueue.getMax());
        maxQueue.deque();
        System.out.println(maxQueue.getMax());
        maxQueue.deque();
    }
}
