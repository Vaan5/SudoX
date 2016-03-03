package sudox.main;

import sudox.utils.Grid;

/**
 * Created by denis on 03/03/16.
 */
public class Main {

    public static void main(String[] args) {
        String grid1 = "003020600900305001001806400008102900700000008006708200002609500800203009005010300";

        Grid g = new Grid(grid1);
        System.out.println(g);
    }
}
