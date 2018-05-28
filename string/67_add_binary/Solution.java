package chopdev.add_binary_67;

import java.util.*;
import java.lang.*;

/*
67. Add Binary
https://leetcode.com/problems/add-binary/
Given two binary strings, return their sum (also a binary string).
For example,
a = "11"
b = "1"
Return "100".
*/
public class Solution {

  public String addBinary(String a, String b) {
    StringBuilder result = new StringBuilder();
    String st1 = new StringBuilder(a).reverse().toString();
    String st2 = new StringBuilder(b).reverse().toString();

    int maxLength = st1.length() > st2.length() ? st1.length() : st2.length();
    int carry = 0;
  
    for (int i = 0; i < maxLength; i++) {
      int first = getSafely(st1, i);
      int second = getSafely(st2, i);
      int sum = first + second + carry;

      if(sum == 3)
      {
        carry = 1;
        result.append('1');
      }
      else if(sum == 2)
      {
        carry = 1;
        result.append('0');
      }
      else if(sum == 1)
      {
        carry = 0;
        result.append('1');
      }
      else {
        carry = 0;
        result.append('0');
      }
    }

    if(carry == 1)
      result.append('1');

    return result.reverse().toString();
  }

  private int getSafely(String arr, int i)
  {
    if(arr.length() - 1 >= i)
      return arr.charAt(i) - '0'; // to convert to number in string, not ascii code
    
    return 0;
  }


  // Good solution
    // https://discuss.leetcode.com/topic/13698/short-ac-solution-in-java-with-explanation/2
    public String addBinary1(String a, String b) {
      if (a.length()==0 && b.length()==0) return "";
      StringBuilder result = new StringBuilder();
      int i=a.length()-1, j=b.length()-1, remainder=0;
      while (i>=0 || j>=0) {
          int res = 0;
          // about a.charAt(i)-'0';
          //http://stackoverflow.com/questions/4318263/java-subtract-0-from-char-to-get-an-int-why-does-this-work
          if (i>=0) {
              res += a.charAt(i)-'0';
              i--;
          }
          if (j>=0) {
              res += b.charAt(j)-'0';
              j--;
          }
          res+=remainder;
          result.append(res%2);
          remainder=res/2;
      }
      if (remainder==1) result.append(1);
      return result.reverse().toString();
  }
}