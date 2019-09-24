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
	
	// Not mine solution - good idea
	// https://leetcode.com/problems/trapping-rain-water/discuss/17386/Sharing-my-Java-code%3A-O(n)-time-O(1)-space
	public int trap3333(int[] A) {
		if (A.length < 3) return 0;
		
		int ans = 0;
		int l = 0, r = A.length - 1;
		
		// find the left and right edge which can hold water
		while (l < r && A[l] <= A[l + 1]) l++;
		while (l < r && A[r] <= A[r - 1]) r--;
		
		while (l < r) {
			int left = A[l];
			int right = A[r];
			if (left <= right) {
				// add volum until an edge larger than the left edge
				while (l < r && left >= A[++l]) {
					ans += left - A[l];
				}
			} else {
				// add volum until an edge larger than the right volum
				while (l < r && A[--r] <= right) {
					ans += right - A[r];
				}
			}
		}
		return ans;
	}
	
	public int trap4444(int[] A){
		int a=0;
		int b=A.length-1;
		int max=0;
		int leftmax=0;
		int rightmax=0;
		while(a<=b){
			leftmax=Math.max(leftmax,A[a]);
			rightmax=Math.max(rightmax,A[b]);
			if(leftmax<rightmax){
				max+=(leftmax-A[a]);       // leftmax is smaller than rightmax, so the (leftmax-A[a]) water can be stored
				a++;
			}
			else{
				max+=(rightmax-A[b]);
				b--;
			}
		}
		return max;
	}
}
