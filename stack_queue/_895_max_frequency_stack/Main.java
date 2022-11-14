public class Main {
    public static void main(String[] args) {
        FreqStack stack = new FreqStack();

        stack.push(5); // The stack is [5]
        stack.push(7); // The stack is [5,7]
        stack.push(5); // The stack is [5,7,5]
        stack.push(7); // The stack is [5,7,5,7]
        stack.push(4); // The stack is [5,7,5,7,4]
        stack.push(5); // The stack is [5,7,5,7,4,5]
        System.out.println();
        System.out.println("POP: " + stack.pop());   // return 5, as 5 is the most frequent. The stack becomes [5,7,5,7,4].
        System.out.println("POP: " + stack.pop());   // return 7, as 5 and 7 is the most frequent, but 7 is closest to the top. The stack becomes [5,7,5,4].
        System.out.println("POP: " + stack.pop());   // return 5, as 5 is the most frequent. The stack becomes [5,7,4].
        System.out.println("POP: " + stack.pop());   // return 4, as 4, 5 and 7 is the most frequent, but 4 is closest to the top. The stack becomes [5,7].
    }
}
