import java.util.Arrays;

public class Main {

    public static void main(String[] args) {
        Solution solution = new Solution();
        int[][] input = {{1,4},{1,3},{2,6},{8,10},{15,18},{20, 25},{20, 25},{25, 26}};
        int[][] res = solution.merge(input);

        for (int[] arr : res) {
            System.out.println(Arrays.toString(arr));
        }

    }
}
