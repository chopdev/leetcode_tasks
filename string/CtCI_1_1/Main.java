public class Main {

    public static void main(String[] args) {

        String str = "ZZ";
        String str1 = "azaf";
        String str2 = "azaaf";
        String str3 = "azfz";

        Solution s = new Solution();

        boolean i1 = s.IsUnique2(str);
        boolean i2 = s.IsUnique2(str1);
        boolean i3 = s.IsUnique2(str2);
        boolean i4 = s.IsUnique2(str3);

        /*char t = '1';

        int f1 = t;
        int f2 = t - '0';

        System.out.println(f1);
        System.out.println(f2);

        System.out.println("");

        System.out.println('A' - 'a');
        System.out.println('B' - 'a');
        System.out.println('Z' - 'a');
        System.out.println('d' - 'a');
        System.out.println('z' - 'a');

        System.out.println((int)'0');

        System.out.println("");
        int t1 = 1<<-5;
        System.out.println(Integer.toBinaryString(t1));*/
    }
}
