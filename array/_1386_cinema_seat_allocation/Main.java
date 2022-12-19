public class Main {
    public static void main(String[] args) {
        int[][] reserved = {{1,2},{1,3},{1,8},{2,6},{3,1},{3,10}};
        Solution solution = new Solution();
        //System.out.println(solution.maxNumberOfFamilies(3, reserved));

        int[][] tt = {{4,3},{1,4},{4,6},{1,7}};
        System.out.println(solution.maxNumberOfFamilies(4, tt));
    }
}
