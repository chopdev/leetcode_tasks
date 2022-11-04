import java.util.ArrayList;

public class Main {

    public static void main(String[] args) {
        Solution sol = new Solution();

        Node node1 = new Node(1, new ArrayList<>());
        Node node2 = new Node(2, new ArrayList<>());
        Node node3 = new Node(3, new ArrayList<>());
        Node node4 = new Node(4, new ArrayList<>());
        Node node5 = new Node(5, new ArrayList<>());
        Node node6 = new Node(6, new ArrayList<>());

        node1.children.add(node3);
        node1.children.add(node2);
        node1.children.add(node4);

        node3.children.add(node5);
        node3.children.add(node6);

        System.out.println(sol.serialize(node1));


        Node res1 = sol.deserialize("1,3,3,2,2,0,4,0,5,0,6,0");
    }
}
