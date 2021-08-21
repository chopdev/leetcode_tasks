# Recursive Multiply: Write a recursive function to multiply two positive integers without using
# the * operator (or / operator). You can use addition, subtraction, and bit shifting, but you should
# minimize the number of those operations.

# The idea is to divide smaller number on two, until it's 1
# when it's one, 1 * number = number
# 17 * 20
# (9 + 8) * 20
# (5 + 4 + 4 + 4) *20
# (3 + 2 + 2 + 2 + 2 + 2 + 2 + 2) * 20
# (1 + 2 + 1 + 1 + 1... + 1) * 20
# (1 + 1 + 1 ... + 1) * 20		

# My solution
# if N*M then O(log(min(N, M))) is a space complexity for recursion tree
# O(logN) time 
def multiply(num1: int, num2: int) -> int:
    smaller = num1 if num1 >= num2 else num2
    bigger = num2 if num1 >= num2 else num1
    return multiply_rec(smaller, bigger)

def multiply_rec(smaller: int, bigger: int) -> int:
    if smaller == 0: return 0
    if smaller == 1: return bigger

    rest = 0
    if (smaller & 1 == 1): # if smaller is odd
        rest = bigger 
        smaller -= 1 # remove one to make it even, but remember that 1 in rest

    sum = multiply_rec(smaller >> 1, bigger) # divide smaller on 2
    return sum + sum + rest # multiply back on 2

print(multiply(0, 0))
print(multiply(11, 0))
print(multiply(1, 2))
print(multiply(2, 2))
print(multiply(17, 21)) # 357
print(multiply(22, 10)) 
print(multiply(17, 20))