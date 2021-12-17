/**
 * 105. Construct Binary Tree from Preorder and Inorder Traversal
 * https://leetcode.com/problems/construct-binary-tree-from-preorder-and-inorder-traversal/
 * 
 * Given two integer arrays preorder and inorder where preorder is the preorder traversal of a binary tree and inorder is the inorder traversal of the same tree, construct and return the binary tree.
 * 
 * Example 1
 * Input: preorder = [3,9,20,15,7], inorder = [9,3,15,20,7]
    Output: [3,9,20,null,null,15,7]

    Example 2
    Input: preorder = [-1], inorder = [-1]
    Output: [-1]

    Constraints:
    1 <= preorder.length <= 3000
    inorder.length == preorder.length
    -3000 <= preorder[i], inorder[i] <= 3000
    preorder and inorder consist of unique values.
    Each value of inorder also appears in preorder.
    preorder is guaranteed to be the preorder traversal of the tree.
    inorder is guaranteed to be the inorder traversal of the tree.
 */
/* 
    My idea: 
    Pre order traversal considers current node first. So we are sure that tree begins from preorder[0].
    We use inorder array to find out which nodes are to the left from the root, and which are from the right. Basically divide inorder array on two.
    Then continue to use preorder array in recursion to build next child Node and return it from recursion
*/

class Solution {

    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode() {}
        TreeNode(int val) { this.val = val; }
        TreeNode(int val, TreeNode left, TreeNode right) {
            this.val = val;
            this.left = left;
            this.right = right;
        }
    }


    // My solution
    // Time O(N*logN) - logN because of findIndexOfNode(), it will decrease ~twice on each recursion level
    // space O(N) - depth of recursion
    int preorderIndex = 0;
    public TreeNode buildTree(int[] preorder, int[] inorder) {
        preorderIndex = 0;
        return dfs(preorder, inorder, 0, inorder.length - 1);
    }

    public TreeNode dfs(int[] preorder, int[] inorder, int inorderFrom, int inorderTo) {
        TreeNode node = new TreeNode(preorder[preorderIndex]);
        preorderIndex ++;
        int inorderIndex = findIndexOfNode(inorder, inorderFrom, inorderTo, node.val);
        if (inorderFrom < inorderIndex) { // there are nodes left to current node
            node.left = dfs(preorder, inorder, inorderFrom, inorderIndex - 1);
        }
        if (inorderTo > inorderIndex) { // there are nodes right to current node
            node.right = dfs(preorder, inorder, inorderIndex + 1, inorderTo);
        }
        return node;
    }

    private int findIndexOfNode(int[] inorder, int from, int to, int val) {
        for (int i = from; i <= to; i++) {
            if (inorder[i] == val)
                return i;
        }
        return -1;
    }
} 



/* 
// Not mine, good idea to use hash map to save node to index relation. 
// findIndexOfNode() function is not needed then
class Solution {
    int pi;
    HashMap<Integer,Integer> hm;
    public TreeNode buildTree(int[] preorder, int[] inorder) {
        hm=new HashMap<>();
        pi=0;
        for(int i=0;i<inorder.length;i++)
            hm.put(inorder[i],i);
       return buildTree(inorder,preorder,0,inorder.length-1);
        
    }
    public TreeNode buildTree(int[] in,int[] pre,int ins,int ine){
        if(ins>ine)
            return null;
        
       TreeNode tNode=new TreeNode(pre[pi++]);
        
        if(ins==ine)
            return tNode;
        int ini=hm.get(tNode.val);
        tNode.left=buildTree(in,pre,ins,ini-1);
        tNode.right=buildTree(in,pre,ini+1,ine);
        return tNode;
        
}
}
*/