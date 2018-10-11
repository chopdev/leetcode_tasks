import java.util.PriorityQueue;

/**
 * https://leetcode.com/problems/kth-smallest-element-in-a-sorted-matrix/description/

 Given a n x n matrix where each of the rows and columns are sorted in ascending order, find the kth smallest element in the matrix.
 Note that it is the kth smallest element in the sorted order, not the kth distinct element.

 Example:
 matrix = [
     [ 1,  5,  9],
     [10, 11, 13],
     [12, 13, 15]
 ],
 k = 8,

 return 13.

 Note:
 You may assume k is always valid, 1 ≤ k ≤ n^2.
 */
public class Solution {

    // Mine solution
    // Time O(N^2*logK), Space Complexity O(K) - size of heap
    // Other approaches: use 1 dimensional array of N^2 size, sort it and return Kth element. Time O(N^2*log(N^2)), Space O(N^2)
    // Other approach is to use bucket sort, but interviewer should say that the data is uniformly distributed. We know the smallest
    // and the biggest values, Time O(N^2), Space O(N^2)
    public int kthSmallest(int[][] matrix, int k) {
        if(matrix == null || matrix.length == 0 || matrix[0].length == 0) return -1;

        PriorityQueue<Integer> priorityQueue = new PriorityQueue<>(k, (o1, o2) -> Integer.compare(o2, o1)); // max heap

        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[0].length; j++) {
                if(priorityQueue.size() < k)
                    priorityQueue.offer(matrix[i][j]);
                else
                    if(priorityQueue.peek() > matrix[i][j]) {
                        priorityQueue.poll();
                        priorityQueue.offer(matrix[i][j]);
                    }
            }
        }

        return priorityQueue.peek();
    }


    // Not mine solution, very fast
    // O(MAX(K, N)*logN), Space O(logN)
    // The idea here is to insert next to smallest Tuple K times
    // https://leetcode.com/problems/kth-smallest-element-in-a-sorted-matrix/discuss/85173/Share-my-thoughts-and-Clean-Java-Code
    public int kthSmallest2222(int[][] matrix, int k) {
        PriorityQueue<Tuple> priorityQueue = new PriorityQueue<>(k);
        int n = matrix.length;

        for (int i = 0; i < n; i++)
            priorityQueue.offer(new Tuple(0, i, matrix[0][i]));

        Tuple tuple = priorityQueue.peek();
        for (int j = 0; j < k; j++) {
            tuple = priorityQueue.poll();
            if(tuple.row >= n-1) continue;
            priorityQueue.offer(new Tuple(tuple.row + 1, tuple.col, matrix[tuple.row + 1][tuple.col]));
        }

        return tuple.value;
    }

    // Not mine solution, wrote code by my self after looking the answer
    // Binary search in range
    // So we divide our matrix until lo > hi, on the last step they will meet and we will return the right value
    // Time O(log(N^2)), Space O(1)
    // https://leetcode.com/problems/kth-smallest-element-in-a-sorted-matrix/discuss/85173/Share-my-thoughts-and-Clean-Java-Code
    public int kthSmallest3333(int[][] matrix, int k) {
        int n = matrix.length;
        int lo = matrix[0][0];
        int hi = matrix[n-1][n-1];
        if(n <= 1) return lo;

        while (lo <= hi) {
            int mid = (lo + hi) / 2;

            int count = 0;
            for (int i = 0; i < n; i++) {
                int j = n - 1;
                while (j >= 0 && matrix[j][i] > mid) j--;
                if(j >= 0) count += j + 1;
            }

            if(count < k)
                lo = mid + 1;
            else hi = mid - 1;
        }

        return lo; // why do we return lo? because count < k exclusive, so when we have equal value, lo is on right position
    }

    //https://leetcode.com/problems/kth-smallest-element-in-a-sorted-matrix/discuss/85173/Share-my-thoughts-and-Clean-Java-Code
    // Solution with exclusive hi value
    public int kthSmallest4444(int[][] matrix, int k) {
        int lo = matrix[0][0], hi = matrix[matrix.length - 1][matrix[0].length - 1] + 1;//[lo, hi)
        while(lo < hi) {
            int mid = lo + (hi - lo) / 2;
            int count = 0,  j = matrix[0].length - 1;
            for(int i = 0; i < matrix.length; i++) {
                while(j >= 0 && matrix[i][j] > mid) j--;
                count += (j + 1);
            }
            if(count < k) lo = mid + 1;
            else hi = mid;
        }
        return lo;
    }
}
