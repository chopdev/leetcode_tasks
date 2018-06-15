public class Main {

    public static void main(String[] args) {
        Solution s = new Solution();

        System.out.println(s.reverse(-2147483412));
        System.out.println(s.reverse(1200));
        System.out.println(s.reverse(-103));
        System.out.println(s.reverse(0));
        System.out.println(s.reverse(900000));

        //2,147,483,647
        System.out.println(s.reverse(2147483646));
        System.out.println(s.reverse(2147483647));
        System.out.println(s.reverse(1534236469));
    }
}
