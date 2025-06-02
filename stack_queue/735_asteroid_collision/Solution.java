class Solution {

    // [1, 2, 3, -4, -5, 1, 2] -> [-4, -5, 1, 2]
    // [1, 3, 2, -1 , -2] -> [1, 3]
    // [7, 6,1, 2, 3, -4, -5] -[7, 6] 


    // O(N) time and space
    public int[] asteroidCollision(int[] asteroids) {
        List<Integer> res = new ArrayList<>(asteroids.length);
        Deque<Integer> deque = new LinkedList<>();

        for (int val : asteroids) {
            if (val > 0) {
                deque.addLast(val);
            } else {
                while (!deque.isEmpty() && deque.peekLast() < Math.abs(val)) {
                    deque.removeLast();
                }

                if (deque.isEmpty()) {
                    res.add(val);
                    continue;
                }

                if (deque.peekLast() == Math.abs(val)) {
                    deque.removeLast();
                }
            }
        }

        while (!deque.isEmpty()) {
            res.add(deque.removeFirst());
        }

        return res.stream().mapToInt(Integer::intValue).toArray();
    }
}