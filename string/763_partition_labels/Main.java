import java.util.List;

public class Main {

    public static void main(String[] args) {
	    Solution sol = new Solution();
        List<Integer> res1 = sol.partitionLabels("ababcbacadefegdehijhklij"); // [9,7,8]

        List<Integer> res2 = sol.partitionLabels("abcd"); // [1,1,1,1]

        List<Integer> res3 = sol.partitionLabels("abcdak"); // [5, 1]
    }
}
