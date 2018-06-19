public class Main {


    public static void main(String[] args) {
        int x = 1;
        int y = 4;
        Solution solution = new Solution();
        int res1 = solution.hammingDistance(x, y);

        int t = 0b0101;  // 5
        System.out.println(Integer.toBinaryString(t));
        System.out.println("Shift right");
        System.out.println(Integer.toBinaryString(t>> 1));
        System.out.println(Integer.toBinaryString(t>> 2));
        System.out.println(Integer.toBinaryString(t>> 3));
        System.out.println(Integer.toBinaryString(t>> 4));
        System.out.println(Integer.toBinaryString(t>> 32));
        System.out.println(Integer.toBinaryString(t>> 34));

        System.out.println("Shift left normal");

        System.out.println(Integer.toBinaryString(t<< 1));
        System.out.println(Integer.toBinaryString(t<< 2));
        System.out.println(Integer.toBinaryString(t<< 3));
        System.out.println(Integer.toBinaryString(t<< 4));
        System.out.println(Integer.toBinaryString(t<< 5));
        System.out.println("Shift left big");

        int fifteen = 0b1111;  // 15
        System.out.println(Integer.toBinaryString(fifteen<< 29));
        System.out.println(Integer.toBinaryString(fifteen<< 30));
        System.out.println(Integer.toBinaryString(fifteen<< 31));
        System.out.println(Integer.toBinaryString(fifteen<< 32));
        System.out.println(Integer.toBinaryString(fifteen<< 33));


        System.out.println("");
        System.out.println(res1);
    }
}
