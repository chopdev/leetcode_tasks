public class Main {

    public static void main(String[] args) {
	    //[{1, 4}, {2, 5}, {9, 12}, {5, 9}, {5, 12} ]
        Interval i1 = new Interval(1, 4);
        Interval i2 = new Interval(2, 5);
        Interval i3 = new Interval(9, 12);
        Interval i4 = new Interval(5, 9);
        Interval i5 = new Interval(5, 12);

        Solution sol = new Solution();
        Interval res1 = sol.getIntervalWithMaxUsers(new Interval[] {i1, i2, i3, i4, i5}); // 5-5  max 3 user online
        Interval res2 = sol.getIntervalWithMaxUsers(new Interval[] {i1});  // 1-4  max 1 user online
        Interval res3 = sol.getIntervalWithMaxUsers(new Interval[0]);   // null
        Interval res4 = sol.getIntervalWithMaxUsers(new Interval[] {i1, i3});  // 1-4  max 1 user online
        Interval res5 = sol.getIntervalWithMaxUsers(new Interval[] {i1, i2, i3}); // 2-4  max 2 user online


        Interval res21 = sol.getIntervalWithMaxUsers2222(new Interval[] {i1, i2, i3, i4, i5}); // 5-5  max 3 user online
        Interval res22 = sol.getIntervalWithMaxUsers2222(new Interval[] {i1});  // 1-4  max 1 user online
        Interval res23 = sol.getIntervalWithMaxUsers2222(new Interval[0]);   // null
        Interval res24 = sol.getIntervalWithMaxUsers2222(new Interval[] {i1, i3});  // 1-4  max 1 user online
        Interval res25 = sol.getIntervalWithMaxUsers2222(new Interval[] {i1, i2, i3}); // 2-4  max 2 user online
    }
}
