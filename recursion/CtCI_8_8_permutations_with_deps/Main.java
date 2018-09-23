import java.util.List;

public class Main {

    public static void main(String[] args) {
        Solution s = new Solution();
        List<String> res1 = s.getPermutatations("AAA");
        List<String> res2 = s.getPermutatations("AABA");
        List<String> res3 = s.getPermutatations("AABBC");

        for (String str : res1)
            System.out.print(str + "  ");
        System.out.println();

        for (String str : res2)
            System.out.print(str + "  ");
        System.out.println();

        for (String str : res3)
            System.out.print(str + "  ");
        System.out.println();
    }
}
