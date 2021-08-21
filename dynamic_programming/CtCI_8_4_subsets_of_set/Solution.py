# Power Set: Write a method to return all subsets of a set.


# We should first have some reasonable expectations of our time and space complexity.
# How many subsets of a set are there? When we generate a subset, each element has the "choice" of either
# being in there or not. That is, for the first element, there are two choices: it is either in the set, or it is not. For
# the second, there are two, etc. So, doing {2 * 2 * ... } n times gives us 2^n subsets.
# Assuming that we're going to be returning a list of subsets, then our best case time is actually the total
# number of elements across all of those subsets. There are 2^n subsets and each of the n elements will be
# contained in half of the subsets (which 2^n-1 subsets). Therefore, the total number of elements across all of
# those subsets is n * 2^(n-1).
# We will not be able to beat 0 (n2^n) in space or time complexity.

from typing import List

def get_subsets(items: List[int]) -> List[List[int]]:
    if not items: return []
    res = []
    get_all_subsets(items, res, [], 0)
    return res

def get_all_subsets(items: List[int], res: List[List[int]], current_set: List[int], index: int) -> None:
    if index >= len(items):
        return

    new_set = list(current_set)
    new_set.append(items[index])
    res.append(new_set)
    get_all_subsets(items, res, current_set, index + 1)
    get_all_subsets(items, res, new_set, index + 1)


##########################################################################################


res = get_subsets([1, 2, 3])

for set in res:
    print(set)