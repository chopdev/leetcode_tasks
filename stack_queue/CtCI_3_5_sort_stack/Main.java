import java.util.Stack;

public class Main {

    public static void main(String[] args) {
        Stack<Integer> s = new Stack<>();
        s.push(1);
        s.push(5);
        s.push(6);
        s.push(3);
        s.push(10);
        s.push(12);

        Solution solution = new Solution();
        solution.sort(s);

        while (!s.isEmpty()) {
            System.out.println(s.pop());
        }
    }
}
