import java.util.List;

public class NestedIntegerImpl implements NestedInteger {

    public List<NestedInteger> nested;
    public int integer;

    public NestedIntegerImpl(int integer) {
        this.integer = integer;
    }

    public NestedIntegerImpl(List<NestedInteger> nested) {
        this.nested = nested;
    }

    @Override
    public boolean isInteger() {
        if(nested == null) return true;
        return false;
    }

    @Override
    public Integer getInteger() {
        return integer;
    }

    @Override
    public List<NestedInteger> getList() {
        return nested;
    }
}
