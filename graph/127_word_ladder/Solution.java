import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;

/**
 127. Word Ladder
https://leetcode.com/problems/word-ladder/

 Given two words (beginWord and endWord), and a dictionary's word list, find the length of shortest transformation
 sequence from beginWord to endWord, such that:

 Only one letter can be changed at a time.
 Each transformed word must exist in the word list. Note that beginWord is not a transformed word.
 Note:

 Return 0 if there is no such transformation sequence.
 All words have the same length.
 All words contain only lowercase alphabetic characters.
 You may assume no duplicates in the word list.
 You may assume beginWord and endWord are non-empty and are not the same.

 Example 1:

 Input:
 beginWord = "hit",
 endWord = "cog",
 wordList = ["hot","dot","dog","lot","log","cog"]

 Output: 5

 Explanation: As one shortest transformation is "hit" -> "hot" -> "dot" -> "dog" -> "cog",
 return its length 5.
 Example 2:

 Input:
 beginWord = "hit"
 endWord = "cog"
 wordList = ["hot","dot","dog","lot","log"]

 Output: 0

 Explanation: The endWord "cog" is not in wordList, therefore no possible transformation.

* */
public class Solution {
    public int ladderLength(String beginWord, String endWord, List<String> wordList) {
        int endInd = -1;
        for (int i = 0; i < wordList.size(); i++)
            if(wordList.get(i).equals(endWord)) endInd = i;

        if(endInd == -1) return 0;
        wordList.add(beginWord);
        ArrayList<Integer>[] graph = new ArrayList[wordList.size()];
        for (int i = 0; i < graph.length; i++) {
            graph[i] = new ArrayList<>();
        }

        for (int i = 0; i < wordList.size(); i++) {
            for (int j = i + 1; j < wordList.size(); j++) {
                if(similar(wordList.get(i), wordList.get(j))) {
                    graph[i].add(j);
                    graph[j].add(i);
                }
            }
        }

        ArrayList<Integer> res = new ArrayList<>();
        if(!dfs(wordList.size() - 1, graph, endInd, new HashSet<>(), res)) return 0;

        return res.size() - 1;
    }

    boolean dfs(int currInd, ArrayList<Integer>[] graph, int destInd, HashSet<Integer> visited, List<Integer> res) {
        if(currInd == destInd) {
            res.add(currInd);
            return true;
        }
        visited.add(currInd);

        for (int child : graph[currInd]) {
            if(!visited.contains(child)) {
                if(dfs(child, graph, destInd, visited, res)) {
                    res.add(currInd);
                    return true;
                }
            }
        }
        return false;
    }

    private boolean similar(String s1, String s2) {
        if(s1.length() != s2.length()) return false;

        int[] lettersCount = new int[26];
        for (int i = 0; i < s1.length(); i++) {
            lettersCount[s1.charAt(i) - 'a']++;
            lettersCount[s2.charAt(i) - 'a']--;
        }

        int rest = 0;
        for (int i = 0; i < lettersCount.length; i++) {
            if(lettersCount[i] > 0) rest += lettersCount[i];
        }

        return rest <= 1;
    }

}
