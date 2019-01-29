import java.util.ArrayList;
import java.util.List;

public class Main {

    public static void main(String[] args) {
	    Solution solution = new Solution();

        Interval i1 = new Interval(1, 3);
        Interval i2 = new Interval(2, 6);
        Interval i5 = new Interval(8, 10);
        Interval i3 = new Interval(3, 7);
        Interval i4 = new Interval(8, 9);

        ArrayList<Interval> intervals = new ArrayList<>();
        intervals.add(i1);
        intervals.add(i2);
        intervals.add(i3);
        intervals.add(i4);
        intervals.add(i5);

        List<Interval> res = solution.merge(intervals);


        Interval i11 = new Interval(1, 4);
        Interval i22 = new Interval(4, 5);
        intervals = new ArrayList<>();
        intervals.add(i11);
        intervals.add(i22);

        res = solution.merge(intervals);
    }
}
