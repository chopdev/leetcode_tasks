# Zero Matrix: Write an algorithm such that if an element in an MxN matrix is 0, its entire row and
# column are set to 0.

# My O(N*M) solution, O(N+M) space
# space can be improved to O(M) columns, because there is no reason to scan row completely if 0 is found
def setZeros(matrix):
    if matrix is None or len(matrix) == 0:
        return
    zeroRows = set()
    zeroColumns = set()
    searchZeros(matrix, zeroRows, zeroColumns)
    
    for row in range(0, len(matrix)):
        for col in range(0, len(matrix[0])):
            if row in zeroRows or col in zeroColumns:
                matrix[row][col] = 0

def searchZeros(matrix, zeroRows, zeroColumns):
    for row in range(0, len(matrix)):
        for col in range(0, len(matrix[0])):
            if matrix[row][col] == 0:
                zeroRows.add(row)
                zeroColumns.add(col)

# My O(N*M) solution, O(1) space
def setZeros2222(matrix):
    if matrix is None or len(matrix) == 0:
        return
    for row in range(0, len(matrix)):
        for col in range(0, len(matrix[0])):
            if matrix[row][col] == 0:
                matrix[row] = [0] * len(matrix[0])
                setColumn(matrix, col, -1)

    for col in range(0, len(matrix[0])):
        if matrix[0][col] == -1:
            setColumn(matrix, col, 0)

def setColumn(matrix, col, val):
    for row in range(len(matrix)):
        matrix[row][col] = val

# Third solutions is to use two booleans for first row and first column to detect if they should be 0. 
# Use first row and column as a marker for other rows ans columns
# nullify rows and columns by markers
# in the end rely on booleans to nullify 1 row and 1 column

###########################################################################
def printMatrix(matrix):
    for i in range(len(matrix)):
        print(matrix[i])
    print()

matr = [[1, 2, 3, 4], [5, 6, 7, 8], [9, 10, 0, 0]]

printMatrix(matr)
setZeros2222(matrix=matr)
printMatrix(matr)




