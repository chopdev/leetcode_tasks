import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        Solution2 sol = new Solution2();

        List<List<String>> ingredients = new ArrayList<>();
        ingredients.add(Arrays.asList("yeast","flour"));
        ingredients.add(Arrays.asList("bread","meat"));
        List<String> res = sol.findAllRecipes(new String[] {"bread","sandwich"}, ingredients, new String[] {"yeast","flour","meat"});

        System.out.println(res);
    }
}
