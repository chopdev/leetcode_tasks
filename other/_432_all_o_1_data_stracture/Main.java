public class Main {
    public static void main(String[] args) {
        AllOne allOne = new AllOne();
        allOne.inc("hello");
        allOne.inc("hello");
        allOne.inc("world");
        allOne.inc("world");
        allOne.inc("hello");
        allOne.dec("world");

        System.out.println(allOne.getMaxKey()); // return "hello"
        System.out.println(allOne.getMinKey()); // // return "world"

        allOne.inc("world");
        allOne.inc("world");
        allOne.inc("leet");

        System.out.println(allOne.getMaxKey()); // return "world"
        System.out.println(allOne.getMinKey()); // // return "leet"

        allOne.inc("leet");
        allOne.inc("leet");

        System.out.println(allOne.getMinKey()); // // return "world"

        /*allOne.inc("a");
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
        */
    }

}
