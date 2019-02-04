public class Node implements Comparable<Node>{
    public double val;
    public int index;

    public Node(double val, int index) {
        this.val = val;
        this.index = index;
    }

    @Override
    public int compareTo(Node o) {
        return Double.compare(o.val, val);
    }
}
