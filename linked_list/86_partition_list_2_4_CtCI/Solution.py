# Partition: Write code to partition a linked list around a value x, such that all nodes less than x come
# before all nodes greater than or equal to x. If x is contained within the list, the values of x only need
# to be after the elements less than x (see below). The partition element x can appear anywhere in the
# "right partition"; it does not need to appear between the left and right partitions.
# EXAMPLE
# Input: 3 -> 5 -> 8 -> 5 -> 113 -> 2 -> 1 [partition = 5]
# Output: 3 -> 1 -> 2 -> 113 -> 5 -> 5 -> 8

class Node:
    def __init__(self, val):
        self.val = val
        self.next = None

n1 = Node(3)
n2 = Node(5)
n3 = Node(8)
n4 = Node(5)
n5 = Node(10)
n6 = Node(2)
n7 = Node(4)
n8 = Node(6)
n9 = Node(0)

n1.next = n2
n2.next = n3
n3.next = n4
n4.next = n5
n5.next = n6
n6.next = n7
n7.next = n8
n8.next = n9

##########################################################################

# My solution, O(N) time, O(1) space
def partition(head: Node, x: int) -> Node:
    node = head
    while (node != None and node.next != None):
        if (node.next.val < x):
            removed = removeNextNode(node)
            removed.next = head
            head = removed
        else:
            node = node.next
    return head

def removeNextNode(node: Node) -> Node:
    tmp = node.next
    node.next = node.next.next
    tmp.next = None
    return tmp


##########################################################################


def printLinkedList(head: Node) -> None:
    nodes = []
    n = head
    while n is not None:
        nodes.append(n.val)
        n = n.next

    print('->'.join(str(i) for i in nodes))

printLinkedList(n1)
print()
printLinkedList(partition(n1, 5))
