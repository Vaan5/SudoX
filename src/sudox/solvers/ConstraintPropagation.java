package sudox.solvers;

import sudox.utils.Grid;

/**
 * Created by denis on 03/03/16.
 */
public class ConstraintPropagation {

    private Grid grid;

    public ConstraintPropagation(String representation) {
        String filteredRepresentation = representation.replaceAll("[^\\d.]", "");

        for (int i = 0; i < 81; i++) {
            int d = filteredRepresentation.charAt(i) - '0';
            if (d >= 1 && d <= 9)
                grid.assign
            else
                values.add(new Element());
        }
    }
}
