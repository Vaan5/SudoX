package sudox.utils;


import java.util.*;

/**
 * Created by denis on 03/03/16.
 */
public class Grid {

    private static int ROWS = 9;
    private static int COLS = 9;
    private static int[] DIGITS = new int[]{1, 2, 3, 4, 5, 6, 7, 8, 9};

    private Map<Integer, Integer[]> peers = new HashMap<>();
    private Map<Integer, List<Integer[]>> units = new HashMap<>();

    private List<Element> values = new ArrayList<>();

    public Grid() {
        for (int i = 0; i < ROWS * COLS; i++) {
            values.add(new Element(DIGITS));
        }

        initializePeers();
    }

    public Grid(String representation) {
        String filteredRepresentation = representation.replaceAll("[^\\d.]", "");

        for (int i = 0; i < ROWS * COLS; i++) {
            int d = filteredRepresentation.charAt(i) - '0';
            if (d >= 1 && d <= 9)
                values.add(new Element(d));
            else
                values.add(new Element());
        }

        initializePeers();
    }

    private void initializePeers() {
        for(int i = 0; i < ROWS * COLS; i++) {
            Set<Integer> p = new HashSet<>();
            List<Integer[]> u = new ArrayList<>();
            List<Integer> unit1 = new ArrayList<>();
            List<Integer> unit2 = new ArrayList<>();
            List<Integer> unit3 = new ArrayList<>();

            int row = i / COLS;
            int col = i % COLS;

            for(int j = 0; j < ROWS; j++) {
                p.add(row * COLS + j);
                unit1.add(row * COLS + j);
                p.add(j * COLS + col);
                unit2.add(j * COLS + col);
            }

            int minRow = row / 3 * 3;
            int minCol = col / 3 * 3;

            for(int j = minRow; j < minRow + 3; j++) {
                for(int k = minCol; k < minCol + 3; k++) {
                    p.add(j * COLS + k);
                    unit3.add(j * COLS + k);
                }
            }

            u.add(unit1.toArray(new Integer[unit1.size()]));
            u.add(unit2.toArray(new Integer[unit2.size()]));
            u.add(unit3.toArray(new Integer[unit3.size()]));
            units.put(i, u);
            peers.put(i, p.toArray(new Integer[p.size()]));
        }
    }

    @Override
    public String toString() {
        String ret = "";
        for (int i = 0; i < ROWS; i++) {
            String row = "";
            for (int j = 0; j < COLS; j++) {
                row += " " + values.get(i * COLS + j).toString();
                if ((j + 1) % 3 == 0 && j != 8) {
                    row += " |";
                }
            }
            row = row.substring(1);
            ret += row + "\n";

            if ((i + 1) % 3 == 0 && i != 8) {
                ret += "------+-------+------\n";
            }
        }
        return ret;

    }

    public boolean assign(int position, int value) {
        Set<Integer> otherValues = new HashSet<>();

        otherValues.addAll(Arrays.asList(values.get(position).getValues()));
        otherValues.remove(value);

        for(Integer i : otherValues) {
            if(!eliminate(position, i))
                return false;
        }

        return true;
    }

    public boolean eliminate(int position, int value) {
        Element e = values.get(position);
        if(!e.remove(value))
            return true;

        if(e.getNumberOfPossibleAssignments() == 0) {
            return false;
        } else if(e.getNumberOfPossibleAssignments() == 1) {
            int digit = e.getValue();

            Integer[] p = peers.get(position);
            boolean flg = true;
            for(Integer i : p) {
                flg = eliminate(i, digit);
            }
            if(!flg) return flg;
        }

        List<Integer[]> fieldUnits = units.get(position);
        for(Integer[] u : fieldUnits) {
            int possiblePlaces = 0;
            int pos = 0;
            for (Integer p : u) {
                if(values.get(p).contains(value)) {
                    possiblePlaces++;
                    pos = p;
                }
            }

            if(possiblePlaces == 0) {
                return false;
            } else if(possiblePlaces == 1){
                if(!assign(pos, value)) {
                    return false;
                }
            }
        }

        return true;
    }

}
