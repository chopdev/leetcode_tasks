# CtCI 8_8
#Permutations with Duplicates: Write a method to compute all permutations of a string whose
#characters are not necessarily unique. The list of permutations should not have duplicates.

# aab 
# aab, aba, baa  - basically there is no sense to put the same char twice around equal char so aa == aa (a1a2 == a2a1)
#                   we can also use hash set to remove duplicates, but creation of a string takes O(k) time

# abc 
# a -> ab, ba -> cab, acb, abc, cba, bca, bac
from typing import List

# My solution O(N!) space and time if there are no duplicates (worst case)
# with duplicates it will be much faster,  "aaaaa" for O(N^2) ??? like N iterations time N concatenations
def permutations(val: str) -> List[str]:
    if not val or len(val) <= 1: return val
    return concat(val, 1, [val[0]])

def concat(val: str, i: int, res: List[str]) -> List[str]:
    if i >= len(val):
        return res
    new_res = []
    for temp in res:
        for j in range(len(temp) + 1):
            if j > 0 and temp[j - 1] == val[i]: continue
            new_res.append(temp[0:j] + val[i] + temp[j:])
    return concat(val, i + 1, new_res)


print(permutations("abc")) #  'cba', 'bca', 'bac', 'cab', 'acb', 'abc'
print(permutations("aab")) # 'baa', 'aba', 'aab'
print(permutations("aaa")) # aaa


print(permutations("a")) # a
print(permutations("")) # ""
print(permutations(None)) # None


## Book has a different solution
# they propose to build permutations from the HashTable of chars
# take chars from hashtable on each iteration
# aab = {a:2, b:1}
# P({a:2, b:1}) = {a+ P({a:1, b:1})} +
#                  {b + P({a:2, b:0})}
#       P({a:1, b:1}) = {a + P({a:0, b:1})} + 
#                        {b + P({a:1, b:0})}
#       P({a:2, b:0}) = ...
# ...