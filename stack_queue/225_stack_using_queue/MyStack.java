import java.util.ArrayDeque;
import java.util.LinkedList;
import java.util.Queue;

// Mine solution
public class MyStack {
    private int count = 0;
    private Queue<Integer> q = new LinkedList<>();

    public void push(int x) {
        q.add(x);
        count ++;
    }

    public int pop() throws Exception {
        if(this.empty()) throw new Exception("empty");
        for (int i = 1; i < count; i++) {
            q.add(q.remove());
        }

        count --;
        return q.remove();
    }

    public int top() throws Exception {
        if(this.empty()) throw new Exception("empty");
        int temp = 0;
        for (int i = 0; i < count; i++) {
            temp = q.remove();
            q.add(temp);
        }

        return temp;
    }

    public boolean empty() {
        return count <= 0;
    }
}
