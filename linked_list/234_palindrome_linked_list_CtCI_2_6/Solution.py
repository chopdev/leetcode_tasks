from typing import List, NoReturn

class Node:
    def __init__(self, value) -> None:
        self.next = None
        self.val = value


# My solution O(n) time, O(N) space
def isPalindrome(head: Node) -> bool:
    leftNode, isPalindrome = dfs(head, head)
    return isPalindrome

def dfs(head: Node, tail: Node) -> List[any]:
    if tail.next == None:
        return [head.next, head.val == tail.val]

    leftNode, isPalindrome = dfs(head, tail.next)

    if not isPalindrome:
        return [leftNode, False]

    return [leftNode.next, leftNode.val == tail.val]
    

# 1 -> 2 -> 3 -> 4 -> 5
# 1 -> 2 -> 3 -> 4
# Not my idea, implemented by me
def isPalindrome2222(head: Node) -> bool:
    if head == None or head.next == None:
        return True
    fast = head
    slow = head
    lenght = 0
    while fast != None and fast.next != None:
        fast = fast.next.next
        slow = slow.next
    
    rightHead = reverseList(slow.next) if fast != None else reverseList(slow)
    leftHead = head
    while rightHead != None:
        if rightHead.val != leftHead.val:
            return False
        rightHead = rightHead.next
        leftHead = leftHead.next
    return True  

def reverseList(node: Node):
    head = None
    while node != None:
        curr = node
        node = node.next
        curr.next = head
        head = curr
    return head

#############################################################

n1 = Node(0)
n2 = Node(1)
n3 = Node(2)
n4 = Node(1)
n5 = Node(0)

n1.next = n2
n2.next = n3
n3.next = n4
n4.next = n5

print(isPalindrome2222(n1))
print(isPalindrome2222(Node(2)))

head = Node(0)
head.next = Node(1)
head.next.next = Node(1)
head.next.next.next = Node(0)

print(isPalindrome2222(head))

head2 = Node(0)
head2.next = Node(1)

print(isPalindrome2222(head2))

head3 = Node(0)
head3.next = Node(1)
head3.next.next = Node(2)

print(isPalindrome2222(head3))

