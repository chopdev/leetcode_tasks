/*
901. Online Stock Span
https://leetcode.com/problems/online-stock-span/description/
*/

import java.util.Deque;
import java.util.ArrayDeque;
import java.util.List;
import java.util.ArrayList;

class StockSpanner {
    int i;
    Deque<int[]> minStack; // stack of {ind, price}
    List<Integer> span;

    public StockSpanner() {
        minStack = new ArrayDeque<>();
        i = -1;
        span = new ArrayList<>();
    }
    

    // 0, 31 -> 1
    // 1, 41 -> 2
    // 
    public int next(int price) {
        i ++;

        int count = 1;
        while (!minStack.isEmpty() && minStack.peek()[1] <= price) {
            int[] nextMin = minStack.pop();
            count = i - nextMin[0] + span.get(nextMin[0]);
        }

        minStack.push(new int[] {i, price});
        span.add(count);

        return count;
    }
}



class StockSpanner_Simpler {
    Deque<int[]> st = new ArrayDeque<>(); // {price, span}

    public int next(int price) {
        int span = 1;
        while (!st.isEmpty() && st.peek()[0] <= price) {
            span += st.pop()[1];
        }
        st.push(new int[]{price, span});
        return span;
    }
}

/**
 * Your StockSpanner object will be instantiated and called as such:
 * StockSpanner obj = new StockSpanner();
 * int param_1 = obj.next(price);
 */
