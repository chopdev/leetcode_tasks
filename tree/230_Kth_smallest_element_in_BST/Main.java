public class Main {

    /*
            80
        /        \
       50       100
    /     \
    25     60
    /  \    /  \
    2  35  55  66     
        
    */
    public static void main(String[] args) {
	    TreeNode r80 = new TreeNode(80);
        TreeNode r50 = new TreeNode(50);
        TreeNode r25 = new TreeNode(25);
        TreeNode r60 = new TreeNode(60);
        TreeNode r2 = new TreeNode(2);
        TreeNode r35 = new TreeNode(35);
        TreeNode r55 = new TreeNode(55);
        TreeNode r66 = new TreeNode(66);
        TreeNode r100 = new TreeNode(100);

        r80.left = r50;
        r80.right = r100;

        r50.left = r25;
        r50.right = r60;

        r25.left = r2;
        r25.right = r35;

        r60.left = r55;
        r60.right = r66;

        Solution s = new Solution();
        int res = s.kthSmallest(r80, 6); // 60
        int res1 = s.kthSmallest(r80, 1); // 2
        int res2 = s.kthSmallest(r80, 2); // 25
        int res3 = s.kthSmallest(r80, 9); //100

        System.out.println(res);
        System.out.println(res1);
        System.out.println(res2);
        System.out.println(res3);
    }
}
