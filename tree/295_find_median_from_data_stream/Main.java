import java.util.PriorityQueue;

public class Main {

    public static void main(String[] args) {
        MedianFinder finder = new MedianFinder();
        finder.addNum(10);
        System.out.println(finder.findMedian());
        finder.addNum(11);
        System.out.println(finder.findMedian());
        finder.addNum(1);
        System.out.println(finder.findMedian());
        finder.addNum(2);
        System.out.println(finder.findMedian());
        finder.addNum(3);
        System.out.println(finder.findMedian());
        finder.addNum(5);
        System.out.println(finder.findMedian());


    }
}
