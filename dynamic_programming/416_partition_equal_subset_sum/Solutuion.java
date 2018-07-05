public class Solutuion {

    public boolean canPartition(int[] nums) {
        if(nums == null)
            return  false;

        return canPartitionInternal(nums, -1, 0, 0);
    }

    public boolean canPartitionInternal(int[] nums, int i, int sum1, int sum2) {
        i++;
        if(nums.length <= i)
            return sum1 - sum2 == 0;

        return canPartitionInternal(nums, i, sum1 + nums[i], sum2) ||
                canPartitionInternal(nums, i, sum1 , sum2 + nums[i]);
    }
}
