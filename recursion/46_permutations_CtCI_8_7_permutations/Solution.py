# Permutations without Dups: Write a method to compute all permutations of a string of unique
#characters.

from typing import List

# My solution O(N!) time, O(N) space
def get_permutations(str: str) -> List[str]:
    if len(str) <= 1: return str
    return get_permutations_resursive(str, 1, [str[0]])

def get_permutations_resursive(str: str, ind: int, res: List[str]) -> List[str]:
    if (ind >= len(str)): return res
    new_res = []
    curr = str[ind]
    for perm in res:
        for i in range(len(perm) + 1):
            new_res.append(perm[:i] + curr + perm[i:])
    return get_permutations_resursive(str, ind + 1, new_res)


res = get_permutations("abc")
for s in res:
    print(s)