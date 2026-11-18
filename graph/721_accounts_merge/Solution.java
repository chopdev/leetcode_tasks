/*
721. Accounts Merge
https://leetcode.com/problems/accounts-merge/

Merge accounts connected by shared email addresses, including transitive links.
Return each merged account with its name followed by unique emails in sorted
order. Matching names alone do not establish that accounts belong together.
*/

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

class Solution {
    /**
     * Transitive relationships are possible: the first and second accounts
     * below do not directly match, but the third connects them.
     * [["john", "a", "b"], ["john", "c", "d"], ["john", "a", "c"]]
     * -> [["john", "a", "b", "c", "d"]]
     */
    public List<List<String>> accountsMerge(List<List<String>> accounts) {
        Map<String, List<Integer>> emailToAccounts = new HashMap<>();

        for (int account = 0; account < accounts.size(); account++) {
            List<String> curr = accounts.get(account);
            for (int i = 1; i < curr.size(); i++) {
                String email = curr.get(i);
                emailToAccounts.computeIfAbsent(email, k -> new ArrayList<>())
                        .add(account);
            }
        }

        List<List<String>> output = new ArrayList<>();
        Set<Integer> visited = new HashSet<>();

        for (String email : emailToAccounts.keySet()) {
            Set<String> allEmails = new HashSet<>();
            String accountName = "";

            for (int account : emailToAccounts.get(email)) {
                dfs(account, emailToAccounts, accounts, allEmails, visited);
                accountName = accounts.get(account).get(0);
            }

            if (!allEmails.isEmpty()) {
                List<String> row = new ArrayList<>(allEmails);
                Collections.sort(row);
                row.add(0, accountName);
                output.add(row);
            }
        }

        return output;
    }

    void dfs(int account, Map<String, List<Integer>> emailToAccounts,
             List<List<String>> accounts, Set<String> res, Set<Integer> visited) {
        if (visited.contains(account)) return;

        visited.add(account);
        List<String> curr = accounts.get(account);

        for (int i = 1; i < curr.size(); i++) {
            String currEmail = curr.get(i);
            res.add(currEmail);

            for (int nextAccount : emailToAccounts.get(currEmail)) {
                dfs(nextAccount, emailToAccounts, accounts, res, visited);
            }
        }
    }

    /*
     Unfinished union-find draft, retained for reference.

    public List<List<String>> accountsMerge2222(List<List<String>> accounts) {
        Map<String, Integer> emailToAccount = new HashMap<>(); // email to main account
        Map<Integer, Set<String>> accountToEmails = new HashMap<>();

        for (int account = 0; account < accounts.size(); account++) {
            List<String> curr = accounts.get(account);
            String accountName = curr.get(0);

            for (int i = 1; i < curr.size(); i++) {

            }
        }
    }

    class UnionFind {
        int[] parent; // accounts that relate to each other

        public UnionFind(int size) {
            parent = new int[size];
            for (int i = 0; i < size; i++) {
                parent[i] = i;
            }
        }

        public boolean union(int a, int b) {
            int parentA = find(a);
            int parentB = find(b);

            if (parentA == parentB) return false;

            parent[parentB] = parentA;
        }

        public int find(int node) {
            if (node == parent[node]) return node;
            return find(parent[node]);
        }
    }
    */
}
