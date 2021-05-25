#Check Permutation: Given two strings, write a method to decide if one is a permutation of the other.

# Questions to ask
# 1.We should understand if the permutation comparison is case sensitive. e.g. God and dog
# 2. Additionally, we should ask if whitespace is significant. e.g "abc   " vs "cab"


def isPermutation(str1, str2):
    if (len(str1) != len(str2)):
        return False
    values = {}
    for char in str1:
        values[char] = values.get(char, 0) + 1
    
    for char in str2: 
        if char not in values or values.get(char) <= 0:
            return False
        values[char] = values.get(char) - 1

    return True


# True
print(isPermutation('abc', 'cba'))
print(isPermutation('aac', 'aca'))

# False
print(isPermutation('ab', 'cba'))
print(isPermutation('abc', 'cbd'))
print(isPermutation('aac', 'cca'))
