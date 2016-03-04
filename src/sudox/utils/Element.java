package sudox.utils;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by denis on 03/03/16.
 */
public class Element {
    private boolean fixed = false;

    private Set<Integer> values = new HashSet<>();

    public Element() {

    }

    public Element(int value) {
        values.add(value);
        fixed = true;
    }

    public Element(int... values) {
        for(int i = 0; i < values.length; i++) {
            this.values.add(values[i]);
        }
    }

    public int getValue() {
        if(values.size() != 1)
            throw new IllegalStateException("Field contains more than one value");


        Integer[] array = values.toArray(new Integer[values.size()]);
        return array[0];
    }

    public Integer[] getValues() {
        return values.toArray(new Integer[values.size()]);
    }

    @Override
    public String toString() {
        if(values.size() == 0) {
            return ".";
        } else {
            Integer[] array = (Integer[]) values.toArray(new Integer[values.size()]);
            String ret = "";
            for (Integer i : array) {
                ret += i.toString();
            }
            return ret;
        }

    }

    public boolean addValue(int value) {
        if(values.contains(value))
            return false;
        values.add(value);
        return true;
    }

    public boolean remove(int value) {
        return values.remove(value);
    }

    public int getNumberOfPossibleAssignments() {
        return values.size();
    }

    public boolean isFixed() {
        return fixed;
    }

    public void setFixed(boolean fixed) {
        this.fixed = fixed;
    }

    public boolean contains(int value) {
        return values.contains(value);
    }
}
