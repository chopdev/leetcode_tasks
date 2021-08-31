#10.1 Sorted Merge: You are given two sorted arrays, A and B, where A has a large enough buffer at the
# end to hold B. Write a method to merge B into A in sorted order.

from typing import List

# My solution, O(N + M) time, no extra space
# the idea is to take biggest item from the end of both arrays and put it to the last possible place in array A
# Other solution would be to merge and then sort but it would take O(B*log(B)) time

def merge_sorted(sortedA: List[int], sortedB: List[int]) -> List[int]:
    if not sortedB: return sortedA
    lastA = 0
    lastB = len(sortedB) - 1
    for i in range(len(sortedA)):
        if sortedA[i] == None:
            lastA = i - 1
            break
    array_end = len(sortedB) + lastA
    for i in range(array_end, -1, -1):
        if lastA >= 0 and lastB >= 0 and sortedA[lastA] <= sortedB[lastB]:
            sortedA[i] = sortedB[lastB]
            lastB -= 1
            continue
        if lastA >= 0 and lastB >= 0 and sortedB[lastB] <= sortedA[lastA]:
            sortedA[i] = sortedA[lastA]
            lastA -= 1
            continue
        if lastA >= 0:
            sortedA[i] = sortedA[lastA]
            lastA -= 1
        if lastB >= 0:
            sortedA[i] = sortedB[lastB]
            lastB -= 1
    return sortedA


print(merge_sorted([1, 4, 10, 19, None, None, None], [2, 3, 20]))
print(merge_sorted([None, None, None], [2, 3, 20]))
print(merge_sorted([1, 2, 3], []))
print(merge_sorted([1, 1, 2, 19, 20, 21, None, None, None, None, None], [2, 3, 20]))