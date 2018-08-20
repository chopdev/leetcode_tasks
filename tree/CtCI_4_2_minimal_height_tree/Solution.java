/*
* Minimal Tree: Given a sorted (increasing order) array with unique integer elements, write an algorithm
    to create a binary search tree with minimal height.
* */
public class Solution {

    // Mine solution. Uses logic like in binary search. Time O(logN) Space O(logN) - recurcion O(N) - result tree
    public Node createMinHeightTree(int[] arr) {
        if(arr == null || arr.length == 0) return null;

        return create(arr, 0, arr.length);
    }

    private Node create(int[] arr, int left, int right) {
        if(right <= left) return null;

        int pivot = left + (right - left)/2;
        Node node = new Node(arr[pivot]);
        node.left = create(arr, left, pivot);
        node.right = create(arr, pivot + 1, right);

        return node;
    }
}
