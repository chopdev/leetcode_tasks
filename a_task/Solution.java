import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Solution {

    // Time complexity: O(N*K*log(N*K)), Space O(N*K) for pairs list
    List<List<Integer>> optimalUtilization(int maxTravelDist,
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
}
