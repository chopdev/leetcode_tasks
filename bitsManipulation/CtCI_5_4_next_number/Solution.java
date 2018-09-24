/**
 * Next Number: Given a positive integer, print the next smallest and the next largest number that
 have the same number of 1 bits in their binary representation.
 */
public class Solution {

    // Mine solution, time O(1)
    // solution in the book is harder, but can be applied to both larger and smaller
    // 0100 1110 -> 0101 0111
    public int getNextLargest(int numb) {
        if(numb == Integer.MAX_VALUE || numb == 0) return 0;

        int smBit = getSmallestBit(numb);
        int mask = 1 << smBit;

        int checkNumber = Integer.MAX_VALUE - numb;
        if(checkNumber <= mask)
            return 0; // number of type 1111 000 , where mask == 0001 000 , we can't get next largest with the same number of bits

        numb = numb + mask;  //   1000 1110 + 0000 0010 = 1001 0000
        int newSmallest = getSmallestBit(numb);
        mask = (1 << (newSmallest - smBit - 1)) - 1; // 0000 0011
        numb |= mask;

        return numb;
    }

    private int getSmallestBit(int numb) {
        for(int i = 0; i < 32; i++) {
            if( ((numb >> i) & 1) == 1)
                return i;
        }

        return  -1;
    }
}

