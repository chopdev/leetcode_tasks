#Return Kth to Last: Implement an algorithm to find the kth to last element of a singly linked list.
from collections import deque, namedtuple
from typing import List

class Node:
    def __init__(self, val) -> None:
        self.val = val
        self.next = None


n1 = Node(1)
n2 = Node(2)
n3 = Node(3)
n4 = Node(4)
n5 = Node(5)
n6 = Node(6)
n7 = Node(7)
n8 = Node(8)
n9 = Node(9)

n1.next = n2
n2.next = n3
n3.next = n4
n4.next = n5
n5.next = n6
n6.next = n7
n7.next = n8
n8.next = n9

##########################################################################

# Mine O(N) space and time  
def kthToLast1111(kToLast: int, head: Node) -> Node:
    stack = deque()
    node = head
    while (node != None):
        stack.append(node)
        node = node.next

    for i in range(kToLast - 1):
        stack.pop()

    return stack.pop()

# Mine O(N) time O(1) space   
def kthToLast2222(kToLast: int, head: Node) -> Node:
    count = 0
    node = head
    while (node != None):
        count += 1
        node = node.next
    
    node = head
    for i in range(count - kToLast):
        node = node.next
    return node


# Mine recursive solution, O(N) space and time
def kthToLast3333(kToLast: int, head: Node) -> Node:
    res = dfs(head, kToLast)
    return res[1]

def dfs(node: Node, kToLast: int) -> List[any]:
    if (node.next == None):
        return [1, None]
    res = dfs(node.next, kToLast)
    res[0] += 1
    if (res[0] == kToLast):
        res[1] = node
    return res

# not mine, O(N) time, O(1) space
def kthToLast4444(kToLast: int, head: Node) -> Node:
    p1 = head
    p2 = head

    for i in range(kToLast):
        p1 = p1.next
    
    while (p1 != None):
        p1 = p1.next
        p2 = p2.next

    return p2

##########################################################################


def printLinkedList(head: Node) -> None:
    nodes = []
    n = head
    while n != None:
        nodes.append(n.val)
        n = n.next

    print('->'.join(str(i) for i in nodes))

printLinkedList(n1)

printLinkedList(kthToLast4444(4, n1))