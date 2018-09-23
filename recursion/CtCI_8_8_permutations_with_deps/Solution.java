import java.util.*;

/*
* Permutations with Dups: Write a method to compute all permutations of a string whose characters
are not necessarily unique. The list of permutations should not have duplicates.
* */
public class Solution {
    // Mine solution
    // Time O(N!)
    public List<String> getPermutatations(String s) {
        if(s == null) return null;
        HashMap<Character, Integer> hashMap = new HashMap<>();

        for (int i = 0; i < s.length(); i++) {
            char curr = s.charAt(i);
            if(hashMap.containsKey(curr))
                hashMap.put(curr, hashMap.get(curr) + 1);
            else
                hashMap.put(curr, 1);
        }

        return getPermutatations(hashMap);
    }

    private List<String> getPermutatations(Map<Character, Integer> map) {
        List<String> res = new ArrayList<>();
        if(map.size() == 0){
            res.add("");
            return res;
        }

        for (char key : map.keySet()) {
            Map<Character, Integer> temp = new HashMap<>(map);
            temp.put(key, temp.get(key) - 1);
            if(temp.get(key) <= 0)
                temp.remove(key);
            List<String> lists = getPermutatations(temp);
            for(String str : lists) {
                res.add(key + str);
            }
        }

        return res;
    }


    // Solution similar to book
    // Here we do not create temp hashmap, but remove/add elements
    public List<String> getPermutatations2222(String s) {
        if(s == null) return null;
        HashMap<Character, Integer> hashMap = new HashMap<>();

        for (int i = 0; i < s.length(); i++) {
            char curr = s.charAt(i);
            if(hashMap.containsKey(curr))
                hashMap.put(curr, hashMap.get(curr) + 1);
            else
                hashMap.put(curr, 1);
        }

        return getPermutatations2222(hashMap, s.length());
    }

    private List<String> getPermutatations2222(Map<Character, Integer> map, int size) {
        List<String> res = new ArrayList<>();
        if(size == 0){
            res.add("");
            return res;
        }

        for (char key : map.keySet()) {
            if(map.get(key) <= 0) continue;

            map.put(key, map.get(key) - 1);
            List<String> lists = getPermutatations2222(map, size - 1);
            map.put(key, map.get(key) + 1);
            for(String str : lists) {
                res.add(key + str);
            }
        }

        return res;
    }
}
