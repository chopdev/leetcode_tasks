# The distance between two nodes is the number of links that make the shortest path between them.
# Write a function to find the greatest distance between any two nodes of binary tree.
#                               Kelly
#                            /
#                  Jeemy
#               / 
#         - Bob
#        /      \ 
#                  John
# Ivan
#        \
#         - Matthew

# Ivan and Jeemy distance = 2.
# Correct result is Matthew and Kelly distance = 4.

from typing import Tuple
import copy

class Node:
    def __init__(self, val) -> None:
        self.left: Node = None
        self.right: Node = None
        self.val: str = val

def get_dist(node: Node) -> int:
    return get_dist_recurs(node)[0]


# NOTE: important thing to consider here is imbalanced tree, in this case max dist is 7 between Karl and Belly
#                               Kelly - Belly
#                            /
#                  Jeemy
#               / 
#         - Bob
#        /      \ 
#                  John - Bombo - Bimbo - Karl
# Ivan
#        \
#         - Matthew

# My solution is to return max distance of either current node or any child node as a first param of tuple.
# And max depth at this node as a second param. Having it we can calculate dist at parant node. 
# O(N) time and space
def get_dist_recurs(node: Node) -> Tuple[int, int]: # [max dist on child node, max depth]
    if node.left == None and node.right == None:
        return [0, 1]

    left_res = get_dist_recurs(node.left) if node.left != None else (0, 0)
    right_res = get_dist_recurs(node.right) if node.right != None else (0, 0)
    left_depth = 0 if left_res[1] == 0 else left_res[1]
    right_depth = 0 if right_res[1] == 0 else right_res[1]
    max_dist = max(left_res[0], right_res[0], left_depth + right_depth) # it's either max dist on one of the child nodes, or max dist based on this node
    max_depth = max(left_res[1] + 1, right_res[1] + 1)
    return (max_dist, max_depth) 


### SOLUTION 2
# We can calculate depth of each node by separate function and cache results in Map. 
# Then proceed with distance detection just returning max dist of currenct node or any child node e.g. return max(curr_dist, max_dist)
    
node = Node('0')
node.left = Node('1')
node.right = Node('2')
node.right.left = Node('3')
node.right.left.left = Node('4')
node.right.left.left.left = Node('5')
node.right.left.right = Node('6') # not important

print(get_dist(node)) # 5

node1 = copy.deepcopy(node)

node1.right.right = Node('3')
node1.right.right.right = Node('4')
node1.right.right.right.right = Node('5')
node1.right.right.right.right.right = Node('5')

print(get_dist(node1)) # 7

print(get_dist(Node('1')))

node2 = Node('1')
node2.left = Node('2')

print(get_dist(node2)) # 1





