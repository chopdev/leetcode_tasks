public class Main {

    public static void main(String[] args) {
        TimeMap kv = new TimeMap();
        kv.set("foo", "bar", 1); // store the key "foo" and value "bar" along with timestamp = 1
        String res1 = kv.get("foo", 1);  // output "bar"
        String res2 =kv.get("foo", 3); // output "bar" since there is no value corresponding to foo at timestamp 3 and timestamp 2, then the only value is at timestamp 1 ie "bar"

        kv.set("foo", "bar2", 4);
        String res3 = kv.get("foo", 4); // output "bar2"
        String res4 = kv.get("foo", 5); //output "bar2"


        kv.set("love","high",10);
        kv.set("love","low",20);
        String res5 = kv.get("love",5); // ""
        String res6 = kv.get("love",10); // high
        String res7 = kv.get("love",15); // high
        String res8 = kv.get("love",20); // low
        String res9 = kv.get("love",25); // low
    }
}
