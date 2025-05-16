class Solution {
    public double findMaxAverage(int[] nums, int k) {
        int maxSum = 0;
        int sum = 0;
        for (int i=0; i< k; i++) 
            sum+= nums[i];
        maxSum = sum;

        for (int i = k; i < nums.length; i++) {
            sum -= nums[i-k];
            sum += nums[i];
            

            if (sum > maxSum) 
                maxSum = sum;       
        }

        System.out.println(maxSum);

        return Math.round(maxSum / (double)k * 100000.0) / 100000.0;
    }
}