public class Pair implements Comparable<Pair>{
    int firstId;
    int secondId;
    int sum;

    public Pair(int first, int second, int sum) {
        firstId = first;
        secondId = second;
        this.sum = sum;
    }
    @Override
    public int compareTo(Pair o) {
        return Double.compare(this.sum, o.sum) * (-1);
    }
}
