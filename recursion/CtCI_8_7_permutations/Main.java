import java.util.List;

public class Main {

    public static void main(String[] args) {
        Solution s = new Solution();
        List<String> res = s.getPermutations("ABC");

        for(String str : res) {
            System.out.println(str);
        }
    }
}