/*
904. Fruit Into Baskets
https://leetcode.com/problems/fruit-into-baskets/description/



Longest subarray with at most 2 distinct (k=2)

[1,1,1,2,2,2,2,3,3,3,3] -> 2,2,2,2,3,3,3,3
[1,2,3,4,1,1,2,2] -> 1,1,2,2


map[10] // count of trees of specific type
uniqueTrees = 0;

if (uniqueTrees > 2) // shrink the window

*/

class Solution {
    public int totalFruit(int[] fruits) {
        int[] map = new int[100000]; // type of tree to count
        int begin = 0, end = 0;
        int uniqueTrees = 0, res = 0;

        for (; end < fruits.length; end ++) {
            if (map[fruits[end]] == 0) uniqueTrees++;
            map[fruits[end]] ++;

            while (uniqueTrees > 2) {
                map[fruits[begin]] --;
                if (map[fruits[begin]] == 0) uniqueTrees--;
                begin ++;
            }

            res = Math.max(res, end - begin + 1);
        }

        return res;
    }
}