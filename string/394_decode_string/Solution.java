/*
394. Decode String
https://leetcode.com/submissions/detail/427756691/

 "3[a2[c]]"
 "accaccacc"

 "f3[a2[ccc2[y]g]]dfdf"
 
 yy -> cccyy
 cccyycccyy - > acccyycccyy
 acccyycccyyacccyycccyyacccyycccyy
*/

/*
My solution
O(K_max*N) time
O(K_max*N) space
*/
class Solution {
    public String decodeString(String s) {
        return decodeLayer(s, 1);
    }
    
    private String decodeLayer(String s, int repeats) {
        StringBuilder res = new StringBuilder();
        for (int i=0; i< s.length(); i++) {
            if (Character.isLetter(s.charAt(i))) {
                res.append(s.charAt(i));
                continue;
            }
            String numb = getNumber(s, i);
            String toDecode = getInsideBrackets(s, i + numb.length());
            res.append(decodeLayer(toDecode, Integer.parseInt(numb)));
            i += numb.length() + toDecode.length() + 1; // number length + 1 bracket + length of content inside brackets
        }
    
        StringBuilder decoded = new StringBuilder();
        for (int r = 0; r < repeats; r ++) {
            decoded.append(res.toString());
        }
    
        return decoded.toString();
    }

    private String getNumber(String s, int begin) {
        for (int i= begin; i< s.length(); i++) {
            if (s.charAt(i) >= '0' && s.charAt(i) <= '9')
                continue;
            return s.substring(begin, i);
        }
        throw new IllegalArgumentException("incorrect string");
    }
    
    private String getInsideBrackets(String s, int begin) {
        if (s.charAt(begin) != '[') {
            throw new IllegalArgumentException("begin");
        }
        int open = 1;
        for (int i=begin + 1; i< s.length(); i++) {
            if (s.charAt(i) == '[') 
                open ++;
            else if (s.charAt(i) == ']')
                open --;
    
            if (open == 0) {
                return s.substring(begin + 1, i);
            }
        }
        throw new IllegalArgumentException("incorrect string");
    }


    /// Not my solution
    // shorter, but idea is the same, recursion is changed by stacks
    // https://leetcode.com/problems/decode-string/discuss/87534/Simple-Java-Solution-using-Stack
    public String decodeString2222(String s) {
        String res = "";
        Stack<Integer> countStack = new Stack<>();
        Stack<String> resStack = new Stack<>();
        int idx = 0;
        while (idx < s.length()) {
            if (Character.isDigit(s.charAt(idx))) {
                int count = 0;
                while (Character.isDigit(s.charAt(idx))) {
                    count = 10 * count + (s.charAt(idx) - '0');
                    idx++;
                }
                countStack.push(count);
            }
            else if (s.charAt(idx) == '[') {
                resStack.push(res);
                res = "";
                idx++;
            }
            else if (s.charAt(idx) == ']') {
                StringBuilder temp = new StringBuilder (resStack.pop());
                int repeatTimes = countStack.pop();
                for (int i = 0; i < repeatTimes; i++) {
                    temp.append(res);
                }
                res = temp.toString();
                idx++;
            }
            else {
                res += s.charAt(idx++);
            }
        }
        return res;
    }
}