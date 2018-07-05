public class Main {

    public static void main(String[] args) {
        Solutuion s = new Solutuion();
        boolean res = s.canPartition(new int[]{ 1, 2, 7, 4});
        res = s.canPartition(null);
        res = s.canPartition(new int[]{ 1});
        res = s.canPartition(new int[]{ 1, 4, 5, 6});
        res = s.canPartition(new int[]{ 1, 10, 11, 10, 10});
    }
}
