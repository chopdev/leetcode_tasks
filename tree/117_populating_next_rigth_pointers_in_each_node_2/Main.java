public class Main {
    public static void main(String[] args) {

        Node n0_2 = new Node(2);
        
        Node n1_1 = new Node(1);
        Node n1_3 = new Node(3);

        Node n2_0 = new Node(0);
        Node n2_7 = new Node(7);
        Node n2_9 = new Node(9);
        Node n2_1 = new Node(1);

        Node n3_2 = new Node(2);
        Node n3_1 = new Node(1);
        Node n3_0 = new Node(0);
        Node n3_8 = new Node(8);
        Node n3_88 = new Node(8);

        Node n4_7 = new Node(7);

        n0_2.left = n1_1;
        n0_2.right = n1_3;

        n1_1.left = n2_0;
        n1_1.right = n2_7;

        n1_3.left = n2_9;
        n1_3.right = n2_1;

        n2_0.left = n3_2;

        n2_7.left = n3_1;
        n2_7.right = n3_0;

        n2_1.left = n3_8;
        n2_1.right = n3_88;

        n3_0.left = n4_7;

        Solution sol = new Solution();
        sol.connect(n0_2);
    }
}