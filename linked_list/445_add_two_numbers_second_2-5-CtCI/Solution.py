# Sum Lists: You have two numbers represented by a linked list, where each node contains a single
# digit. The digits are stored in reverse order, such that the 1 's digit is at the head of the list. Write a
# function that adds the two numbers and returns the sum as a linked list.

# EXAMPLE
# Input: (7-> 1 -> 6) + (5 -> 9 -> 2) .Thatis,617 + 295.
# Output: 2 - > 1 - > 9. That is, 912.
# FOLLOW UP
# Suppose the digits are stored in forward order. Repeat the above problem.
# Input: (6 -> 1 -> 7) + (2 -> 9 -> 5).Thatis,617 + 295.
# Output: 9 - > 1 - > 2. That is, 912.

from typing import List



# 9 
# 8 -> 8 -> 2 

# 1 -> 2
# 8 -> 8 -> 2 

class Node:
    def __init__(self, val) -> None:
        self.next = None
        self.val = val

n1 = Node(9)
n2 = Node(9)

n3 = Node(8)
n4 = Node(8)
n3.next = n4

n5 = Node(1)
n5.next = Node(2)
################################################################################################

# My solution for initial problem, O(N) time
def sumLists(numb1: Node, numb2: Node) -> Node:
    rest = 0
    dumb = Node(0)
    head = dumb
    while (numb1 != None or numb2 != None):
        val1 = numb1.val if numb1 != None else 0
        val2 = numb2.val if numb2 != None else 0
        val = val1 + val2 + rest
        rest = int(val / 10)
        head.next = Node(val % 10)
        head = head.next
        if (numb1 != None): numb1 = numb1.next
        if (numb2 != None): numb2 = numb2.next
    
    if (rest > 0):
        head.next = Node(rest)
    return dumb.next

def sumListsForward(numb1: Node, numb2: Node) -> Node:
    first_len = getLength(numb1)
    second_len = getLength(numb2)

    tail, rest = 0, None
    if (first_len >= second_len):
        tail, rest = dfs(numb1, first_len, numb2, second_len)
    else:
        tail, rest = dfs(numb2, second_len, numb1, first_len)

    if (rest > 0):
        tmp = Node(rest)
        tmp.next = tail
        tail = tmp
    return tail

def getLength(node: Node) -> int:
    count = 0
    while (node != None):
        count += 1
        node = node.next
    return count 

def dfs(numb1: Node, len1: int, numb2: Node, len2: int) -> List[any]: # [Node, int rest]
    tail, rest, val = None, 0, 0
    if (len1 > len2):
        tail, rest = dfs(numb1.next, len1 - 1, numb2, len2)
        val = numb1.val + rest
    else:
        if (numb1.next == None and numb2.next == None):
            val = numb1.val + numb2.val
            return [Node(val % 10), int(val / 10)]
        
        tail, rest = dfs(numb1.next, len1 - 1, numb2.next, len2 - 1)
        val = numb1.val + numb2.val + rest

    tmp = Node(val % 10)
    tmp.next = tail
    return [tmp, int(val / 10)]

    

################################################################################################

def printList(head: Node) -> None:
    node = head
    values = []
    while (node != None):
        values.append(node.val)
        node = node.next
    print("->".join(str(val) for val in values))

#printList(sumLists(n1, n2)) # 9 + 9 = 18
#printList(sumLists(n1, n3)) # 9 + 88 = 97
#printList(sumLists(n3, Node(0))) # 88 + 0 = 88
#printList(sumLists(Node(1), Node(1))) # 1 + 1 = 2
#printList(sumLists(Node(5), Node(5))) # 5 +5 = 10


#printList(sumListsForward(n1, n2)) # 9 + 9 = 18
#printList(sumListsForward(n1, n3)) # 9 + 88 = 97
printList(sumListsForward(n5, n3)) # 12 + 88 = 100
printList(sumListsForward(Node(0), Node(0))) # 0 + 0 = 0
printList(sumListsForward(Node(0), Node(1))) # 0 + 1 = 1