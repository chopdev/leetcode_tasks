# Palindrome Permutation: Given a string, write a function to check if it is a permutation of
# a palindrome. A palindrome is a word or phrase that is the same forwards and backwards. A
# permutation is a rearrangement of letters. The palindrome does not need to be limited to just
# dictionary words.

def isPalindromPermutation2222(str):
    count_odd = 0
    table = {}
    for curr in str:
        char = curr.lower()
        if char < 'a' or char > 'z':
            continue
        
        table[char] = table.get(char, 0) + 1
        if table[char] % 2 == 1:
            count_odd += 1
        else:
            count_odd -= 1

    return count_odd <= 1

# my implementation, idea is taken from book
def isPalindromPermutation(str):
    vector = 0
    for curr in str:
        char = curr.lower()
        if char < 'a' or char > 'z':
            continue
        index = ord(char) - ord('a')
        shifted_bit = 1<<index
        if (vector & shifted_bit) == shifted_bit:
            vector &= ~shifted_bit # clear specific bit if it was set
        else:
            vector |= shifted_bit # set bit, e.g. mark odd count of chars

    if vector == 0:
        return True

    # check if exactly one bit is set 
    decreased = vector - 1 # 0001000 - 1 = 0000111 
    return (vector & decreased) == 0


# True
print(isPalindromPermutation('Tact coa'))
print(isPalindromPermutation('Tact Coa'))
print(isPalindromPermutation('TactCoa3'))
print(isPalindromPermutation('ABBBA'))


# False
print(isPalindromPermutation('ABC'))
print(isPalindromPermutation('ABBBAA'))
