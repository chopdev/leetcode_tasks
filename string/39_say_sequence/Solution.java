package chopdev.say_sequence_38;

import java.util.*;
import java.lang.*;

/*
38. Count and Say
https://leetcode.com/problems/count-and-say/
The count-and-say sequence is the sequence of integers beginning as follows:
1, 11, 21, 1211, 111221, ...
1 is read off as "one 1" or 11.
11 is read off as "two 1s" or 21.
21 is read off as "one 2, then one 1" or 1211.
Given an integer n, generate the nth sequence.

Note: The sequence of integers will be represented as a string.
*/
public class Solution {

  public String countAndSay(int n) {
    StringBuilder sb = new StringBuilder();
    int count = 0;
    String number = "1";
    int previous = 0;

    for (int i = 0; i < n - 1; i++) { // remove first number from iteration
      for (int j = 0; j < number.length(); j++) {
        int current = number.charAt(j) - '0';
        if(current != previous && j != 0) {
          sb.append(count).append(previous);
          count = 0;
        }

        count ++;
        previous = current;
      }

      if(count > 0)
      {
        sb.append(count).append(previous);
      }
      count = 0;
      previous = 0;
      number = sb.toString();
      sb = new StringBuilder();
    }

    return number;
  }
}