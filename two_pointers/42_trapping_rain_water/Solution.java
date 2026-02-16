/*
[5] -> 0
[1, 1] -> 0
[1, 5] -> 0

[1,4,2]->2
[2,1,4]->1


[1, 2, 8, 2, 0, 4, 1, 1, 5]



*/
class Solution {

    // O(N) solution
    /*
        Why moving the smaller side works
        
        If height[l] <= height[r], then the limiting wall for position l is leftMax 
        (because there exists some right wall at least as tall as height[l]), so the water at l is
         fully determined by leftMax and you can safely finalize it and move l.   
    */
    public int trap(int[] height) {
        if (height.length <= 2) return 0;
        int res = 0;

        int left = 0; 
        int right = height.length - 1;
        int maxLeft = 0, maxRight = 0; // maxLeft = max height seen from left so far. maxRight = max height seen from right so far

        while (left < right) {
            if (height[left] <= height[right]) {
                maxLeft = Math.max(height[left], maxLeft);
                res += maxLeft - height[left];
                left ++;
            } else {
                maxRight = Math.max(height[right], maxRight);
                res += maxRight - height[right];
                right --;
            }
        }

        return res;
    }

    // O(N^2) solution
    // start from index i and move to the right until items are smaller than current. Record next max meanwhile 
    public int trap22222(int[] height) {
        if (height.length <= 2) return 0;
        int res = 0;

        for (int begin = 0; begin < height.length - 1; begin++) {
            if (height[begin] <= height[begin + 1]) continue;

            int end = begin + 1;
            int nextMaxInd = end;
            for (; end < height.length; end ++) {
                if (height[end] >= height[begin]) {
                    nextMaxInd = end;
                    break;
                }

                if (height[end] > height[nextMaxInd]) {
                    nextMaxInd = end;
                }
            }

            int rangeHeight = Math.min(height[begin], height[nextMaxInd]);
            int rangeSum = 0;
            for (int i = begin + 1; i < nextMaxInd; i ++) {
                rangeSum += rangeHeight - height[i];
            }
            res += rangeSum;
            begin = nextMaxInd - 1; 
        }

        return res;
    }
}