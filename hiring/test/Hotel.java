public class Hotel implements Comparable<Hotel> {
    int id;
    int count;

    public Hotel(int id) {
        this.id = id;
    }

    @Override
    public int compareTo(Hotel o) {
        return Integer.compare(o.count, this.count);
    }
}
