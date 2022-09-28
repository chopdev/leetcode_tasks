import java.util.Arrays;

public class Main {
    public static void main(String[] args) {
        Solution sol = new Solution();

        System.out.println(Arrays.toString(sol.getArrangement(6, 3)));
        System.out.println(Arrays.toString(sol.getArrangement(3, 9)));
        System.out.println(Arrays.toString(sol.getArrangement(3, 0)));
        System.out.println(Arrays.toString(sol.getArrangement(0, 0)));
        System.out.println(Arrays.toString(sol.getArrangement(3, 3)));

        System.out.println();
        System.out.println(Arrays.toString(sol.getArrangement(6, 4)));
    }
}
