/**
 134. Gas Station
 https://leetcode.com/problems/gas-station/

 There are N gas stations along a circular route, where the amount of gas at station i is gas[i].
 You have a car with an unlimited gas tank and it costs cost[i] of gas to travel from station i to its next station (i+1).
 You begin the journey with an empty tank at one of the gas stations.

 Return the starting gas station's index if you can travel around the circuit once in the clockwise direction, otherwise return -1.

 Note:

 If there exists a solution, it is guaranteed to be unique.
 Both input arrays are non-empty and have the same length.
 Each element in the input arrays is a non-negative integer.


 Example 1:

 Input:
 gas  = [1,2,3,4,5]
 cost = [3,4,5,1,2]

 Output: 3

 Explanation:
 Start at station 3 (index 3) and fill up with 4 unit of gas. Your tank = 0 + 4 = 4
 Travel to station 4. Your tank = 4 - 1 + 5 = 8
 Travel to station 0. Your tank = 8 - 2 + 1 = 7
 Travel to station 1. Your tank = 7 - 3 + 2 = 6
 Travel to station 2. Your tank = 6 - 4 + 3 = 5
 Travel to station 3. The cost is 5. Your gas is just enough to travel back to station 3.
 Therefore, return 3 as the starting index.


 Example 2:

 Input:
 gas  = [2,3,4]
 cost = [3,4,3]

 Output: -1

 Explanation:
 You can't start at station 0 or 1, as there is not enough gas to travel to the next station.
 Let's start at station 2 and fill up with 4 unit of gas. Your tank = 0 + 4 = 4
 Travel to station 0. Your tank = 4 - 3 + 2 = 3
 Travel to station 1. Your tank = 3 - 3 + 3 = 3
 You cannot travel back to station 2, as it requires 4 unit of gas but you only have 3.
 Therefore, you can't travel around the circuit once no matter where you start.

 */
public class Solution {

    // My solution
    // In the worst case time complexity is O(N^2)
    // space complexity O(N)
    // gas  [1, 2, 3, 4, 5]
    // cost [3, 4, 4, 1, 2]
    // diff [-2, -2, -2, 3, 3]
    public int canCompleteCircuit(int[] gas, int[] cost) {
        int[] gasDiff = new int[gas.length];  // gas left in tank to get to i+1
        int allDiff = 0;
        for (int i = 0; i < gas.length; i++) {
            gasDiff[i] = gas[i] - cost[i];
            allDiff += gasDiff[i];
        }
        if(allDiff < 0) return -1;

        for (int i = 0; i < gasDiff.length; i++) {  // check every beginning
            int sum = gasDiff[i];
            if(sum < 0) continue;                   // if it's negative, we dont have gas to move
            int j = i + 1 < gasDiff.length ? i + 1 : 0;
            while (sum >= 0 && j != i) {            // make circle until gas is empty or we returned to starting point
                sum += gasDiff[j];
                j++;
                if(j >= gasDiff.length) j = 0;
            }

            if(j == i && sum >= 0) return i;
        }

        return -1;
    }

    // Not mine O(N) solution
    // O(N) space, but can be redone in O(1)
    // Idea:
    // If the total number of gas is bigger than the total number of cost. There must be a solution.
    // If we have a lack of fuel on previous steps, then it is in one of the next stations.
    // https://leetcode.com/problems/gas-station/discuss/42667/Straightforward-Java-Linear-Solution-with-O(1)-space-explanation-and-Math-proof
    public int canCompleteCircuit2222(int[] gas, int[] cost) {
        int[] tank = new int[gas.length];  // gas left in tank to get to i+1
        int allDiff = 0;
        for (int i = 0; i < gas.length; i++) {
            tank[i] = gas[i] - cost[i];
            allDiff += tank[i];
        }
        if(allDiff < 0) return -1;

        int accumulate = 0;
        int start = 0;
        for (int i = 0; i < tank.length; i++) {
            if(tank[i] + accumulate < 0) {
                start = i + 1;
                accumulate = 0;
            }
            else accumulate += tank[i];
        }

        return start;
    }


    // Not mine O(N) solution, similar as canCompleteCircuit2222 but one loop
    // https://leetcode.com/problems/gas-station/discuss/42568/Share-some-of-my-ideas.
    public int canCompleteCircuit3333(int[] gas, int[] cost) {
        int total = 0, accumulate = 0, start = 0;
        for (int i = 0; i < gas.length; i++) {
            int curr = gas[i] - cost[i];
            total += curr;
            if(curr + accumulate < 0) {
                start = i + 1;
                accumulate = 0;
            }
            else accumulate += curr;
        }

        return total < 0 ? -1 : start;
    }
}
