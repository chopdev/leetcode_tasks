import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Main {

    public static void main(String[] args) {
	    String[] words = new String[]{"hot","dot","dog","lot","log","cog"};
        List<String> wordsList = new ArrayList<>(Arrays.asList(words));

        Solution solution = new Solution();
        int res = solution.ladderLength3333("hit", "cog", wordsList);


        String[] words2 = new String[]{"a", "b", "c"};
        List<String> wordsList2 = new ArrayList<>(Arrays.asList(words2));
        int res2 = solution.ladderLength3333("a", "c", wordsList2);
    }
}
