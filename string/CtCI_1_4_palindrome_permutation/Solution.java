import java.util.HashMap;
import java.util.Map;

//*
// Palindrome Permutation: Given a string, write a function to check if it is a permutation of a palindrome.
//A palindrome is a word or phrase that is the same forwards and backwards. A permutation
//is a rea rearrangement of letters. The palindrome does not need to be limited to just dictionary words.
// */

// Questions to interviewer: How big can be the string? how to deal whitespaces?
public class Solution {

    // Mine solution (without hints)
    // Complexity O(N), Space: O(N)
    public boolean IsPolindrome(String str) {
        if(str == null) return true;

        HashMap<Character, Integer> htable = new HashMap<>();

        for (int i = 0; i < str.length();i++) {
            char curr = Character.toLowerCase(str.charAt(i));
            if(curr == ' ')
                continue;

            Integer val = htable.get(curr);
            htable.put(curr, val == null ? 1 : val + 1); // add in hashtable count of entrances
        }

        int oddCount = 0;

        for (Map.Entry<Character, Integer> entry : htable.entrySet())
        {
            if(entry.getValue() % 2 != 0)
                oddCount++;
        }

        return  oddCount <= 1;
    }

    // Mine solution (without hints)
    // // Complexity O(N), Space: O(1)
    public boolean IsPolindrome2(String str) {
        str = str.toLowerCase();
        int vector = 0;
        int lettersCount = 0;

        // make XOR to each letter
        for (int i = 0; i < str.length(); i++) {
            char val = str.charAt(i);
            if(val == ' ')
                continue;

            lettersCount ++;
            vector ^= val - '0';
        }

        // if count of letters is even, vector shold be null to have polindrome
        if(lettersCount % 2 == 0 && vector != 0)
            return false;

        if(vector == 0)
            return true;

        for (int i = 0; i < str.length(); i++) {
            char val = str.charAt(i);
            if(val == ' ')
                continue;

            if(vector == (val - '0')) // if we found one odd letter - return true, like "lloll" o is odd or "lloooll"
                return true;
        }

        return false;
    }
}
