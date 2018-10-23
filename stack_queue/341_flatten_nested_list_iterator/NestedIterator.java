/**
 Given a nested list of integers, implement an iterator to flatten it.
 Each element is either an integer, or a list -- whose elements may also be integers or other lists.

 Example 1:

 Input: [[1,1],2,[1,1]]
 Output: [1,1,2,1,1]
 Explanation: By calling next repeatedly until hasNext returns false,
 the order of elements returned by next should be: [1,1,2,1,1].

 Example 2:

 Input: [1,[4,[6]]]
 Output: [1,4,6]
 Explanation: By calling next repeatedly until hasNext returns false,
 the order of elements returned by next should be: [1,4,6].

 * */

import java.util.Iterator;
import java.util.List;
import java.util.Stack;

/**
 * // This is the interface that allows for creating nested lists.
 * // You should not implement it, or speculate about its implementation
 * public interface NestedInteger {
 *
 *     // @return true if this NestedInteger holds a single integer, rather than a nested list.
 *     public boolean isInteger();
 *
 *     // @return the single integer that this NestedInteger holds, if it holds a single integer
 *     // Return null if this NestedInteger holds a nested list
 *     public Integer getInteger();
 *
 *     // @return the nested list that this NestedInteger holds, if it holds a nested list
 *     // Return null if this NestedInteger holds a single integer
 *     public List<NestedInteger> getList();
 * }
 */
public class NestedIterator implements Iterator<Integer> {

    Stack<Integer> indexes;
    Stack<List<NestedInteger>> lists;

    public NestedIterator(List<NestedInteger> nestedList) {
        if(nestedList == null) return;

        indexes = new Stack<>();
        lists = new Stack<>();

        if(nestedList.size() > 0) {
            indexes.push(0);
            lists.push(nestedList);
        }
    }

    @Override
    public Integer next() {
        int res = -1;
        int index = indexes.pop();
        List<NestedInteger> list = lists.pop();

        while (index < list.size() && !list.get(index).isInteger()) {
            List<NestedInteger> element = list.get(index).getList();
            if(element.size() == 0) {
                index ++;
                continue;
            }

            pushToStacks(index + 1, list);
            list = element;
            index = 0;
        }

        if(index < list.size()) {
            res =  list.get(index).getInteger();
            pushToStacks(index + 1, list);
        }

        return res;
    }

    @Override
    public boolean hasNext() {
        return lists.size() > 0;
    }

    private void pushToStacks(int index, List<NestedInteger> list) {
        if(index < list.size()) {
            indexes.push(index);
            lists.push(list);
        }
    }
}
