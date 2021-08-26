# Check Balanced: Implement a function to check if a binary tree is balanced. For the purposes of
# this question, a balanced tree is defined to be a tree such that the heights of the two subtrees of any
# node never differ by more than one.

from typing import Tuple

class Node:
    def __init__(self, val: int) -> None:
        self.left = None
        self.right = None
        self.val = val

# My solution O(N) time, O(H) space, where H is hight of tree
def is_balanced(head: Node) -> bool:
    if head == None: return True
    return dfs(head)[0]

# we could return some Integer.MIN_VALUE as an error code
# this can be an alternative to tuple
def dfs(node: Node) -> Tuple[bool, int]:
    if (node == None): 
        return (True, 0)
    left = dfs(node.left)
    if not left[0]: return (False, 0)
    right = dfs(node.right)
    if not right[0]: return (False, 0)
    return (abs(left[1] - right[1]) <= 1, max(left[1], right[1]) + 1)
    
head = Node(1)
head.left = Node(2)
head.right = Node(2)
head.left.left = Node(3)
head.right.left = Node(3)

print(is_balanced(head))

head.right.left.left = Node(4)

print(is_balanced(head))