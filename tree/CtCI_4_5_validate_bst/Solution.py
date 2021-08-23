# Validate BST: Implement a function to check if a binary tree is a binary search tree.

from typing import Tuple


# Option 1 - do in-order traversal and copy values to array then check that array is sorted

class Node:
    def __init__(self, val: int) -> None:
        self.left = None
        self.right = None
        self.val = val

# Not my solution
# O(N) time and space
# This question is tricky, I just wanted to compare parent to children, but that's not correct
def validate_binary_tree(head: Node) -> bool:
    if head == None: return False
    return is_bst(head, None, None)

def is_bst(node: Node, min: int, max: int) -> bool:
    if node == None: return True
    if max != None and node.val > max:
        return False
    if min != None and node.val <= min:
        return False
    return is_bst(node.left, min, node.val) and is_bst(node.right, node.val, max)



node = Node(5)
node.left = Node(5)
node.right = Node(8)
node.left.left = Node(2)
node.right.left = Node(6)
node.right.right = Node(10)
node.right.right = Node(12)

print(validate_binary_tree(node)) # True
print(validate_binary_tree(Node(6))) # True

node.left.right = Node(6)
print(validate_binary_tree(node)) # False