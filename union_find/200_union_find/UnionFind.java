public class UnionFind {
    int[] parents;
    int size; // number of components

    public  UnionFind(char[][] grid) {
        int rowLenght = grid[0].length;
        parents = new int[rowLenght * grid.length];
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < rowLenght; j++) {
                if(grid[i][j] == '1') {
                    int id = i * rowLenght + j;
                    parents[id] = id;
                    size++; // from the beginning each element references itself
                }
            }
        }
    }

    public int find(int id) {
        if(parents[id] == id) return id;

        parents[id] = find(parents[id]); // by recursion we make our tree flat, all elements look directly on tree root
        return parents[id];
    }

    // union two points in one component
    // returns current count of islands
    public void union(int id1, int id2) {
        int find1 = find(id1);
        int find2 = find(id2);

        if(find1 != find2) {
            parents[find1] = find2; // reference root of tree on other root of the tree
            size--;
        }
    }
}
