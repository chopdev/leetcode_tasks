public class Main {

    public static void main(String[] args) {
        Solution sol = new Solution();
        int[] arr1 = new int[] {1, 2, 3, 4, 5, 6, 7};
        int[] arr2 = new int[] {8, 8, 10, 10, 11, 12, 12, 12, 13, 3, 2, 1};
        int[] arr3 = new int[] {3, 5, 2, 1, 6, 4};
        int[] arr4 = new int[] {3, 5, 2, 1, 6, 4};

        sol.wiggleSort(arr1);
        sol.wiggleSort(arr2);
        sol.wiggleSort(arr3);
        sol.wiggleSort(arr4);
    }
}
