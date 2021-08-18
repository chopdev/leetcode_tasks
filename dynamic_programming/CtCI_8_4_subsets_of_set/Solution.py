# Power Set: Write a method to return all subsets of a set.

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