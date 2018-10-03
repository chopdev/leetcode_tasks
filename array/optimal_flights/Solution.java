import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/*
Given an aircraft that moves packages. Aircraft takes package from the heap of packages and carry it to destination. 
Input:
Aircraft can flight some maxTravelDist. 
There are also two  lists. 
forwardRouteList - contains list of pairs: id of flight and distance to destination.
returnRouteList - contains list of pairs: id of flight and distance back.

Return List of optimal pairs of forward and return flights for the aircraft.
Optimal flight is a flight with maximum forward + return distance but less or equal than maxTravelDist.

Example 
1)
maxTravelDist = 100
[[1, 50], [2, 70], [3, 20]] - forward
[[1, 25], [2, 50], [3, 30]]  - return
Result [[1, 2] [2, 3]] - both pairs give 100 

2)
maxTravelDist = 100
[[1, 45], [2, 40], [3, 20]] - forward
[[1, 25], [2, 45], [3, 30]]  - return
Result [[1, 2]] - this pair gives the most optimal distance 90

*/
public class Solution {

    // Time complexity: O(N*K*log(N*K)), Space O(N*K) for pairs list
    List<List<Integer>> optimalFlights(int maxTravelDist,
                                           List<List<Integer>> forwardRouteList,
                                           List<List<Integer>> returnRouteList)
    {
        List<Pair> pairs = new ArrayList<>();

        for (int i = 0; i < forwardRouteList.size(); i++) {
            for (int j = 0; j < returnRouteList.size(); j++) {
                int firstDist = forwardRouteList.get(i).get(1);
                int secondDist = returnRouteList.get(j).get(1);
                int sum = firstDist + secondDist;
                if(sum <= maxTravelDist)
                    pairs.add(new Pair(forwardRouteList.get(i).get(0), returnRouteList.get(j).get(0), sum));
            }
        }

        Collections.sort(pairs);

        List<List<Integer>> res = new ArrayList<>();
        int maxSum = 0;
        for (int i = 0; i < pairs.size(); i++) {
            if(maxSum <= pairs.get(i).sum){
                maxSum = pairs.get(i).sum;
                List<Integer> temp = new ArrayList<>();
                temp.add(pairs.get(i).firstId);
                temp.add(pairs.get(i).secondId);
                res.add(temp);
            }
            else
                break;
        }

        return res;
    }


    // Mine better solution
    // Time complexity: O(N*K), Space O(1) - do not use extra memory, except result which is not taken into account
    List<List<Integer>> optimalFlights2222(int maxTravelDist,
                                       List<List<Integer>> forwardRouteList,
                                       List<List<Integer>> returnRouteList)
    {
        List<List<Integer>> res = new ArrayList<>();
        int maxFoundDist = 0;

        for (int i = 0; i < forwardRouteList.size(); i++) {
            for (int j = 0; j < returnRouteList.size(); j++) {
                int firstDist = forwardRouteList.get(i).get(1);
                int secondDist = returnRouteList.get(j).get(1);
                int sum = firstDist + secondDist;

                if(sum > maxTravelDist || maxFoundDist > sum)
                    continue;

                if(maxFoundDist < sum)
                    res = new ArrayList<>();

                maxFoundDist = sum;
                List<Integer> temp = new ArrayList<>();
                temp.add(forwardRouteList.get(i).get(0));
                temp.add(returnRouteList.get(j).get(0));
                res.add(temp);
            }
        }

        return res;
    }
}
