//*
// https://leetcode.com/problems/string-compression/description/
//
// Given an array of characters, compress it in-place.
//The length after compression must always be smaller than or equal to the original array.
//Every element of the array should be a character (not int) of length 1.
//After you are done modifying the input array in-place, return the new length of the array.
//Follow up:
//Could you solve it using only O(1) extra space?
// /
public class Solution2 {

    // Mine solution, Space O(1), compress in-place
    public int compress11(char[] chars) {
        if(chars == null || chars.length == 0) return 0;

        int counter = 0;
        char prev = Character.MIN_VALUE;
        int nextIndex = 0;

        for (int i = 0; i < chars.length; i++) {

            if(prev == Character.MIN_VALUE) {
                prev = chars[i];
                counter++;
                nextIndex++;
                continue;
            }

            if(prev == chars[i]){ // if charecter is the same, increase count and write this count to next index
                counter ++;
                //chars[i] = Character.MIN_VALUE;
                insertNumber(chars, nextIndex, counter);
            }
            else {
                if(counter > 1)  // write count to next index and increase next index to the length of the count
                    nextIndex += insertNumber(chars, nextIndex, counter);

                if(i!= nextIndex){
                    chars[nextIndex] = chars[i];
                    //chars[i] = Character.MIN_VALUE;
                }

                prev = chars[nextIndex];
                nextIndex ++;
                counter = 1;
            }
        }

        return chars.length;
    }

    int insertNumber(char[] arr, int i, int number) {
        String s = Integer.toString(number);
        for (int j = 0; j < s.length(); j++) {
            arr[i + j] = s.charAt(j);
        }

        return s.length();
    }

    // Not mine short solution
    public int compress(char[] chars) {
        int indexAns = 0, index = 0;
        while(index < chars.length){
            char currentChar = chars[index];
            int count = 0;
            while(index < chars.length && chars[index] == currentChar){
                index++;
                count++;
            }
            chars[indexAns++] = currentChar;
            if(count != 1)
                for(char c : Integer.toString(count).toCharArray())
                    chars[indexAns++] = c;
        }
        return indexAns;
    }
}
