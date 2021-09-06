#10.2 Group Anagrams: Write a method to sort an array of strings so that all the anagrams are next to
#each other.

# abc g cba fd gg ff df abc oo -> abc cba abc g fd df gg ff oo
# ab ab ba -> ab ab ba
# ac bg cj -> ac bg cj

from typing import List

# My solution
# N - count of items in arr, K - average length of item in arr
# Complexity: O(N*K*logK), space: O(N*K)
def group_anagrams(arr: List[str]) -> List[str]:
    anagrams = {}
    for item in arr:
        sorted_item = ''.join(sorted(item))
        if sorted_item in anagrams:
            anagrams[sorted_item].append(item)
        else:
            anagrams[sorted_item] = [item]

    return [val for key in anagrams for val in anagrams[key]]

print(group_anagrams(['abc', 'g', 'cba', 'fd', 'gg','ff','df','abc','oo']))
print(group_anagrams(['ab', 'ab', 'ba']))
print(group_anagrams(['ac', 'gb', 'bj']))
print(group_anagrams(['ac']))
print(group_anagrams([]))
