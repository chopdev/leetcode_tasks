public class Main {

    public static void main(String[] args) {
	    MapSum mp = new MapSum();
	    mp.insert("apple", 3);
	    int tt = mp.sum("ap");   // 3
	    mp.insert("app", 2);
        tt = mp.sum("ap");       // 5
        mp.insert("appl", 1);
        tt = mp.sum("ap");      // 6

        mp.insert("app", 3);
        tt = mp.sum("ap");      // 7


        // failed cases
        mp.insert("b", 3);
        tt = mp.sum("ba"); // 0
    }
}
