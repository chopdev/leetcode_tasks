import javafx.util.Pair;

import java.util.Dictionary;
import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;

/*
767. Reorganize String
https://leetcode.com/problems/reorganize-string/

Given a string s, rearrange the characters of s so that any two adjacent characters are not the same.
Return any possible rearrangement of s or return "" if not possible.

Example 1:
Input: s = "aab"
Output: "aba"
Example 2:

Input: s = "aaab"
Output: ""

Constraints:
1 <= s.length <= 500
s consists of lowercase English letters.
*/
public class Solution {

    // My Solution
    // We want to use characters with the biggest frequency first, so we can return possible option. e.g. "aaabb" -> "abab", we cannot take "b" first "babaa"
    // time ~O(N*log(N))
    public String reorganizeString(String s) {
        PriorityQueue<Pair<Character, Integer>> heap = getCharToCountHeap(s);
        StringBuilder res = new StringBuilder();
        for (int i=0; i<s.length(); i++) {
            Pair<Character, Integer> candidate = heap.poll();
            Character lastChar = i > 0 ? res.charAt(i - 1) : ' ';
            if (candidate.getKey().equals(lastChar)) {
                if (heap.isEmpty()) return "";
                Pair<Character, Integer> nextMax = heap.poll();
                res.append(nextMax.getKey());
                if (nextMax.getValue() > 1) heap.offer(new Pair<>(nextMax.getKey(), nextMax.getValue() - 1));
                heap.offer(candidate);
            } else {
                res.append(candidate.getKey());
                if (candidate.getValue() > 1) heap.offer(new Pair<>(candidate.getKey(), candidate.getValue() - 1));
            }
        }
        return res.toString();
    }

    private PriorityQueue<Pair<Character, Integer>> getCharToCountHeap(String s) {
        Map<Character, Integer> charToCount = new HashMap<>();

        for (Character ch : s.toCharArray()) {
            charToCount.put(ch, charToCount.getOrDefault(ch, 0) + 1);
        }

        PriorityQueue<Pair<Character, Integer>> heap = new PriorityQueue<>((x, y) -> Integer.compare(y.getValue(), x.getValue()));
        for (Character ch : charToCount.keySet()) {
            heap.offer(new Pair<>(ch, charToCount.get(ch)));
        }
        return heap;
    }
}
