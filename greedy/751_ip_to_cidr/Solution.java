/*
751. IP to CIDR
https://leetcode.com/problems/ip-to-cidr/description/

Given a starting IPv4 address and a count n, cover exactly those n consecutive
addresses with the fewest CIDR blocks.
*/

import java.util.ArrayList;
import java.util.List;

class Solution {
    /**
     * 1. CIDR block sizes are powers of two: 1, 2, 4, 8, 16, ...
     *
     * CIDR   Block size   Last-octet pattern (x denotes a variable bit)
     * /32    1            11111111
     * /31    2            1111111x
     * /30    4            111111xx
     * /29    8            11111xxx
     * /28    16           1111xxxx
     * /27    32           111xxxxx
     *
     * 2. The starting IP must be aligned to the block size.
     * For an eight-address block, the final three bits must be zero:
     * 192.128.26.1   ...0001  - not aligned
     * 192.128.26.2   ...0010  - not aligned
     * 192.128.26.8   ...1000  - aligned
     * 192.128.26.9   ...1001  - not aligned
     * 192.128.26.10  ...1010  - not aligned
     * 192.128.26.15  ...01111 - not aligned
     * 192.128.26.16  ...10000 - aligned
     *
     * A block of size 2^k requires the last k bits of the starting IP to be
     * zero, so all combinations of those bits share a fixed prefix.
     * Equivalently, the starting IP must be divisible by 2^k.
     *
     * 3. Greedy approach:
     * Convert the IP to a long to avoid signed int overflow. Start with a
     * block of size one and double it while alignment and remaining count allow.
     *
     * Choose the largest valid block at the first uncovered IP. A smaller
     * choice requires multiple blocks to cover the same range. Aligned CIDR
     * blocks are nested or disjoint, so choosing the largest valid block cannot
     * make the remaining range harder to cover optimally.
     */
    public List<String> ipToCIDR(String ip, int n) {
        long curr = ipToNumber(ip);
        int remaining = n;
        List<String> res = new ArrayList<>();

        while (remaining > 0) {
            int blockSize = 1;
            int prefix = 32;

            while (blockSize * 2 <= remaining &&
                   curr % (blockSize * 2) == 0) {
                blockSize *= 2;
                prefix -= 1;
            }

            res.add(longToIp(curr) + "/" + prefix);

            curr += blockSize;
            remaining -= blockSize;
        }

        return res;
    }

    String longToIp(long numb) {
        return ((numb >> 24) & 0xFF) + "." +
               ((numb >> 16) & 0xFF) + "." +
               ((numb >> 8) & 0xFF) + "." +
               (numb & 0xFF);
    }

    public long ipToNumber(String ip) {
        long curr = 0;
        String[] parts = ip.split("\\.");
        for (String part : parts) {
            curr = (curr << 8) | Integer.parseInt(part);
        }
        return curr;
    }
}
