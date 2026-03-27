/*
Path from src to target with ops: x2, floor(/3)
No given link (Google interview style)
*/

import java.util.Queue;
import java.util.LinkedList;
import java.util.Set;
import java.util.HashSet;

class Solution {
    int getMinPath(long src, long target) {
        if (src == target) return 0;

        Queue<Long> q = new LinkedList<>();
        Set<Long> visited = new HashSet<>();

        q.offer(src);
        visited.add(src);

        int steps = 0;

        while (!q.isEmpty()) {
            int size = q.size();

            for (int i = 0; i < size; i++) {
                long curr = q.poll();

                if (curr == target) return steps;

                long op1 = curr * 2;
                long op2 = curr / 3;

                if (op1 > 0 && op1 <= target * 2 && visited.add(op1)) {
                    q.offer(op1);
                }

                if (op2 > 0 && visited.add(op2)) {
                    q.offer(op2);
                }
            }

            steps++;
        }

        return -1;
    }
}
