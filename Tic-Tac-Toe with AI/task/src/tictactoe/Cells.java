package tictactoe;

import java.util.ArrayList;

public class Cells {
    private final String[][] cells = new String[4][4];
    ArrayList<int[][]> allVariants = new ArrayList<>();

    public void loadAllVariants() {
        allVariants.add(new int[][]{new int[]{1, 1}, new int[]{2, 1}, new int[]{3, 1}});
        allVariants.add(new int[][]{new int[]{1, 2}, new int[]{2, 2}, new int[]{3, 2}});
        allVariants.add(new int[][]{new int[]{1, 3}, new int[]{2, 3}, new int[]{3, 3}});
        allVariants.add(new int[][]{new int[]{1, 1}, new int[]{1, 2}, new int[]{1, 3}});
        allVariants.add(new int[][]{new int[]{2, 1}, new int[]{2, 2}, new int[]{2, 3}});
        allVariants.add(new int[][]{new int[]{3, 1}, new int[]{3, 2}, new int[]{3, 3}});
        allVariants.add(new int[][]{new int[]{1, 1}, new int[]{2, 2}, new int[]{3, 3}});
        allVariants.add(new int[][]{new int[]{3, 1}, new int[]{2, 2}, new int[]{1, 3}});
    }

    public Cells() {
        createCells();
        loadAllVariants();
    }

    public String[][] getCells() {
        return cells;
    }

    public String getValue(int a, int b) {
        return cells[a][b];
    }

    public void setValue(int a, int b, String symbol) {
        cells[a][b] = symbol;
    }

    public void createCells() {
        for (int i = 1; i < 4; i++) {
            for (int j = 1; j < 4; j++) {
                cells[i][j] = " ";
            }
        }
    }

    @Override
    public String toString() {
        return "---------\n" +
                "| " + cells[1][1] + " " + cells[1][2] + " " + cells[1][3] + " |\n" +
                "| " + cells[2][1] + " " + cells[2][2] + " " + cells[2][3] + " |\n" +
                "| " + cells[3][1] + " " + cells[3][2] + " " + cells[3][3] + " |\n" +
                "---------";
    }
}
