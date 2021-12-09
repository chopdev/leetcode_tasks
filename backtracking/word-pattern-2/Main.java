public class Main {

    public static void main(String[] args) {
	    Solution sol = new Solution();

        /*System.out.println(sol.fitsPattern("erekek", 2, "ek")); // true
        System.out.println(sol.fitsPattern("erekek", 0, "erekek")); // true
        System.out.println(sol.fitsPattern("erekek", 4, "ek")); // true
        System.out.println(sol.fitsPattern("erekek", 0, "ek")); // false
        System.out.println(sol.fitsPattern("erekek", 4, "ekk")); // false*/

        System.out.println(sol.isPattern("aab", "ererek")); // true
        System.out.println(sol.isPattern("aaa", "bbb")); // true
        System.out.println(sol.isPattern("aaa", "sqfsqfsqf")); // true
        System.out.println(sol.isPattern("abca", "sqbbbkksq")); // true
        System.out.println(sol.isPattern("cabca", "kksqbbbkksq")); // true
        System.out.println(sol.isPattern("cabca", "ksqbbbkksq")); // true
        System.out.println(sol.isPattern("aab", "bbb")); //true.  should it be considered as true or false? can be made as false by usage of HashSet with unique words for pattern chars
        System.out.println(sol.isPattern("aaa", "sqfsqfhqf")); // false
        System.out.println(sol.isPattern("cabca", "qbbbkksq")); // false
        System.out.println(sol.isPattern("aaa", "b")); // false

    }
}
