import java.util.ArrayList;
import java.util.List;

public class Main {

    public static void main(String[] args) {
        Solution sol = new Solution();

        List<String> list = new ArrayList<>();
        list.add("cats");
        list.add("dogs");
        list.add("and");
        list.add("lol");
        list.add("catsandogs");

        boolean res = sol.wordBreak("catsandogs", list);
        boolean res2 = sol.wordBreak("catsaffndogs", list);


        List<String> list1 = new ArrayList<>();
        list1.add("a");
        list1.add("aaa");
        list1.add("aaaaaa");
        boolean res3 = sol.wordBreak("aaaaaaaaaaaaaaaab", list1);
    }
}
