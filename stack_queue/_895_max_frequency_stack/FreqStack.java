import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

public class FreqStack {
    /**
     * value to frequency
     * */
    Map<Integer, Integer> valToFreq = new HashMap<>();
    /**
     * frequency to all elements with stack order on that frequency.
     * If element is present on frequency N, it's also present on all lower frequencies e.g. 0 <= K <= N
      * */
    Map<Integer, Stack<Integer>> freqToOrder = new HashMap<>();
    int maxFreq;


    public void push(int x) {
        int freq = valToFreq.getOrDefault(x, 0) + 1;
        valToFreq.put(x, freq);
        freqToOrder.computeIfAbsent(freq, key -> new Stack<>()).push(x);
        maxFreq = Math.max(freq, maxFreq);
    }

    public int pop() {
        int val = freqToOrder.get(maxFreq).pop();
        if (freqToOrder.get(maxFreq).size() == 0) maxFreq --;
        valToFreq.put(val, valToFreq.get(val) - 1);
        return val;
    }

}
