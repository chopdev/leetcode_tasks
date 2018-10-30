import java.util.List;

public class Main {

    public static void main(String[] args) {
	    Solution s = new Solution();
        String[] arr = new String[] {"eat", "tea", "tan", "ate", "nat", "bat"};
        List<List<String>> res = s.groupAnagrams(arr);

        for (List<String> list: res) {
            for (String str: list) {
                System.out.print(str + " ");
            }
            System.out.println();
        }
    }
}
