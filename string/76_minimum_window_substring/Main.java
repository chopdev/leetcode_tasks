public class Main {

    public static void main(String[] args) {
        String s = "ADOBAEBANCCODE";
        String t = "ABCA";
        Solution sol = new Solution();
        String res = sol.minWindow(s, t);

        System.out.println(res);
    }
}
