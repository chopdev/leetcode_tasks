public class Main {

    public static void main(String[] args) {
        Solution s = new Solution();
        int t1= s.count(4);
        int t2= s.count(10);

        int lol1 = s.countDynamic(4);
        int lol2 = s.countDynamic(10);
        int lol3 = s.countDynamic(1);
        System.out.println(t1);
        System.out.println(t2);

        System.out.println(lol1);
        System.out.println(lol2);
        System.out.println(lol3);
    }
}
