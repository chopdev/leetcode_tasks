public class Main {

    public static void main(String[] args) {
	    int[][] graph = new int[5][];

	    // [[3], [2, 4], [1], [0, 4], [3, 1]]
        int[] r0 = new int[] {3};
        int[] r1 = new int[] {2, 4};
        int[] r2 = new int[] {1};
        int[] r3 = new int[] {0, 4};
        int[] r4 = new int[] {3, 1};

        graph[0] = r0;
        graph[1] = r1;
        graph[2] = r2;
        graph[3] = r3;
        graph[4] = r4;

        Solution sol = new Solution();
        boolean res1 = sol.isBipartite4444(graph);
    }
}
