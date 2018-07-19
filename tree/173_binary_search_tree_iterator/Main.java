public class Main {

    public static void main(String[] args) {
        TreeNode root1 = new TreeNode(1);
        root1.right = new TreeNode(2);
        BSTIterator j = new BSTIterator(root1);
        while (j.hasNext())
            System.out.print(j.next() + "->");

        System.out.println();

	    TreeNode root = new TreeNode(100);
        TreeNode r50 = new TreeNode(50);
        TreeNode r10 = new TreeNode(10);
        TreeNode r1 = new TreeNode(1);
        TreeNode r15 = new TreeNode(15);
        TreeNode r60 = new TreeNode(60);
        TreeNode r130 = new TreeNode(130);
        TreeNode r120 = new TreeNode(120);
        TreeNode r110 = new TreeNode(110);
        TreeNode r150 = new TreeNode(150);

        root.left = r50;
        root.right = r130;

        r50.left = r10;
        r50.right = r60;

        r10.left =  r1;
        r10.right = r15;

        r130.left = r120;
        r130.right = r150;

        r120.left = r110;

        BSTIterator i = new BSTIterator(root);
        while (i.hasNext())
            System.out.print(i.next() + "->");
    }
}
