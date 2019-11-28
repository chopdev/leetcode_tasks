import java.util.HashMap;
import java.util.Map;

/**
 1189. Maximum Number of Balloons
 https://leetcode.com/problems/maximum-number-of-balloons/

 Given a string text, you want to use the characters of text to form as many instances of the word "balloon" as possible.
 You can use each character in text at most once. Return the maximum number of instances that can be formed.

 Example 1:
 Input: text = "nlaebolko"
 Output: 1

 Example 2:
 Input: text = "loonbalxballpoon"
 Output: 2

 Example 3:
 Input: text = "leetcode"
 Output: 0

 * */
public class Solution {

    // My O(N) time, O(1) space solution
    public int maxNumberOfBalloons(String text) {
        Map<Character, Integer> symbToCount = getInitialMap();
        for(Character ch : text.toCharArray()) {
            if(symbToCount.containsKey(ch)) {
                symbToCount.put(ch, symbToCount.get(ch) + 1);
            }
        }

        return getNumberOfWords(symbToCount);
    }

    private Map<Character, Integer> getInitialMap() {
        Map<Character, Integer> map = new HashMap<>();
        map.put('b', 0);
        map.put('a', 0);
        map.put('l', 0);
        map.put('o', 0);
        map.put('n', 0);
        return map;
    }

    private int getNumberOfWords(Map<Character, Integer> symbToCount) {
        int numb = Integer.MAX_VALUE;
        numb = Math.min(symbToCount.get('b'), numb);
        numb = Math.min(symbToCount.get('a'), numb);
        numb = Math.min(symbToCount.get('l') / 2, numb);
        numb = Math.min(symbToCount.get('o') / 2, numb);
        numb = Math.min(symbToCount.get('n'), numb);
        return numb;
    }


    // similar, but with array
    // https://leetcode.com/problems/maximum-number-of-balloons/discuss/382401/WithComments-StraightForward-Java-Simple-count-of-chars
    public int maxNumberOfBalloons2222(String text) {
        int[] chars = new int[26]; //count all letters
        for (char c : text.toCharArray()) {
            chars[c - 'a']++;
        }
        int min = chars[1];//for b
        min = Math.min(min, chars[0]);//for a
        min = Math.min(min, chars[11] / 2);// for l /2
        min = Math.min(min, chars[14] / 2);//similarly for o/2
        min = Math.min(min, chars[13]);//for n
        return min;
    }

    // https://leetcode.com/problems/maximum-number-of-balloons/discuss/382940/Java-count-ballon-char-and-select-min-of-them
    public int maxNumberOfBalloons3333(String text) {
        int b = 0, a = 0, l = 0, o = 0, n = 0;

        for(char ch : text.toCharArray()){
            switch(ch){
                case 'b': ++b;
                    break;
                case 'a': ++a;
                    break;
                case 'l': ++l;
                    break;
                case 'o': ++o;
                    break;
                case 'n': ++n;
                    break;

            }
        }

        return Math.min(Math.min(o/2, l/2), Math.min(Math.min(b, a), n));
    }
}
