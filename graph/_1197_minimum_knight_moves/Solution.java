import java.util.Deque;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;
import java.util.Set;

/**
 1197. Minimum Knight Moves
 https://leetcode.com/problems/minimum-knight-moves/?envType=study-plan-v2&envId=premium-algo-100

 In an infinite chess board with coordinates from -infinity to +infinity, you have a knight at square [0, 0].
 A knight has 8 possible moves it can make, as illustrated below. Each move is two squares in a cardinal direction, then one square in an orthogonal direction.
 Return the minimum number of steps needed to move the knight to the square [x, y]. It is guaranteed the answer exists.


 Example 1:

 Input: x = 2, y = 1
 Output: 1
 Explanation: [0, 0] → [2, 1]


 Example 2:

 Input: x = 5, y = 5
 Output: 4
 Explanation: [0, 0] → [2, 1] → [4, 2] → [3, 4] → [5, 5]


 Constraints:

 -300 <= x, y <= 300
 0 <= |x| + |y| <= 300
 * */
public class Solution {

    private final int[][] possibleSteps = {{2, 1}, {2, -1}, {-2, 1}, {-2, -1}, {1, 2}, {-1, 2}, {1, -2}, {-1, -2}};


    /**
     * My BFS solution
     *
     * Due to the nature of BFS, before reaching the target, we will have covered all the neighborhoods that are closer to the start point. The aggregate of these neighborhoods forms a circle, and the area can be approximated by the
     * area of a square with an edge length of max(2∣x∣,2∣y∣).
     * The number of cells within this square would be (max(∣x∣,∣y∣))^ 2)
     *
     * time and space complexity: O(max(∣X∣,∣Y∣))^ 2)
     * */
    public int minKnightMoves(int x, int y) {
        Set<Point> visible = new HashSet<>();
        Point seek = new Point(x, y);

        Queue<Point> queue = new LinkedList<>();
        queue.add(new Point(0, 0));
        visible.add(new Point(0, 0));

        int steps = -1;
        while (!queue.isEmpty()) {
            steps ++;

            int currentLevelSize = queue.size();
            for (int i = 0; i < currentLevelSize; i++) {
                Point curr  = queue.poll();
                if (curr.equals(seek)) return steps;

                for (int[] possibleStep : possibleSteps) {
                    Point candidate = new Point(curr.x + possibleStep[0], curr.y + possibleStep[1]);
                    if (!visible.contains(candidate)) {
                        queue.add(candidate);
                        visible.add(candidate);
                    }
                }

            }
        }

        return steps;
    }

    class Point {
        int x;
        int y;

        public Point(int x, int y) {
            this.x = x;
            this.y = y;
        }

        @Override
        public int hashCode() {
            int res = 17;
            res = 31 * res + x; // 31 for multiplication helps improve the distribution of hash codes.
            res = 31 * res + y;
            return res;
        }

        @Override
        public boolean equals(Object obj) {
            if (!(obj instanceof  Point)) return false;
            Point another = (Point) obj;
            return this.x == another.x && this.y == another.y;
        }
    }



    /**
     * My implementation of bidirectional BFS
     *
     * Time and space complexity is the same: O(max(∣X∣,∣Y∣))^ 2)
     *
     * Although the bidirectional BFS cuts the exploration scope in half, compared to the unidirectional BFS, the overall time and space complexities remain the same.
     *
     * This can be proved mathematically. Suppose that the distance between the start and target points is d, the exploration scope covered by the unidirectional BFS will be a circle whose area is π*d^2
     * On the other hand, with the bidirectional BFS, the exploration scope will be two smaller circles whose total area is 2* π*(d/2)^2 = (π*d^2)/2 i.e. half of the area covered by the unidirectional BFS.
     *
     * */
    public int minKnightMoves4444(int x, int y) {
        Map<Point, Integer> distToOrigin = new HashMap<>(); // use to track visited items and the distance to origin
        Queue<int[]> originQueue = new LinkedList<>(); // BFS from origin point
        distToOrigin.put(new Point(0, 0), 0);
        originQueue.add(new int[] {0, 0, 0});

        Map<Point, Integer> distToTarget = new HashMap<>(); // use to track visited items and the distance to target/destination
        Queue<int[]> distQueue = new LinkedList<>(); // BFS from target point
        distToTarget.put(new Point(x, y), 0);
        distQueue.add(new int[] {x, y, 0});

        while (true) {
            int[] currFromOrigin = originQueue.poll();
            int[] currFromDist = distQueue.poll();

            if (distToTarget.containsKey(new Point(currFromOrigin[0], currFromOrigin[1]))) // check if there is an intersection of circles from origin and target
                return currFromOrigin[2] + distToTarget.get(new Point(currFromOrigin[0], currFromOrigin[1])); // return distance to current item from the origin and from the target

            if (distToOrigin.containsKey(new Point(currFromDist[0], currFromDist[1]))) // check if there is an intersection of circles from origin and target
                return currFromDist[2] + distToOrigin.get(new Point(currFromDist[0], currFromDist[1])); // return distance to current item from the origin and from the target

            for (int[] nextStep : possibleSteps) {
                // increase circle from origin
                Point nextFromOrigin = new Point(nextStep[0] + currFromOrigin[0], nextStep[1] + currFromOrigin[1]);
                if (!distToOrigin.containsKey(nextFromOrigin)) {
                    distToOrigin.put(nextFromOrigin, currFromOrigin[2] + 1);
                    originQueue.add(new int[] {nextFromOrigin.x, nextFromOrigin.y, currFromOrigin[2] + 1});
                }

                // increase circle from target
                Point nextFromTarget = new Point(nextStep[0] + currFromDist[0], nextStep[1] + currFromDist[1]);
                if (!distToTarget.containsKey(nextFromTarget)) {
                    distToTarget.put(nextFromTarget, currFromDist[2] + 1);
                    distQueue.add(new int[] {nextFromTarget.x, nextFromTarget.y, currFromDist[2] + 1});
                }
            }
        }
    }




