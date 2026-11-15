/*
684. Redundant Connection
https://leetcode.com/problems/redundant-connection/

An undirected tree has one extra edge. Return an edge whose removal restores
a tree; if several edges qualify, return the one appearing last in the input.
*/

class Solution {
    int[] parent;

    public int[] findRedundantConnection(int[][] edges) {
        int n = edges.length;
        parent = new int[n + 1];
        for (int i = 1; i <= n; i++) {
            parent[i] = i;
        }

        for (int[] edge : edges) {
            int a = edge[0];
            int b = edge[1];

            if (!union(a, b)) return new int[] {a, b};
        }

        return null;
    }

    boolean union(int a, int b) {
        int parentA = find(a);
        int parentB = find(b);

        if (parentA == parentB) return false; // connected already

        parent[parentB] = parentA;
        return true;
    }

    int find(int node) {
        if (node == parent[node]) return node;
        return find(parent[node]);
    }
}
