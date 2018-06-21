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

        System.out.println("Shift right unsigned");
        System.out.println(Integer.toBinaryString(t>>> 1));
        System.out.println(Integer.toBinaryString(t>>> 2));
        System.out.println(Integer.toBinaryString(t>>> 3));
        System.out.println(Integer.toBinaryString(t>>> 4));
        System.out.println(Integer.toBinaryString(t>>> 32));
        System.out.println(Integer.toBinaryString(t>>> 34));

        System.out.println("Shift right - negative value");
        int neg = -0b100101;
        System.out.println(neg);
        System.out.println(Integer.toBinaryString(neg));
        System.out.println(Integer.toBinaryString(neg >> 1));
        System.out.println(Integer.toBinaryString(neg>> 2));
        System.out.println(Integer.toBinaryString(neg>> 3));
        System.out.println(Integer.toBinaryString(neg>> 4));
        System.out.println(Integer.toBinaryString(neg>> 32));
        System.out.println(Integer.toBinaryString(neg>> 34));

        System.out.println("Shift right unsigned - negative value");

        System.out.println(Integer.toBinaryString(neg));
        System.out.println(Integer.toBinaryString(neg >>> 1));
        System.out.println(Integer.toBinaryString(neg>>> 2));
        System.out.println(Integer.toBinaryString(neg>>> 3));
        System.out.println(Integer.toBinaryString(neg>>> 4));
        System.out.println(Integer.toBinaryString(neg>>> 32));
        System.out.println(Integer.toBinaryString(neg>>> 34));

        System.out.println("");
        System.out.println(Integer.toBinaryString(-71));
        System.out.println("Shift right COMPARING");
        System.out.println(Integer.toBinaryString(-4));
        System.out.println("The operator ‘>>’ uses the sign bit (left most bit) to fill the trailing positions after shift");
        System.out.println(Integer.toBinaryString(-4 >> 1));
        System.out.println(-4 >> 1);
        System.out.println("In Java, the operator ‘>>>’ is unsigned right shift operator. It always fills 0 irrespective of the sign of the number.");
        System.out.println(Integer.toBinaryString(-4 >>> 1));
        System.out.println(-4 >>> 1);
        System.out.println("");

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
