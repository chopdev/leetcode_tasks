/*
Binary to String: Given a real number between 8 and 1 (e.g., 0.72) that is passed in as a double,
print the binary representation. If the number cannot be represented accurately in binary with at
most 32 characters, print "ERROR:'
*/
public class Solution {

    // Mine solution
    public String binaryToString(double val) {
        if(val > 1 || val < 0)
            return "ERROR";
        long integer = (long) val;
        double dec = val;

        int thirtyOne = (~0 >>> 1); // binary number 32 bits, 1 zero and 31 ones  01111111...1111
        while (dec > integer) {
            integer = (long) (dec *10);
            dec *= 10;
            if(integer > thirtyOne) 
                return "ERROR";
        }

        StringBuilder res = new StringBuilder(".");
        int resVal = (int)integer;
        for (int i = 32; i >= 0 ; i--) {
            res.append((resVal>>i) & 1);
        }

        return res.toString();
    }


    // Not mine solution, works not correct because of accuracy of double
    public String binaryToStringCorrect(double val) {
        if(val > 1 || val < 0) return "ERROR";

        StringBuilder res = new StringBuilder(".");
        while (val > 0) {
            if(res.length() >= 32) return "ERROR";
            double temp = val * 2;
            if(temp >=1) {
                res.append(1);
                val = temp - 1;
            }
            else {
                res.append(0);
                val = temp;
            }
        }

        return res.toString();
    }
}
