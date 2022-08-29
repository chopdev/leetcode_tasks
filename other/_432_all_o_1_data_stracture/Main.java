public class Main {
    public static void main(String[] args) {
        AllOne allOne = new AllOne();
        allOne.inc("a");
        allOne.inc("b");
        allOne.inc("c");

        allOne.inc("d");
        allOne.inc("a");
        allOne.inc("b"); // 2 b

        allOne.inc("c");
        allOne.inc("d");
        allOne.inc("c"); // 3

        allOne.inc("d");
        allOne.inc("d"); // 4 d
        allOne.inc("a"); // 3 a
        System.out.println(allOne.getMaxKey()); // return "d"
        System.out.println(allOne.getMinKey()); // return "b"
    }

}
