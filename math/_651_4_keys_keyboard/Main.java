public class Main {
    public static void main(String[] args) {
        Solution sol = new Solution();

        /*System.out.println(sol.maxA(7)); // 9
        System.out.println(sol.maxA(8)); // 12
        System.out.println(sol.maxA(9)); // 16
        System.out.println(sol.maxA(10)); // 20
        System.out.println(sol.maxA(11)); // 27
        System.out.println(sol.maxA(12)); // 36
        System.out.println(sol.maxA(13)); // 48*/
        System.out.println(sol.maxA(14)); // 64


        //sol.maxA(9); // 1 1 1   1 1 1  1 1 1   1 1 1  1 1 1     +3  + 2 copy + 1 paste + 1 paste + 1 paste + 1 paste
                        // 1 1 1 1   1 1 1 1  1 1 1 1  1 1 1 1     +4  + 2 copy + 1 paste + 1 + 1
    }
}
