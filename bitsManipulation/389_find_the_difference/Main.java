public class Main {

    public static void main(String[] args) {
        String s = "abcd";
        String t = "abcde";

        Solution solution = new Solution();
        char res1 = solution.findTheDifference(s, t);

        System.out.println(res1);
    }
}
