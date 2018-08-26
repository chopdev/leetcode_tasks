import java.util.List;

public class Main {

    public static void main(String[] args) {
	    String[] projects = new String[] {"1", "2", "3", "4", "5", "7", "8"};
	    String[][] deps = new String[][] {
	            {"8", "7"},
                {"7", "4"},
                {"4", "5"},
                {"4", "3"},
                {"4", "2"},
                {"2", "1"},
                {"3", "1"}};

	    Solution s = new Solution();
	    List<String> res = s.buildOrder(projects, deps);

	    for (String str : res) {
	        System.out.print(str + " ");
        }
    }
}
