import java.util.ArrayList;
import java.util.List;

public class Main {

    public static void main(String[] args) {
        Solution s = new Solution();

        List<List<Integer>> forwardRouteList = new ArrayList<>();
        List<List<Integer>> returnRouteList = new ArrayList<>();

        List<Integer> tmp1 = new ArrayList<>();
        tmp1.add(1);
        tmp1.add(8);

        List<Integer> tmp11 = new ArrayList<>();
        tmp11.add(2);
        tmp11.add(15);

        List<Integer> tmp111 = new ArrayList<>();
        tmp111.add(3);
        tmp111.add(9);

        List<Integer> tmp2 = new ArrayList<>();
        tmp2.add(1);
        tmp2.add(8);

        List<Integer> tmp22 = new ArrayList<>();
        tmp22.add(2);
        tmp22.add(11);

        List<Integer> tmp222 = new ArrayList<>();
        tmp222.add(3);
        tmp222.add(12);

        forwardRouteList.add(tmp1);
        forwardRouteList.add(tmp11);
        forwardRouteList.add(tmp111);
        returnRouteList.add(tmp2);
        returnRouteList.add(tmp22);
        returnRouteList.add(tmp222);
        s.optimalFlights2222(20, forwardRouteList, returnRouteList);
    }
}
