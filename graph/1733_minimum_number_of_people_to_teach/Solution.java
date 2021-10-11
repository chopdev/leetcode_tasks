import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
On a social network consisting of m users and some friendships between users, two users can communicate with each other if they 
know a common language.

You are given an integer n, an array languages, and an array friendships where:

There are n languages numbered 1 through n,
languages[i] is the set of languages the i​​​​​​th​​​​ user knows, and
friendships[i] = [u​​​​​​i​​​, v​​​​​​i] denotes a friendship between the users u​​​​​​​​​​​i​​​​​ and vi.
You can choose one language and teach it to some users so that all friends can communicate with each other. Return the minimum number of users you need to teach.

Note that friendships are not transitive, meaning if x is a friend of y and y is a friend of z, this doesn't guarantee that x is a friend of z.
 

Example 1:
Input: n = 2, languages = [[1],[2],[1,2]], friendships = [[1,2],[1,3],[2,3]]
Output: 1
Explanation: You can either teach user 1 the second language or user 2 the first language.

Example 2:
Input: n = 3, languages = [[2],[1,3],[1,2],[3]], friendships = [[1,4],[1,2],[3,4],[2,3]]
Output: 2
Explanation: Teach the third language to users 1 and 3, yielding two users to teach.
 

Constraints:
2 <= n <= 500
languages.length == m
1 <= m <= 500
1 <= languages[i].length <= n
1 <= languages[i][j] <= n
1 <= u​​​​​​i < v​​​​​​i <= languages.length
1 <= friendships.length <= 500
All tuples (u​​​​​i, v​​​​​​i) are unique
languages[i] contains only unique values
 */


/**
  My solution

 Basically we need to to find the most popular language between unmet friendships. 
 Then we can go through unmet friendships and dicidehow many people should be teached with this language (if it's not in the list of the users languages).
 
 n = 3, languages = [[2],[1,3],[1,2],[3]], friendships = [[1,4],[1,2],[3,4],[2,3]]
 unmet_friendships = [[1,4],[1,2],[3,4]]

 1: 2
 2: 3
 3: 3
 */
public class Solution {
    public int minimumTeachings(int n, int[][] languages, int[][] friendships) {
        Map<Integer, Integer> languageToUsage = new HashMap<>();
        List<int[]> unmetFriendships = new ArrayList<>();
        for(int[] friendship : friendships) {
            int usr1 = friendship[0];
            int usr2 = friendship[1];
            int[] usr1Langs = languages[usr1 - 1];
            int[] usr2Langs = languages[usr2 - 1];
            if (!languagesIntersect(usr1Langs, usr2Langs)) {
                unmetFriendships.add(friendship);
                increasePopularity(languageToUsage, usr1Langs);
                increasePopularity(languageToUsage, usr2Langs);
            }
        }
        
        int topLang = getMostPopularLanguage(languageToUsage);
        int peopleToTeach = 0;
        for (int[] unmetFriendship : unmetFriendships) {
            int usr1 = unmetFriendship[0];
            int usr2 = unmetFriendship[1];
            int[] langToLearn = new int[] {topLang};
            if (!languagesIntersect(languages[usr1 - 1], langToLearn))
                peopleToTeach++;
            if (!languagesIntersect(languages[usr2 - 1], langToLearn))
                peopleToTeach++;
        }
        return peopleToTeach;
    }

    private int getMostPopularLanguage(Map<Integer, Integer> languageToUsage) {
        int res = -1;
        int maxUsage = Integer.MIN_VALUE;
        for (int lang : languageToUsage.keySet()) {
            if (languageToUsage.get(lang) > maxUsage) {
                maxUsage = languageToUsage.get(lang);
                res = lang;
            }
        }
        return res;
    }

    private void increasePopularity(Map<Integer, Integer> languageToUsage, int[] languages) {
        for (int lan : languages) {
            languageToUsage.put(lan, languageToUsage.getOrDefault(lan, 0) + 1);
        }
    }

    private boolean languagesIntersect(int[] lan1, int[] lan2) {
        Set<Integer> firstLanguages = new HashSet<>();
        for (int tmp : lan1) {
            firstLanguages.add(tmp);
        }
        for (int language : lan2) {
            if (firstLanguages.contains(language)) {
                return true;
            }
        }
        return false;
    }
}
