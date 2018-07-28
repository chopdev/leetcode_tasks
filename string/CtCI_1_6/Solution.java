public class Solution {

    public String shorthand(String str) {
        if(str == null || str.length() == 0) return str;

        StringBuilder res = new StringBuilder();
        char curr = Character.MIN_VALUE;
        int count = 0;

        for (int i = 0; i < str.length(); i++) {
            char charI = Character.toLowerCase(str.charAt(i));
            if(count == 0) {
                count ++;
                curr = charI;
                continue;
            }

            if(curr != charI){
                updateStringBuilder(res, count, curr);

                curr = charI;
                count = 1;
            }
            else
                count ++;
        }

        updateStringBuilder(res, count, curr);

        return res.toString();
    }

    private void  updateStringBuilder(StringBuilder res, int count, char curr){
        if(count == 1)
            res.append(curr);
        else {
            res.append(count);
            res.append(curr);
        }
    }
}
