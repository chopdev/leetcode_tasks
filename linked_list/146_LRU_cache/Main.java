public class Main {

    public static void main(String[] args) {
        LRUCache2222 cache = new LRUCache2222(2);
        cache.put(1, 1);
        cache.put(2, 2);
        int res1 = cache.get(1);       // returns 1
        cache.put(3, 3);    // evicts key 2
        int res2 = cache.get(2);       // returns -1 (not found)
        cache.put(4, 4);    // evicts key 1
        int res3 = cache.get(1);       // returns -1 (not found)
        int res4 = cache.get(3);       // returns 3
        int res5 = cache.get(4);       // returns 4



        cache = new LRUCache2222(2);
        cache.put(1, 1);
        cache.put(2, 2);
        int res6 = cache.get(1);    // 1
        cache.put(3, 3);
        int res66 = cache.get(2);   // -1

        cache.put(4, 4);
        int res7 =cache.get(1);  // -1
        int res8 =cache.get(3);  // 3
        int res9 =cache.get(4);  // 4



        cache = new LRUCache2222(1);
        cache.put(2, 1);
        int res10 = cache.get(2);    // 1
        cache.put(3, 2);
        int res11 = cache.get(2);   // -1
        int res12 = cache.get(3);  // 2

    }
}
