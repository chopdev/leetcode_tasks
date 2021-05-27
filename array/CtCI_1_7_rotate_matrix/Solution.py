#Rotate Matrix: Given an image represented by an NxN matrix, where each pixel in the image is 4
# bytes, write a method to rotate the image by 90 degrees. Can you do this in place?


#
#    1  2  3 4        1 2 3  
#   12 13 14 5        8 9 4
#   11 16 15 6        7 6 5
#   10 9  8  7
#
# row of external is bigger on 2 items, matrix is decreasing twice with each layer
# consider rotation of not first element in a row


# O(N^2) solution
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





###########################################
#matrix = [[1, 2, 3, 4], [12, 13, 14, 5], [11, 16, 15, 6], [10, 9, 8, 7]]
matrix = [[1, 2, 3, 4, 5], [6, 7, 8, 9, 10], [11, 12, 13, 14, 15], [16, 17, 18, 19, 20], [21, 22, 23, 24, 25]]

def printMatrix(matrix):
    for i in range(len(matrix)):
        print(matrix[i])
    print()

printMatrix(matrix)
rotate(matrix)
printMatrix(matrix)



