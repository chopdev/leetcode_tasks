import java.util.*;
/*
* Permutations without Dups: Write a method to compute all permutations of a string of unique
characters.
* */
public class Solution {

    // Complexity O(N!) why? for first position we can have N chars, for second N-1 ...
    // Space O(N) deepness of recursion + array



    // LOOK book easier solution is there
    // Mine solution
    // Explanation
    // A -> A
    // AB -> AB, BA
    // ABC -> ABC, ACB,  BAC, BCA,  CAB, CBA
    // SO we put each char of the string at the beginning and combine it with permutations of remaining chars
    // for first position we can have n chars, for second n - 1, ...
    public List<String> getPermutations(String s) {
        if(s == null) return null;
        Set<Character> chars = new HashSet<>();
        for (int i = 0; i < s.length(); i++) {
            chars.add(s.charAt(i));
        }
        List<LinkedList<Character>> petmutations = getPermutations(chars);
        List<String> res = new ArrayList<>();

        for (LinkedList<Character> ch : petmutations) {
            StringBuilder builder = new StringBuilder();
            for (char c : ch) {
                builder.append(c);
            }
            res.add(builder.toString());
        }

        return res;
    }

    private List<LinkedList<Character>> getPermutations(Set<Character> chars) {
        List<LinkedList<Character>> res = new ArrayList<>();
        if(chars.size() == 1) {
            LinkedList<Character> temp = new LinkedList<>();
            temp.add((char)chars.toArray()[0]);
            res.add(temp);
            return res;
        }

        for (char curr : chars) {
            Set<Character> temp = new HashSet<>(chars);
            temp.remove(curr);
            List<LinkedList<Character>> lists = getPermutations(temp);
            for (LinkedList<Character> list : lists) {
                list.add(0, curr);
                res.add(list);
            }
        }
        return res;
    }


    // Solution after looking in book, but in book used other approach
    // Here approach is the same, but we work with strings directly
    public List<String> getPermutations2222(String s) {
        if(s == null) return null;

        List<String> list = new ArrayList<>();
        if(s.length() == 1) {
            list.add(s);
            return list;
        }

        for (int i = 0; i < s.length(); i++) {
            char curr = s.charAt(i);
            String reminder = removeCharAt(s, i);
            List<String> temp = getPermutations(reminder);
            for(String  perms : temp)
                list.add(curr + perms);
        }

        return list;
    }

    String removeCharAt(String s, int i) {
        String s1 = s.substring(0, i);
        String s2 = s.substring(i + 1);
        return s1 + s2;
    }
}
