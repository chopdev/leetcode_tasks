public class Main {

    public static void main(String[] args) {
	    Solution s = new Solution();

	    boolean t1 = s.oneAway("polo", "oolo"); // true
        boolean t2 = s.oneAway("polo", "pblo"); // true
        boolean t3 = s.oneAway("polo", "polb"); // true

        boolean t4 = s.oneAway("polo", "apolo"); // true
        boolean t5 = s.oneAway("polo", "poolo"); // true
        boolean t6 = s.oneAway("polo", "poloo"); // true

        boolean t7 = s.oneAway("polo", "bblo"); // false
        boolean t8 = s.oneAway("polo", "pbbo"); // false
        boolean t9 = s.oneAway("polo", "pobb"); // false

        boolean t10 = s.oneAway("polo", "bolob"); // false
        boolean t11 = s.oneAway("polo", "pobbo"); // false
        boolean t12 = s.oneAway("polo", "polobb"); // false

        System.out.println(t1);
        System.out.println(t2);
        System.out.println(t3);
        System.out.println(t4);
        System.out.println(t5);
        System.out.println(t6);
        System.out.println(t7);
        System.out.println(t8);
        System.out.println(t9);
        System.out.println(t10);
        System.out.println(t11);
        System.out.println(t12);

    }
}
