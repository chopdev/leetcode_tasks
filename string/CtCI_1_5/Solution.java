//**
// One Away: There are three types of edits that can be performed on strings: insert a character,
//remove a character, or replace a character. Given two strings, write a function to check if they are
//one edit (or zero edits) away.
//EXAMPLE
//pale, ple -> true
//pales, pale -> true
//pale, bale -> true
//pale, bake -> false
// /
// This problem can be also soleved by using HashTable<Character, Boolean> - TIme: O(N) Space O(N)
// or by Sorting - Time O(N*log(N))
public class Solution {

    //*
    // Mine solution Time complexity O(N) where N - longest string length
    // Space complexity O(1)
    // This can be solved in 2 steps: if length is equal but only replacements exist or loop for detecting additional letter
    // If combine this steps in one loop, u will get solution below
    // */
    boolean oneAway(String s1, String s2){
        if(s1 == null || s2 == null) return false;

        String longer = s1.length() > s2.length() ? s1 : s2;
        String shorter = s1.length() > s2.length() ? s2 : s1;

        // if length diff is bigger than 1
        if(longer.length() - shorter.length() > 1) return false;

        int offset = 0, diff = 0;
        boolean equalLength = longer.length() == shorter.length();

        for (int i = 0; i < shorter.length(); i++) {
            if(longer.charAt(offset + i) != shorter.charAt(i)) {
                if(!equalLength){
                    offset ++; // offset is always 0 if length are equal
                    i--;        // in other case skip one letter in longer string and check other letters
                }

                diff++;

                if(diff > 1)
                    return false;
            }
        }

        return true;
    }
}
