public class Main {
    public static void main(String[] args) {
        Solution solution = new Solution();

        //System.out.println(solution.minEatingSpeed(new int[] {3,6,7,11}, 8)); // 4
        //System.out.println(solution.minEatingSpeed(new int[] {30,11,23,4,20}, 5)); // 30
        //System.out.println(solution.minEatingSpeed(new int[] {30,11,23,4,20}, 6)); // 23


        // failed case because of int overflow! need to clarify that always, int is  2^32 / 2  = 2^31 = 2.147.483.647 ~ 2 billions
        //System.out.println(solution.minEatingSpeed(new int[] {122890061,438301112,257983174,614414075,602060597,391389864,140358431,117439906,992360201,246252220}, 33212669)); // 119

    }
}
