import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 127. Word Ladder
https://leetcode.com/problems/word-ladder/

 Given two words (beginWord and endWord), and a dictionary's word list, find the length of shortest transformation sequence from beginWord to endWord, such that:

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
        ArrayList<String>[] graph = new ArrayList[wordList.size()];
        for (int i = 0; i < graph.length; i++) {
            graph[i] = new ArrayList<>();
        }

        
    }

}
