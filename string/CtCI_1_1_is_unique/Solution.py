#Is Unique: Implement an algorithm to determine if a string has all unique characters. What if you
# cannot use additional data structures?

# O(N) solution, O(N) space for set
def isUnique2222(str):
    chars = set()
    for char in str:
        if char in chars:
            return False 
        chars.add(char)
    return True

# O(NlogN) solution, O(N) space for recursion of Quick Sort
def isUnique1111(str):
    sorted_str = sorted(str)
    for i in range(0, len(str) - 2):
        if sorted_str[i] == sorted_str[i+1]:
            return False

    return True


# Saw it in java solutions, O(N) time O(1) space
def isUnique(str):
    vector = 0
    for char in str:
        char_index = ord(char) - ord('a')
        shifted_bit = 1 << char_index
        if vector & shifted_bit > 0:
            return False
        vector = vector | shifted_bit
    return True

# True
print(isUnique('abc'))

# False
print(isUnique('abca'))
print(isUnique('aabc'))
print(isUnique('abac'))
