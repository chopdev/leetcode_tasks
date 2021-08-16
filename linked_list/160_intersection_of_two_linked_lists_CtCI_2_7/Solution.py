# Intersection: Given two (singly) linked lists, determine if the two lists intersect. Return the
# intersecting node. Note that the intersection is defined based on reference, not value. That is, if the
# kth node of the first linked list is the exact same node (by reference) as the jth node of the second
# linked list, then they are intersecting.

# Important to ask: do they have the same ending? Or they might have just one intersection node and the rest of lists are different

class Node:
    def __init__(self, value) -> None:
        self.next = None
        self.val = value

# Brute force: O(N*M) - check each node of list one with node from list 2

# another brute force: use of Set to detect nodes from the first list O(N+M) time O(N) space.
#  We can also make it O(min(N, M)) for space complexit
# my solution
def get_intersect(head1: Node, head2: Node) -> Node:
    unique = set()
    node1 = head1
    while node1 != None:
        unique.add(node1)
        node1 = node1.next

    node2 = head2
    while node2 != None:
        if node2 in unique:
            return node2
        node2 = node2.next

    return None

# If ending is the same we can jus measure length of both lists and do an indent in the longer list.
def get_intersect2222(head1: Node, head2: Node) -> Node:
    pass

########################################################################################
inters = Node(5)
inters.next = Node(6)
inters.next.next = Node(7)

head1 = Node(1)
head2 = Node(2)

head1.next = Node(12)
head1.next.next = inters

head2.next = inters

res = get_intersect(head1, head2)
print(res == inters) # True

res2 = get_intersect(head1, inters)
print(res2 == inters) # True

res3 = get_intersect(head1, Node(2))
print(res3 == None) # True