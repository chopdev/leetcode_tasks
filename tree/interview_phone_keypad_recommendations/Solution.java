import java.util.ArrayList;
import java.util.List;

/* 
Telephone keypad has numbers 0 to 9 which are also related to letters on it
(1=abc, 2=def, 3=ghi, 4=jkl, 5=mno, 6=pqr, 7=stu, 8=vwx, 9=yz, 0=space).

While typing on number keys with the keypad the user sees suggestions of words that have those letters.
The suggestions come from a predefined dictionary.

For example, given a dictionary with the following words: "amazing", "age", "ball", "bid", "circle", "comedy", 
when the user types 132 then the word suggestions will be: "age" and "bid" 
(1 can represent ‘a’, ‘b’ or ‘c’ and key 3 can represent "g","h" or "i", 2 can represent "d", "e" or "f"). 
Note: this is an exact match of sequence numbers and words.

Write a function that returns recommended words by a sequence of numbers.


Follow ups:
How would you change this solution to not only display exact match of words given a sequence, but also those words that are prefixed with that sequence?.
How would you show the most popular words on top of your suggestions instead of alphabetically ordered? How would you define popular words?
How would you show at the top of all suggestions the best next word based on the history of what the user typed in the past, for example the user types the word “good” and the top suggestion for the next word may be “bye” based on the fact that the user types “good bye” the most in the past as opposed to other possible words like “good night”? The best next word is only determined considering the previous word, not the full sentence.
*/


/**
 * Brute force solution:
 * Translate numbers to words. Check every word in a set. 
 * Translate numbers to words - complexity O(3^N) as each additional number adds up to 3 more possible chars
 * Check every word in a set - is O(1) operation, but we need to do it for O(3^N) times, as we could have 3^N possible words
 * 
 * Trie solution:
 * Check if prefix exist for first letter (3 possible values)
 * THen recursively check if prefix exist for first letter + second letter
 * Do the same till the end, check that the word is ending in trie. Return word or null otherwise
 * 
 * Complexity: worst case complexity is the same as brute force, but in real world it's much better
 * 
 *  Alternatively, a tree can be built using numbers (9 of them). In the end nodes we will need to save the entire correct words
 */

public class RecommendationComponent {
    Trie trie;
    Map<Integer, List<Character>> numbToLetters;

    public RecommendationComponent(List<String> wordsDictionary) {
        buildTrie();
        buildLettersMap();
    }

    public List<String> getRecommendations(String numbers) {
        List<String> res = new ArrayList<>();
        findRecursive("", numbers, 0, res);
        return res;
    }

    public void findRecursive(String prefix, String numbers, int i, List<String> res) {
        if (i >= numbers.length()) {
            if (trie.exactMatch(prefix))
                res.add(prefix);
            return;
        }
        for (Character letter : numbToLetters.get(numbers.charAt(i) - '0')) {
            String newPrefix = prefix + letter;
            if (trie.hasPrefix(newPrefix)) {
                findRecursive(newPrefix, numbers, i+1, res);
            }
        }
    } 
}