# Triple Step: A child is running up a staircase with n steps and can hop either 1 step, 2 steps, or 3
# steps at a time. Implement a method to count how many possible ways the child can run up the
# stairs.

### IMPORTANT: you need to care about int overflow, which has a limit 2^31 - 1
# complexity of pure recursive algorithm is ~ O(3^N)

# stair[i] = stair[i-1] + 1 + stair[i - 2] + 2 + stair[i - 3] + 3
# from stair[i-3] to stair[i] we have 3 ways we can go: do three 1 steps / do one 1 step + one 2 step / do one 3 step
# O(N) time and space
# my solution
def get_possibilities_count(n: int) -> int:
    stair = [0]
    stair.insert(1, 1)
    stair.insert(2, 2)
    stair.insert(3, 3)
    for i in range(4, n + 1):
        stair.insert(i, stair[i-1] + 1 + stair[i - 2] + 2 + stair[i - 3] + 3)
    return stair[n]

# My solution
# O(1) space, O(N) time
def get_possibilities_count2222(n: int) -> int:
    if n == 1: return 1
    if n == 2: return 2
    if n == 3: return 3

    first = 1
    second = 2
    third = 3
    res = 0
    for i in range(4, n + 1):
        res = first + 1 + second + 2 + third + 3
        first = second
        second = third
        third = res
    return res

######################################################################

print(get_possibilities_count(3)) 
print(get_possibilities_count(4)) 

print(get_possibilities_count2222(3)) 
print(get_possibilities_count2222(4)) 