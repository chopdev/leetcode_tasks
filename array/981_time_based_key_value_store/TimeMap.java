import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 Question to ask: could we call set method in decreasing order of timestamp?

 Done in the note of the task:
 The timestamps for all TimeMap.set operations are strictly increasing.

 My solution
 it won't work if set could be called with decrease numbers
 O(1) - set
 O(log(N)) - get

 similar to this, but here iterative binary search
 https://leetcode.com/problems/time-based-key-value-store/discuss/244229/Java-beats-100
* */
class TimeMap {

    HashMap<String, ArrayList<Value>> map;

    /** Initialize your data structure here. */
    public TimeMap() {
        map = new HashMap<>();
    }

    public void set(String key, String value, int timestamp) {
        if(!map.containsKey(key)) {
            ArrayList<Value> list = new ArrayList<>();
            list.add(new Value(value, timestamp));
            map.put(key, list);
        }
        else {
            map.get(key).add(new Value(value, timestamp));
        }
    }

    public String get(String key, int timestamp) {
        if(!map.containsKey(key) || map.get(key).get(0).timestamp > timestamp) return "";
        ArrayList<Value> list = map.get(key);
        return binarySearch(list, timestamp, 0, list.size());
    }

    private String binarySearch(ArrayList<Value> values, int currTimestamp, int start, int end) {
        if(start >= end - 1) return values.get(start).value;

        int mid = (start + end) / 2;
        Value val = values.get(mid);
        if(val.timestamp == currTimestamp) return val.value;

        if(currTimestamp > val.timestamp)
            return binarySearch(values, currTimestamp, mid, end);
        else
            return binarySearch(values, currTimestamp, start, mid - 1);
    }
}

class Value {
    String value;
    int timestamp;

    public Value(String value, int timestamp) {
        this.timestamp = timestamp;
        this.value = value;
    }
}