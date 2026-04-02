/*
986. Interval List Intersections
https://leetcode.com/problems/interval-list-intersections/
*/

import java.util.List;
import java.util.ArrayList;

class Solution {
    public int[][] intervalIntersection(int[][] firstList, int[][] secondList) {
        if (firstList.length == 0) return new int[0][];
        if (secondList.length == 0) return new int[0][];

        int i = 0;
        int j = 0;

        List<int[]> res = new ArrayList<>();

        while (i < firstList.length && j < secondList.length) {
            if (!(firstList[i][1] < secondList[j][0] || secondList[j][1] < firstList[i][0])) {
                res.add(new int[] {Math.max(firstList[i][0], secondList[j][0]), Math.min(firstList[i][1], secondList[j][1])});
            }

            if (firstList[i][1] >= secondList[j][1]) j++;
            else i++;
        }

        return res.toArray(new int[res.size()][2]);
    }
}
