/**
 42. Trapping Rain Water
 https://leetcode.com/problems/trapping-rain-water/

 Given n non-negative integers representing an elevation map where the width of each bar is 1,
 compute how much water it is able to trap after raining.

 Example:

 Input: [0,1,0,2,1,0,1,3,2,1,2,1]
 Output: 6

 SOLUTION
 https://leetcode.com/articles/trapping-rain-water/
 */
public class Solution {

    // Mine partially not working solution
    // for this case 5,2,1,2,1,5
    // I look for peaks and calculate volume between them
    public int trap(int[] height) {
        int sum = 0;
        int from = 0, to = 0;

        while (from < height.length - 1) {
            while (from < height.length - 1 && height[from] <= height[from + 1]) from++;  // find peak element
            to = from;
            while (to < height.length - 1 && height[to] >= height[to + 1]) to++; // starting from peak element go down
            while (to < height.length - 1 && height[to] <= height[to + 1]) to++; // starting down go to next peak

            if(to > from) sum += getVolume(height, from, to); // add volume between two peaks
            from = to; // put from on new peak
        }
        return sum;
    }

    private int getVolume(int[] height, int from, int to) {
        int maxHeight = Math.min(height[from], height[to]);
        int volume = maxHeight * (to - from - 1);

        for (int i = from + 1; i < to ; i++) {
            volume -= Math.min(height[i], maxHeight);
        }
        return volume;
    }


    // Not mine solution
    // O(N^2) time
    // calculate volume for each cell
    int trap2222(int[] height)
    {
        int ans = 0;
        int size = height.length;
        for (int i = 1; i < size - 1; i++) {
            int max_left = 0, max_right = 0;
            for (int j = i; j >= 0; j--) { //Search the left part for max bar size
                max_left = Math.max(max_left, height[j]);
            }
            for (int j = i; j < size; j++) { //Search the right part for max bar size
                max_right = Math.max(max_right, height[j]);
            }
            ans += Math.min(max_left, max_right) - height[i];
        }
        return ans;
    }
}
