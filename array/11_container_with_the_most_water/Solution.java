/**
 https://leetcode.com/problems/container-with-most-water/description/
 11. Container With Most Water

 Given n non-negative integers a1, a2, ..., an , where each represents a point at coordinate (i, ai). n vertical
 lines are drawn such that the two endpoints of line i is at (i, ai) and (i, 0). Find two lines, which together
 with x-axis forms a container, such that the container contains the most water.

 Note: You may not slant the container and n is at least 2.


 The above vertical lines are represented by array [1,8,6,2,5,4,8,3,7]. In this case, the max area of water (blue section) the container can contain is 49.

 Example:
 Input: [1,8,6,2,5,4,8,3,7]
 Output: 49

 */
public class Solution {

    // Mine brute force solution O(N^2) time, O(1) space
    public int maxArea(int[] height) {
        if(height == null) return 0;
        int maxSquare = 0;

        for (int i = 0; i < height.length; i++) {
            for (int j = i + 1; j < height.length; j++) {
                int a = Math.min(height[i], height[j]);
                int b = j - i;
                maxSquare = a*b > maxSquare ? a*b : maxSquare;
            }
        }

        return maxSquare;
    }
}