    // https://leetcode.com/problems/minimum-knight-moves/editorial/?envType=study-plan-v2&envId=premium-algo-100
    public int minKnightMoves2222(int x, int y) {
        // the offsets in the eight directions
        int[][] offsets = {{1, 2}, {2, 1}, {2, -1}, {1, -2},
                {-1, -2}, {-2, -1}, {-2, 1}, {-1, 2}};

        // - Rather than using the inefficient HashSet, we use the bitmap
        //     otherwise we would run out of time for the test cases.
        // - We create a bitmap that is sufficient to cover all the possible
        //     inputs, according to the description of the problem.
        boolean[][] visited = new boolean[607][607];

        Deque<int[]> queue = new LinkedList<>();
        queue.addLast(new int[]{0, 0});
        int steps = 0;

        while (queue.size() > 0) {
            int currLevelSize = queue.size();
            // iterate through the current level
            for (int i = 0; i < currLevelSize; i++) {
                int[] curr = queue.removeFirst();
                if (curr[0] == x && curr[1] == y) {
                    return steps;
                }

                for (int[] offset : offsets) {
                    int[] next = new int[]{curr[0] + offset[0], curr[1] + offset[1]};
                    // align the coordinate to the bitmap
                    if (!visited[next[0] + 302][next[1] + 302]) {
                        visited[next[0] + 302][next[1] + 302] = true;
                        queue.addLast(next);
                    }
                }
            }
            steps++;
        }
        // move on to the next level
        return steps;
    }


    /*
    * bidirectional BFS
    * */
        public int minKnightMoves3333(int x, int y) {
            // the offsets in the eight directions
            int[][] offsets = {{1, 2}, {2, 1}, {2, -1}, {1, -2},
                    {-1, -2}, {-2, -1}, {-2, 1}, {-1, 2}};

            // data structures needed to move from the origin point
            Deque<int[]> originQueue = new LinkedList<>();
            originQueue.addLast(new int[]{0, 0, 0});
            Map<String, Integer> originDistance = new HashMap<>();
            originDistance.put("0,0", 0);

            // data structures needed to move from the target point
            Deque<int[]> targetQueue = new LinkedList<>();
            targetQueue.addLast(new int[]{x, y, 0});
            Map<String, Integer> targetDistance = new HashMap<>();
            targetDistance.put(x + "," + y, 0);

            while (true) {
                // check if we reach the circle of target
                int[] origin = originQueue.removeFirst();
                String originXY = origin[0] + "," + origin[1];
                if (targetDistance.containsKey(originXY)) {
                    return origin[2] + targetDistance.get(originXY);
                }

                // check if we reach the circle of origin
                int[] target = targetQueue.removeFirst();
                String targetXY = target[0] + "," + target[1];
                if (originDistance.containsKey(targetXY)) {
                    return target[2] + originDistance.get(targetXY);
                }

                for (int[] offset : offsets) {
                    // expand the circle of origin
                    int[] nextOrigin = new int[]{origin[0] + offset[0], origin[1] + offset[1]};
                    String nextOriginXY = nextOrigin[0] + "," + nextOrigin[1];
                    if (!originDistance.containsKey(nextOriginXY)) {
                        originQueue.addLast(new int[]{nextOrigin[0], nextOrigin[1], origin[2] + 1});
                        originDistance.put(nextOriginXY, origin[2] + 1);
                    }

                    // expand the circle of target
                    int[] nextTarget = new int[]{target[0] + offset[0], target[1] + offset[1]};
                    String nextTargetXY = nextTarget[0] + "," + nextTarget[1];
                    if (!targetDistance.containsKey(nextTargetXY)) {
                        targetQueue.addLast(new int[]{nextTarget[0], nextTarget[1], target[2] + 1});
                        targetDistance.put(nextTargetXY, target[2] + 1);
                    }
                }
            }
        }


}
