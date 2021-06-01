#Rotate Matrix: Given an image represented by an NxN matrix, where each pixel in the image is 4
# bytes, write a method to rotate the image by 90 degrees. Can you do this in place?

# https://leetcode.com/problems/rotate-image/

#
#    1  2  3 4        1 2 3  
#   12 13 14 5        8 9 4
#   11 16 15 6        7 6 5
#   10 9  8  7
#
# row of external is bigger on 2 items, matrix is decreasing twice with each layer
# consider rotation of not first element in a row


# O(N^2) solution, N - one side lenght
def rotate(matrix):
    n = len(matrix)
    for layer in range(0, n//2 + n%2):  # n//2 == int(n/2), it rounds to lower value
        first = layer
        last = n - 1 - layer
        for i in range(first, last):
            offset = i - first # we should know relative offset to specific layer
            swap(matrix, first, i, i, last)
            swap(matrix, first, i, last, last - offset)
            swap(matrix, first, i, last - offset, first)

def swap(matrix, i, j, k, m):
    matrix[i][j], matrix[k][m] =  matrix[k][m], matrix[i][j]



# not mine solution, but iteresting idea, much easier to implement without mistakes
# https://leetcode.com/problems/rotate-image/solution/
def rotate2222(matrix):
    if matrix is None or len(matrix) != len(matrix[0]):
        raise ValueError('invalid matrix')
    transpose(matrix)
    reflect(matrix)

def transpose(matrix):
    for i in range(len(matrix)):
        for j in range(i+1, len(matrix)):
            matrix[j][i], matrix[i][j] = matrix[i][j], matrix[j][i]

def reflect(matrix):
    N = len(matrix)
    for row in range(N):
        for j in range(N // 2):
            matrix[row][j], matrix[row][N - 1 - j] = matrix[row][N - 1 - j], matrix[row][j]


# https://leetcode.com/problems/rotate-image/discuss/18884/Seven-Short-Solutions-(1-to-7-lines)
# Nice solution, will work with N*M matrix, BUT not completely in place
def rotate3333(matrix):
    matrix[:] = map(list, zip(*matrix[::-1]))

# https://leetcode.com/problems/rotate-image/discuss/18884/Seven-Short-Solutions-(1-to-7-lines)
# similar to rotate2222 but in different order
def rotate4444(matrix):
    matrix.reverse() # reflect rows firstly
    transpose(matrix)


###########################################
#matrix = [[1, 2, 3, 4], [12, 13, 14, 5], [11, 16, 15, 6], [10, 9, 8, 7]]
matrix = [[1, 2, 3, 4, 5], [6, 7, 8, 9, 10], [11, 12, 13, 14, 15], [16, 17, 18, 19, 20], [21, 22, 23, 24, 25]]

def printMatrix(matrix):
    for i in range(len(matrix)):
        print(matrix[i])
    print()

printMatrix(matrix)
rotate4444(matrix)
#print(list(zip(*matrix[::-1])))

printMatrix(matrix)



