import java.util.Stack;

public class Main {

    public static void main(String[] args) throws Exception {

        Solution s = new Solution();
        Stack<Integer> first = new Stack<>();
        Stack<Integer> second = new Stack<>();
        Stack<Integer> third = new Stack<>();

        first.push(5);
        first.push(4);
        first.push(3);
        first.push(2);
        first.push(1);

        s.moveToThird(first, second, third);
        System.out.println(third.pop());
    }
}
