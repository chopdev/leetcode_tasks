import java.util.Arrays;
import java.util.List;
import java.util.PriorityQueue;

/**
 * 1405. Longest Happy String
 * https://leetcode.com/problems/longest-happy-string/
 *
 * A string s is called happy if it satisfies the following conditions:
 *
 * s only contains the letters 'a', 'b', and 'c'.
 * s does not contain any of "aaa", "bbb", or "ccc" as a substring.
 * s contains at most a occurrences of the letter 'a'.
 * s contains at most b occurrences of the letter 'b'.
 * s contains at most c occurrences of the letter 'c'.
 * Given three integers a, b, and c, return the longest possible happy string. If there are multiple longest happy strings,
 * return any of them. If there is no such string, return the empty string "".
 *
 * A substring is a contiguous sequence of characters within a string.
 *
 * Example 1:
 *
 * Input: a = 1, b = 1, c = 7
 * Output: "ccaccbcc"
 * Explanation: "ccbccacc" would also be a correct answer.
 *
 *
 * Example 2:
 *
 * Input: a = 7, b = 1, c = 0
 * Output: "aabaa"
 * Explanation: It is the only correct answer in this case.
 *
 * Constraints:
 *
 * 0 <= a, b, c <= 100
 * a + b + c > 0
 * */
public class Solution {

    class Entry {
        char value;
        int count;

        public Entry(char value, int count) {
            this.count = count;
            this.value = value;
        }
    }


    // My solution, beats 88%
    // sorting is O(1), time & space O(A+B+C)
    public String longestDiverseString2222(int a, int b, int c) {
        Entry aa = new Entry('a', a);
        Entry bb = new Entry('b', b);
        Entry cc = new Entry('c', c);

        List<Entry> entries = Arrays.asList(aa, bb, cc);
        entries.sort((t1, t2) -> Integer.compare(t2.count, t1.count));

        if (entries.get(0).count == 0) return "";

        StringBuilder res = new StringBuilder();

        while (entries.get(0).count > 0) {
            Entry largest = entries.get(0);
            Entry smaller = entries.get(1);

            if (largest.count > smaller.count) {
                if (largest.count > 1) {
                    res.append(largest.value).append(largest.value);
                    largest.count -= 2;
                }
                else {
                    res.append(largest.value);
                    largest.count --;
                }

                if (smaller.count > 0) {
                    res.append(smaller.value);
                    smaller.count --;
                } else if (largest.count > 0) {
                    break;
                }
            } else {
                res.append(largest.value);
                largest.count --;
                res.append(smaller.value);
                smaller.count --;
            }

            entries.sort((t1, t2) -> Integer.compare(t2.count, t1.count));
        }

        return res.toString();
    }


    // My implementation, idea is inspired from the below link
    // https://leetcode.com/problems/longest-happy-string/solutions/564254/java-greedy-on-frequency-with-priorityqueue-o-nlogn/
    public String longestDiverseString(int a, int b, int c) {
        int[] counts = new int[3];
        counts[0] = a;
        counts[1] = b;
        counts[2] = c;

        // interesting idea to use array from the closure, although some "class Node {char ch; int count; }" will be cleaner
        PriorityQueue<Integer> queue = new PriorityQueue<>((ind1, ind2) -> Integer.compare(counts[ind2], counts[ind1]));
        queue.offer(0);
        queue.offer(1);
        queue.offer(2);
        StringBuilder res = new StringBuilder();

        for (int i = 0; i < a + b + c; i++) {
            int maxCountIndex = queue.poll();
            char currentChar = getCharFromIndex(maxCountIndex);

            if (res.length() < 2 || res.charAt(i - 1) != currentChar || res.charAt(i - 2) != currentChar) {
                res.append(currentChar);
                counts[maxCountIndex] --;
                if (counts[maxCountIndex] > 0) queue.offer(maxCountIndex);
            } else {
                if (queue.isEmpty())
                    return res.toString(); // max possible string we can build

                int nextMaxCountIndex = queue.poll();
                char nextMaxCountChar = getCharFromIndex(nextMaxCountIndex);
                res.append(nextMaxCountChar);
                counts[nextMaxCountIndex] --;
                if (counts[maxCountIndex] > 0) queue.offer(maxCountIndex);
                if (counts[nextMaxCountIndex] > 0) queue.offer(nextMaxCountIndex);
            }
        }

        return res.toString();
    }

    private char getCharFromIndex(int ind) {
        return ind == 0 ? 'a' : ind == 1 ? 'b' : 'c';
    }
}
