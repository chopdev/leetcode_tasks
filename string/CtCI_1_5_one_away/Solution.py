# One Away: There are three types of edits that can be performed on strings: insert a character,
# remove a character, or replace a character. Given two strings, write a function to check if they are
# one edit (or zero edits) away.

# abc avc
# abc abbc
# abc ac
# abc abc

 
# My solution O(N) in worst case, O(1) if the diff > 1
def isOneAway(str1, str2):
    longer = str1 if len(str1) > len(str2) else str2
    shorter = str2 if len(str1) > len(str2) else str1
    diff = len(longer) - len(shorter)

    if diff > 1: return False

    i, j = 0, 0
    foundDiff = False
    while i < len(longer) and j < len(shorter):
        if longer[i] != shorter[j]:
            if foundDiff: return False
            foundDiff = True
            j -= diff

        i += 1
        j += 1

    return True


# True
print(isOneAway("abc", "avc"))
print(isOneAway("abc", "abbc"))
print(isOneAway("ac", "abc"))
print(isOneAway("abc", "abc"))

# False 
print(isOneAway("abc", "avvc"))
print(isOneAway("abc", "a"))
print(isOneAway("abc", "kvc"))
print(isOneAway("abc", ""))
print(isOneAway("abc", ""))

