import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class Main {

    public static void main(String[] args) {
	    Solution sol = new Solution();
	    //List<List<String>> res = sol.partition("aab");
        List<List<String>> res = sol.partition("aabaa");

        /*
        [a, a, b, a, a]
        [a, a, b, aa]
        [a, aba, a]
        [aa, b, a, a]
        [aa, b, aa]
        [aabaa]
        * */

	    for (List<String> s : res) {
	        System.out.println(Arrays.toString(s.toArray()));
        }
    }
}
