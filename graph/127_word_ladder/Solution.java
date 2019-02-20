import java.util.*;

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

    // My solution
    // But I used DFS here, which is not suitable for closest path finding
    // Anyway Idea with using of graph was good here
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

        return dfs(wordList.size() - 1, graph, endInd, new HashSet<>());
    }

    int dfs(int currInd, ArrayList<Integer>[] graph, int destInd, HashSet<Integer> visited) {
        if(currInd == destInd) {
            return 1;
        }
        visited.add(currInd);
        int min = Integer.MAX_VALUE;
        for (int child : graph[currInd]) {
            if(!visited.contains(child)) {
                min = Math.min(dfs(child, graph, destInd, visited) + 1, min);
            }
        }

        visited.remove(currInd);
        return min == Integer.MAX_VALUE ? 0 : min;
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


    // Not mine solution
    // https://leetcode.com/problems/word-ladder/discuss/40717/Another-accepted-Java-solution-(BFS)
    // O(M*N) time
    public int ladderLength2222(String start, String end, List<String> wordList) {
        Set<String> dict = new HashSet<>(wordList);
        if(!dict.contains(end)) return 0;
        // Use queue to help BFS
        Queue<String> queue = new LinkedList<String>();
        queue.add(start);
        queue.add(null);

        // Mark visited word
        Set<String> visited = new HashSet<String>();
        visited.add(start);

        int level = 1;

        while (!queue.isEmpty()) {
            String str = queue.poll();

            if (str != null) {
                // Modify str's each character (so word distance is 1)
                for (int i = 0; i < str.length(); i++) {
                    char[] chars = str.toCharArray();

                    for (char c = 'a'; c <= 'z'; c++) {
                        chars[i] = c;

                        String word = new String(chars);

                        // Found the end word
                        if (word.equals(end)) return level + 1;

                        // Put it to the queue
                        if (dict.contains(word) && !visited.contains(word)) {
                            queue.add(word);
                            visited.add(word);
                        }
                    }
                }
            } else {
                level++;

                if (!queue.isEmpty()) {
                    queue.add(null);
                }
            }
        }

        return 0;
    }

    // Mine interpretation of upper solution, but bidirectional BFS
    public int ladderLength3333(String start, String end, List<String> wordList) {
        Set<String> dict = new HashSet<>(wordList);
        if(!dict.contains(end)) return 0;

        Queue<String> qStart = new LinkedList<>();
        qStart.add(start);
        qStart.add(null);
        Queue<String> qEnd = new LinkedList<>();
        qEnd.add(end);
        qEnd.add(null);

        HashSet<String> startVisited = new HashSet<>();
        startVisited.add(start);
        HashSet<String> endVisited = new HashSet<>();
        endVisited.add(end);

        int len = 1;
        Queue<String> currQ = qStart;
        HashSet<String> currVisited = startVisited;
        HashSet<String> otherVisited = startVisited;

        while (!qStart.isEmpty() && !qEnd.isEmpty()) {
            if(qStart.size() > qEnd.size()) {
                currQ = qEnd;
                currVisited = endVisited;
                otherVisited = startVisited;
            }else {
                currQ = qStart;
                currVisited = startVisited;
                otherVisited = endVisited;
            }

            String str = currQ.poll();
            if(str == null) {
                len++;
                if(!currQ.isEmpty()) currQ.add(null); // level is ended, mark new level
                continue;
            }


            for (int i = 0; i < str.length(); i++) {
                char[] letters = str.toCharArray();

                for (char j = 'a'; j <= 'z'; j++) {
                    letters[i] = j;
                    String modified = String.valueOf(letters);
                    if(otherVisited.contains(modified)) return len + 1;

                    if(dict.contains(modified) && !currVisited.contains(modified)) {
                        currQ.add(modified);
                        currVisited.add(modified);
                    }
                }
            }
        }

        return 0;
    }
}
