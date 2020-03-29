import java.util.*;

/**
 * https://leetcode.com/problems/two-city-scheduling/
* 1029. Two City Scheduling
 *
 * There are 2N people a company is planning to interview. The cost of flying the i-th person to city A is costs[i][0], and the cost of flying the i-th person to city B is costs[i][1].
 *
 * Return the minimum cost to fly every person to a city such that exactly N people arrive in each city.
 *
 *
 *
 * Example 1:
 *
 * Input: [[10,20],[30,200],[400,50],[30,20]]
 * Output: 110
 * Explanation:
 * The first person goes to city A for a cost of 10.
 * The second person goes to city A for a cost of 30.
 * The third person goes to city B for a cost of 50.
 * The fourth person goes to city B for a cost of 20.
 *
 * The total minimum cost is 10 + 30 + 50 + 20 = 110 to have half the people interviewing in each city.
 *
 *
 * Note:
 *
 * 1 <= costs.length <= 100
 * It is guaranteed that costs.length is even.
 * 1 <= costs[i][0], costs[i][1] <= 1000
* */
public class Solution {


    // TIME:  O(N*logN)
    // sort the list first by the B-A cost, then the beginning of the list (smallest values of "relative
    // cost to send to B") are the ones you would rather send to B and the ones at the end you'd rather send to A.
    // https://leetcode.com/problems/two-city-scheduling/discuss/278898/Java-2ms-sorting-solution-with-explanation
    public int twoCitySchedCost(int[][] costs) {
        Arrays.sort(costs, (a, b) -> (a[1] - a[0]) - (b[1] - b[0]));
        int cost = 0;
        for (int i = 0; i < costs.length / 2; i++) {
            cost += costs[i][1] + costs[costs.length-i-1][0];
        }
        return cost;
    }

    // My long solution (not works for all cases)
    // the idea is to move in other city only guys with the smallest difference in the costs of the tickets
    public int twoCitySchedCost2222(int[][] costs) {
        int N = costs.length / 2;
        int sum = 0;
        int firstCityCount = 0;

        for (int i = 0; i < costs.length; i++) {
            boolean firstCheaper = costs[i][0] < costs[i][1];
            firstCityCount += firstCheaper ? 1 : 0;
            sum += firstCheaper ? costs[i][0] : costs[i][1];
        }

        if(firstCityCount == N) {
            return sum;
        }

        List<Person> cityMore = new ArrayList<>();
        for (int i = 0; i < costs.length; i++) {
            if (firstCityCount < N && costs[i][1] < costs[i][0]) {
                cityMore.add(new Person(costs[i][0], costs[i][1]));
            }
            if (firstCityCount > N && costs[i][0] < costs[i][1]) {
                cityMore.add(new Person(costs[i][0], costs[i][1]));
            }
        }

        cityMore.sort(Comparator.comparingInt(p -> p.diff));

        for (int i = 0; i < Math.abs(N - firstCityCount); i++) {
            if (firstCityCount > N) {
                sum -= cityMore.get(i).first;
                sum += cityMore.get(i).second;
            }
            else {
                sum += cityMore.get(i).first;
                sum -= cityMore.get(i).second;
            }
        }
        return sum;
    }

    static class Person {
        int first;
        int second;
        int diff;

        public Person(int first, int second) {
            this.first = first;
            this.second = second;
            diff = Math.abs(first - second);
        }
    }
}
