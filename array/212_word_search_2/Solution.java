import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;


/**
 *
 * https://leetcode.com/problems/word-search-ii/
 * 212. Word Search II
 *
 *
 * Given an m x n board of characters and a list of strings words, return all words on the board.
 *
 * Each word must be constructed from letters of sequentially adjacent cells, where adjacent cells are horizontally
 * or vertically neighboring. The same letter cell may not be used more than once in a word.
 *
 * Input: board = [["o","a","a","n"],["e","t","a","e"],["i","h","k","r"],["i","f","l","v"]], words = ["oath","pea","eat","rain"]
 * Output: ["eat","oath"]
 *
 * Input: board = [["a","b"],["c","d"]], words = ["abcb"]
 * Output: []
 *
 * Constraints:
 *
 * m == board.length
 * n == board[i].length
 * 1 <= m, n <= 12
 * board[i][j] is a lowercase English letter.
 * 1 <= words.length <= 3 * 104
 * 1 <= words[i].length <= 10
 * words[i] consists of lowercase English letters.
 * All the strings of words are unique.
 *




O(R*C + N*P)

[ a n a t ]
[ b o o s ]     [hanat, ooo, oooo, obn, ananas]
[ k i o l ]
[ k i a m ]

Hash<Char, Point> points

class Point {
    row,
    col
}

Depth first search


*/
public class Solution {

    /**
    *
    * My efficient solution
     * M*N - board size
     * W - words count
     * WL - average length of word
     *
     * O(M*N + M*N*W*WL) = O(M*N*W*WL)
     *
     * DFS method is not 4^WL because it won't do full dive with the length of WL on each branch of recursion
     *
     *
     * Space: M*N - points map, W - foundWords, WL+M*N - depth of recursion + seen points
     * O(M*N + W + WL + M*N) = O(W+WL+M*N)
     *
    * */
    class Point {
        int row;
        int col;
        char value;

        public Point(int row, int col, char value) {
            this.row = row;
            this.col = col;
            this.value = value;
        }

        @Override
        public boolean equals(Object p) {
            Point other = (Point)p;
            return this.row == other.row && this.col == other.col && this.value == other.value;
        }

        @Override
        public int hashCode() {
            return this.row * 10 + this.col * 100 + (this.value - 'a') * 1000;
        }
    }

    public List<String> findWords(char[][] board, String[] words) {
        Map<Character, List<Point>> points = new HashMap<>();
        for(int i = 0; i<board.length; i++)
            for(int j = 0; j<board[0].length; j++) {
                char letter = board[i][j];
                if (!points.containsKey(letter)) {
                    points.put(letter, new ArrayList<>());
                }
                points.get(letter).add(new Point(i, j, letter));
            }


        List<String> foundWords = new ArrayList();
        for (String word : words) {
            if (!points.containsKey(word.charAt(0)))
                continue;

            for (Point initial : points.get(word.charAt(0))) {
                boolean found = dfs(initial.row, initial.col, board, new HashSet<>(), word, 0);
                if (found) {
                    foundWords.add(word);
                    break;
                }
            }
        }

        return foundWords;
    }

    private boolean dfs(int row, int col, char[][] board, HashSet<Point> seen, String word, int currInd) {
        if (row < 0 || col < 0
                || row >= board.length || col >= board[0].length
                || board[row][col] != word.charAt(currInd)
                || seen.contains(new Point(row, col, board[row][col]))) {
            return false;
        }

        if (currInd == word.length() - 1) {
            return true;
        }

        seen.add(new Point(row, col, word.charAt(currInd)));

        return dfs(row, col + 1, board, seen, word, currInd + 1) ||
                dfs(row, col - 1, board, seen, word, currInd + 1) ||
                dfs(row - 1, col, board, seen, word, currInd + 1) ||
                dfs(row + 1, col, board, seen, word, currInd + 1);
    }


    /**
     * Not mine solution
     * https://leetcode.com/problems/word-search-ii/discuss/59780/Java-15ms-Easiest-Solution-(100.00)
     *
     * Complexity analysis
     * https://leetcode.com/problems/word-search-ii/discuss/156559/Java-(40ms)-Solution-with-Complexity-Analysis
     *
     * Time: O(M * N * WL * W)
     *
     * Space: O(WL * W)
     * O(WL) - The recursive stack can grow at most to wl layers.
     * O(WL * W) - In the worst case when all words start with different characters, the trie has wl * l nodes. Also, since each word
     * is stored in a leaf node, all the leaf nodes require wl * l memory.
     * */
    public List<String> findWords2222(char[][] board, String[] words) {
        List<String> res = new ArrayList<>();
        TrieNode root = buildTrie(words);
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                dfs(board, i, j, root, res);
            }
        }
        return res;
    }

    public void dfs(char[][] board, int i, int j, TrieNode p, List<String> res) {
        char c = board[i][j];
        if (c == '#' || p.next[c - 'a'] == null) return;
        p = p.next[c - 'a'];
        if (p.word != null) {   // found one
            res.add(p.word);
            p.word = null;     // de-duplicate
        }

        board[i][j] = '#';
        if (i > 0) dfs(board, i - 1, j ,p, res);
        if (j > 0) dfs(board, i, j - 1, p, res);
        if (i < board.length - 1) dfs(board, i + 1, j, p, res);
        if (j < board[0].length - 1) dfs(board, i, j + 1, p, res);
        board[i][j] = c;
    }

    public TrieNode buildTrie(String[] words) {
        TrieNode root = new TrieNode();
        for (String w : words) {
            TrieNode p = root;
            for (char c : w.toCharArray()) {
                int i = c - 'a';
                if (p.next[i] == null) p.next[i] = new TrieNode();
                p = p.next[i];
            }
            p.word = w;
        }
        return root;
    }

    class TrieNode {
        TrieNode[] next = new TrieNode[26];
        String word;
    }

    /**
     *
     * Another Trie solution
     * https://leetcode.com/problems/word-search-ii/discuss/59784/My-simple-and-clean-Java-code-using-DFS-and-Trie
     * */
}
