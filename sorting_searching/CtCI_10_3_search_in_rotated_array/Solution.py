# Search in Rotated Array: Given a sorted array of n integers that has been rotated an unknown
# number of times, write code to find an element in the array. You may assume that the array was
# originally sorted in increasing order.
# EXAMPLE
# InputfindSin {15, 16, 19, 20, 25, 1, 3,4,5,7,10, 14}
# Output 8 (the index of 5 in the array)

# We need to detect in which part of array the middle item is
# if it's in the less part of array
#    and we search for bigger item, it might appear in both sides
#    and we search for smaller item, it will appear in the left side
# if it's in the bigger part of array
#    and we search for bigger item, it will appear in the right side
#    and we search for smaller item, it can appear in both sides

# important to consider: what if there are duplicate items?

from typing import List

def search(arr: List[str], element: int) -> int:
    return search_rec(arr, 0, len(arr) - 1, element)

# My solution
# O(logN) time, O(N) in the worst case if we search both sides to oftenly
def search_rec(arr: List[str], low: int, high: int, element: int) -> int:
    if low > high:
        return -1 # not found
    mid = int((low + high) / 2)
    if arr[mid] == element:
        return mid # found
    is_smaller_part = arr[mid] < arr[high]
    res = -1
    if is_smaller_part:
        if element > arr[mid]:
            res = search_rec(arr, mid + 1, high, element)
            if res > 0: return res
        return search_rec(arr, low, mid - 1, element)
    else:
        if element < arr[mid]:
            res = search_rec(arr, low, mid - 1, element)
            if res > 0: return res
        return search_rec(arr, mid + 1, high, element)


print(search([15, 16, 19, 20, 25, 1, 3,4,5,7,10, 14], 5)) # 8
print(search([15, 16, 19, 20, 25, 1, 3,4,5,7,10, 14], 15)) # 0
print(search([15, 16, 19, 20, 25, 1, 3,4,5,7,10, 14], 25)) # 4
print(search([15, 16, 19, 20, 25, 1, 3,4,5,7,10, 14], 1)) # 5
print(search([15, 16, 19, 20, 25, 1, 3,4,5,7,10, 14], 14)) # 11

print(search([], 2)) # -1
print(search([2], 2)) # 0
print(search([1, 2, 3], 2)) # 1
print(search([3, 1, 2], 2)) # 2


print(search([2, 2, 2, 3, 4, 2], 3)) # 3
print(search([2, 2, 2, 3, 4, 2], 4)) # 4
print(search([2, 2, 2, 3, 4, 2], 2)) # 2
print(search([2, 2, 2, 2], 3)) # -1 O(N) solution in this case
