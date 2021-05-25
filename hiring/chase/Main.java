public class Main {
    public static void main(String[] args) {
        System.out.println(decodeCount("222"));
        System.out.println(decodeCount("262"));
        System.out.println(decodeCount("101"));
        System.out.println(decodeCount("1010"));
        System.out.println(decodeCount("2020"));
        System.out.println(decodeCount("2020"));
        System.out.println(decodeCount("02020"));

        System.out.println();
        // 3
        System.out.println(decodeCount("1201234"));
      }

      public static int decodeCount(String str) {
        if (str.length() == 1 && str.charAt(0) == '0') {
          return 0;
        }
        return decode(str, 0, new int[str.length()]);
      }

      public static int decode(String str, int index, int[] memo) {
        if (index >= str.length() - 1) {
          return 1;
        }

        if (memo[index] != 0) {
          return memo[index];
        }
        
        int twoLetter = Integer.parseInt(str.substring(index, index + 2));
        if (twoLetter <= 26 && twoLetter >= 10) {
          memo[index] += decode(str, index + 2, memo);
        }
       
        if (twoLetter == 10 || twoLetter == 20)
          return memo[index];
        
        memo[index] += decode(str, index + 1, memo);
        int oneLetter = Integer.parseInt(str.substring(index, index + 1));
        if (oneLetter == 0) {
          return 0;
        }
        
        return memo[index];
      }
      

      // https://leetcode.com/problems/decode-ways/
      // interview version
      public static int decodeCount(String str, int index) {
        if (index >= str.length() - 1) {
          return 1;
        }
        
        int count = 0;
        int twoLetter = Integer.parseInt(str.substring(index, index + 2));
        if (twoLetter <= 26 && twoLetter >= 10) {
          count += decodeCount(str, index + 2);
        }
       
        if (twoLetter == 10 || twoLetter == 20)
          return count;
        
        count += decodeCount(str, index + 1);
        int oneLetter = Integer.parseInt(str.substring(index, index + 1));
        if (oneLetter == 0) {
          return 0;
        }
        
        return count;
      }

      public static String capitalizeWords(String sent) {
        String[] words = sent.split(" ");
        StringBuilder res = new StringBuilder();
        for(String word : words) {
          res.append(word.substring(0, 1).toUpperCase() + word.substring(1));
          res.append(" ");
        }
        return res.toString();
      }
}