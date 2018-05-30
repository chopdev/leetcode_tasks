package chopdev.say_sequence_38;

import java.util.*;
import java.lang.*;

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