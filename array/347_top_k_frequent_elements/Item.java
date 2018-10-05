public class Item implements Comparable<Item> {

    public int numb;
    public int count;

    public Item(int numb, int count) {
        this.numb = numb;
        this.count = count;
    }

    @Override
    public int compareTo(Item o) {
        return Integer.compare(o.count, count); // from bigger to smaller
    }
}
