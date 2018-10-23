import java.util.ArrayList;
import java.util.List;

public class Main {

    public static void main(String[] args) {
        NestedIntegerImpl r1 = new NestedIntegerImpl(1);
        NestedIntegerImpl r2 = new NestedIntegerImpl(1);

        NestedIntegerImpl r3 = new NestedIntegerImpl(2);

        NestedIntegerImpl r4 = new NestedIntegerImpl(1);
        NestedIntegerImpl r5 = new NestedIntegerImpl(1);

        List<NestedInteger> list1 = new ArrayList<>();
        list1.add(r1);
        list1.add(r2);

        List<NestedInteger> list2 = new ArrayList<>();
        list2.add(r4);
        list2.add(r5);

        NestedIntegerImpl r6 = new NestedIntegerImpl(list1);
        NestedIntegerImpl r7 = new NestedIntegerImpl(list2);

        List<NestedInteger> list3 = new ArrayList<>();
        list3.add(r6);
        list3.add(r3);
        list3.add(r7);

        NestedIterator iterator  = new NestedIterator(list3);

        while (iterator.hasNext())
            System.out.println(iterator.next());

        List<NestedInteger> list555 = new ArrayList<>();
        List<NestedInteger> list777 = new ArrayList<>();
        list777.add(new NestedIntegerImpl(list555));
        NestedIterator iterator555  = new NestedIterator(list777);

        while (iterator555.hasNext())
            System.out.println(iterator555.next());
    }
}
