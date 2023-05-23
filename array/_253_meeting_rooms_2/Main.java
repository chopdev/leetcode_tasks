public class Main {
    public static void main(String[] args) {
        int[][] intervals1 = { {1, 5}, {8,9}, {8,9} };
        int[][] intervals2 = { {1,8},{6,20},{9,16},{13,17}};
        Solution sol = new Solution();

        System.out.println(sol.minMeetingRooms(intervals2));
    }
}
