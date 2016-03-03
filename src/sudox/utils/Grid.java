package sudox.utils;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by denis on 03/03/16.
 */
public class Grid {

    private static int ROWS = 9;
    private static int COLS = 9;
    private static int[] DIGITS = new int[]{1, 2, 3, 4, 5, 6, 7, 8, 9};

    private List<Element> values = new ArrayList<>();

    public Grid() {
        for (int i = 0; i < ROWS * COLS; i++) {
            values.add(new Element(DIGITS));
        }
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

    }

    public boolean eliminate(int position, int value) {
        Element e = values.get(position);
        if(!e.remove(value))
            return true;

        if(e.getNumberOfPossibleAssignments() == 0) {
            return false;
        } else if(e.getNumberOfPossibleAssignments() == 1) {

        }
    }

}
