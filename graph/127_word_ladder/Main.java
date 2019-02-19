import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Main {

    public static void main(String[] args) {
	    String[] words = new String[]{"hot","dot","dog","lot","log","cog"};
        List<String> wordsList = new ArrayList<>(Arrays.asList(words));

        Solution solution = new Solution();
        int res = solution.ladderLength("hit", "cog", wordsList);
    }
}
