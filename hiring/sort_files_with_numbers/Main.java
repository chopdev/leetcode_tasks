import java.util.Arrays;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        Solution sol = new Solution();

        List<String> res = sol.sort(Arrays.asList("file30.gif", "a00001b.gif",
                "a.gif", "a.giff", "a2b.gif",
                "e2.gif", "e0002.gif",
                "file.gif", "file30.gif",
                "file00150.gif", "file100.gif"));

        System.out.println(String.join("\n", res));
    }
}
