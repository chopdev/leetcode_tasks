import java.util.HashSet;
import java.util.Set;

/**
 421. Maximum XOR of Two Numbers in an Array
 https://leetcode.com/problems/maximum-xor-of-two-numbers-in-an-array/


 Given an integer array nums, return the maximum result of nums[i] XOR nums[j], where 0 <= i <= j < n.



 Example 1:

 Input: nums = [3,10,5,25,2,8]
 Output: 28
 Explanation: The maximum result is 5 XOR 25 = 28.


 Example 2:

 Input: nums = [14,70,53,83,49,91,36,80,92,51,66,70]
 Output: 127


 Constraints:

 1 <= nums.length <= 2 * 105
 0 <= nums[i] <= 231 - 1
 */
public class Solution {

    // my brute force O(N^2)
    public int findMaximumXOR2222(int[] nums) {
        int max = 0;
        for (int i = 0; i < nums.length; i++) {
            for (int j = i + 1; j < nums.length; j++) {
                max = Math.max(max, nums[i] ^ nums[j]);
            }
        }

        return max;
    }



    ///
    // couple things to remind:
    // 1) 0000 1100 = 2^3 + 2^2
    // 2) a ^ b = c   a ^ c = b

    class Node {
        Node[] children;
        int val;

        public Node(int val) {
            this.val = val;
            children = new Node[2];
        }
    }


    // Not mine solution, but my implementation
    // idea taken from here: https://leetcode.com/problems/maximum-xor-of-two-numbers-in-an-array/discuss/91059/Java-O(n)-solution-using-Trie
    public int findMaximumXOR(int[] nums) {
        Node root = buildTrie(nums);

        int maxXor = 0;
        for (int num : nums) {
            Node curr = root;
            int sum = 0;
            for(int i = 30; i>= 0; i--) { // start from maximum bit to make sum max
                int currBit = (num >>> i) & 1;
                int oppositeBit = currBit ^ 1;
                if (curr.children[oppositeBit] != null) { // check if opposite bit exists
                    sum += 1 << i;
                    curr = curr.children[oppositeBit];
                }
                else curr = curr.children[currBit];
            }
            maxXor = Math.max(maxXor, sum);
        }
        return maxXor;
    }

    private Node buildTrie(int[] nums) {
        Node root = new Node(-1);
        for (int num : nums) {
            Node curr = root;
            for(int i = 30; i>= 0; i--) { // non negative numbers, 31th bit is always 0, so consider only 30 first 30
                int currBit = (num >>> i) & 1; // check if bit is set to 1, starting from 30th bit and going down
                if (curr.children[currBit] == null) {
                    curr.children[currBit] = new Node(currBit);
                }
                curr = curr.children[currBit];
            }
        }
        return root;
    }



    // Not my solution
    // NOTE: the one above solution is better for understanding
    // There is detailed explanation here in comments:
    // https://leetcode.com/problems/maximum-xor-of-two-numbers-in-an-array/discuss/91049/Java-O(n)-solution-using-bit-manipulation-and-HashMap
    public int findMaximumXOR3333(int[] nums) {
        int max = 0, mask = 0;
        for(int i = 31; i >= 0; i--){
            mask = mask | (1 << i);
            Set<Integer> set = new HashSet<>();
            for(int num : nums){
                set.add(num & mask);
            }
            int tmp = max | (1 << i);
            for(int prefix : set){
                if(set.contains(tmp ^ prefix)) {
                    max = tmp;
                    break;
                }
            }
        }
        return max;
    }
}
