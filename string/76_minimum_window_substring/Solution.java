import java.util.HashMap;

public class Solution {
     public String minWindow(String s, String t) {
         if(s == null || t == null) return null;

         HashMap<Character, Integer> map = new HashMap<>();
         for (int i = 0; i < t.length(); i++) {
             if(map.containsKey(t.charAt(i)))
                map.put(t.charAt(i), map.get(t.charAt(i)) + 1);
             else
                 map.put(t.charAt(i), 1);
         }

         int end = 0, start = 0, head = 0, length = Integer.MAX_VALUE, count = 0;

         while (end < s.length())
         {
             Character currChar = s.charAt(end);
            if(map.containsKey(currChar)) {
                map.put(currChar, map.get(currChar) - 1);
                if(map.get(currChar) >= 0)
                    count++;
            }

            while (count == t.length()) {
                if(length > end - start) {
                    length = end - start;
                    head = start;
                }
                currChar = s.charAt(start);
                if(map.containsKey(currChar)) {
                    map.put(currChar, map.get(currChar) + 1);
                    if(map.get(currChar) > 0)
                        count--; // invalidate
                }
                start++;
            }

            end++;
         }

         return length == Integer.MAX_VALUE ? "" : s.substring(head, head + length + 1);
     }
}
