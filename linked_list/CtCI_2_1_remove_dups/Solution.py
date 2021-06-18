# Remove Dups: Write code to remove duplicates from an unsorted linked list.
# FOLLOW UP
# How would you solve this problem if a temporary buffer is not allowed?

class Node:
    def __init__(self, val) -> None:
        self.val = val
        self.next = None


n1 = Node(1)
n2 = Node(2)
n3 = Node(3)
n4 = Node(1)
n5 = Node(1)
n6 = Node(2)
n7 = Node(1)
n8 = Node(1)
n9 = Node(2)

n1.next = n2
n2.next = n3
n3.next = n4
n4.next = n5
n5.next = n6
n6.next = n7
n7.next = n8
n8.next = n9

##########################################################################

# My O(N) solution, Space O(N)
def removeDups(head: Node) -> Node:
    if head is None:
        return
    unique = set()
    node = head
    unique.add(node.val)

    while node.next is not None:
        if node.next.val in unique:
            node.next = node.next.next
            continue

        unique.add(node.next.val)   
        node = node.next
    
    return head

# My O(N^2) solution
def removeDups2222(head: Node) -> Node:
    if head is None:
        return
    
    curr = head

    while curr is not None:
        node = curr
        while node.next is not None:
            if node.next.val == curr.val:
                node.next = node.next.next
                continue

            node = node.next
        curr = curr.next
    
    return head
    

##########################################################################


def printLinkedList(head: Node) -> None:
    nodes = []
    n = head
    while n is not None:
        nodes.append(n.val)
        n = n.next

    print('->'.join(str(i) for i in nodes))


printLinkedList(n1)
#printLinkedList(removeDups(n1))
printLinkedList(removeDups2222(n1))