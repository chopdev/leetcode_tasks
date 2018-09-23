import java.util.List;

public class Main {

    public static void main(String[] args) {
        Solution s = new Solution();
        List<String> res = s.getPermutations2222("ABC");

        for(String str : res) {
            System.out.println(str);
        }
    }
}