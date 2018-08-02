import java.util.Hashtable;

/**
 * implement an algorithm to determine if a string has all unique characters.
 * What if you cannot use additional data structures?
 *
 * Solutions: https://www.geeksforgeeks.org/determine-string-unique-characters/
 */
public class Solution {

    // Mine solution
    // Commplexity - O(N), space O(N)
    // Solution with sorting can has Complexity O(N*log(N))
    public boolean IsUnique(String str) {

        Hashtable<Character, Boolean> hashtable = new Hashtable<>();

        for (int i = 0; i < str.length(); i++) {
            char curr = str.charAt(i);
            if(hashtable.containsKey(curr)) return false;

            hashtable.put(curr, true);
        }

        return true;
    }

    // Not mine solution
    // Commplexity - O(N), space O(1)
    /// https://www.asciitable.com/   - ASCII table
    // 0 starts from 48 ASCII
    // A starts form 65 ASCII
    // a starts form 97 ASCII
    // int contains 32 bits - so we can use only limited range of symbols
    // thats why THIS FUNCTION valid only for a-z symbols
    // we can also use boolean array to support bigger range
    public boolean IsUnique2(String str) {

        int vector = 0;

        for (int i = 0; i < str.length(); i++) {
            int curr = str.charAt(i) - 'a'; // 'a' - 'a' = 0, 'b' - 'a' = 1 ...
            int offset = 1<<curr; // left offset of 1 by current number

            if((vector & offset) > 0) // if the bit is already set, character was seen before
                return false;

            vector |= offset; // set bit with specified offset
        }

        return true;
    }
}
