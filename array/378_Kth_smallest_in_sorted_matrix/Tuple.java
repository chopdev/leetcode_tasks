public class Tuple implements Comparable<Tuple> {
    public int row;
    public int col;
    public int value;

    public Tuple(int row, int col, int value) {
        this.row = row;
        this.col = col;
        this.value = value;
    }

    @Override
    public int compareTo(Tuple o) {
        return Integer.compare(this.value, o.value);
    }
}
