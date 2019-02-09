import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;

/**
 380. Insert Delete GetRandom O(1)
 https://leetcode.com/problems/insert-delete-getrandom-o1/

 Design a data structure that supports all following operations in average O(1) time.

 insert(val): Inserts an item val to the set if not already present.
 remove(val): Removes an item val from the set if present.
 getRandom: Returns a random element from current set of elements. Each element must have the same probability of being returned.
 Example:

 // Init an empty set.
 RandomizedSet randomSet = new RandomizedSet();

 // Inserts 1 to the set. Returns true as 1 was inserted successfully.
 randomSet.insert(1);

 // Returns false as 2 does not exist in the set.
 randomSet.remove(2);

 // Inserts 2 to the set, returns true. Set now contains [1,2].
 randomSet.insert(2);

 // getRandom should return either 1 or 2 randomly.
 randomSet.getRandom();

 // Removes 1 from the set, returns true. Set now contains [2].
 randomSet.remove(1);

 // 2 was already in the set, so return false.
 randomSet.insert(2);

 // Since 2 is the only number in the set, getRandom always return 2.
 randomSet.getRandom();

 */

// Not mine solution
    // https://leetcode.com/problems/insert-delete-getrandom-o1/discuss/85401/Java-solution-using-a-HashMap-and-an-ArrayList-along-with-a-follow-up.-(131-ms)
public class RandomizedSet {
    ArrayList<Integer> nums;
    HashMap<Integer, Integer> numToPosition;
    Random rand;

    /** Initialize your data structure here. */
    public RandomizedSet() {
        nums = new ArrayList<>();
        numToPosition = new HashMap<>();
        rand = new Random();
    }

    /** Inserts a value to the set. Returns true if the set did not already contain the specified element. */
    public boolean insert(int val) {
        if(numToPosition.containsKey(val)) return false;

        numToPosition.put(val, nums.size());
        nums.add(val);
        return true;
    }

    /** Removes a value from the set. Returns true if the set contained the specified element. */
    public boolean remove(int val) {
        if(!numToPosition.containsKey(val)) return false;

        int pos = numToPosition.get(val);
        int last = nums.size() - 1;

        if(pos != last) { // this makes a trick of constant remove, swap last item of the list with current item
            int temp = nums.get(last);
            nums.set(last, nums.get(pos));
            nums.set(pos, temp);
            numToPosition.put(temp, pos);
        }

        numToPosition.remove(val);
        nums.remove(last);
        return true;
    }

    /** Get a random element from the set. */
    public int getRandom() {
        int ind = rand.nextInt(nums.size());
        return nums.get(ind);
    }
}

