public class Main {

    public static void main(String[] args) {
        int[] arr1 = new int[6];
        int[] arr2 = new int[] {2, 5, 6};
        arr1[0] = 1;
        arr1[1] = 2;
        arr1[2] = 3;

        Solution solution = new Solution();
        solution.merge(arr1, 3, arr2, 3);



    }
}
