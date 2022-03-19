package tictactoe;

import java.util.Random;

public class MediumPlayer implements Player{
    private String mySymbol;
    private String opponentSymbol;
    private final Cells cells;
    private final String showLevel = "Making move level \"medium\"";

    public MediumPlayer(Cells cells) {
        this.cells = cells;
    }

    public String getMySymbol() {
        return mySymbol;
    }

    public void setMySymbol(String mySymbol) {
        this.mySymbol = mySymbol;
    }

    public String getOpponentSymbol() {
        return opponentSymbol;
    }

    public void setOpponentSymbol(String opponentSymbol) {
        this.opponentSymbol = opponentSymbol;
    }

    public void turn() {
        System.out.println(showLevel);
        int[] getTurn = getCoordinates();
        setSymbol(getTurn);
    }

    @Override
    public int[] getCoordinates() {
            if (canWinNextStep(mySymbol) != null) {
                return canWinNextStep(mySymbol);
            } else if (canWinNextStep(opponentSymbol) != null) {
                return canWinNextStep(opponentSymbol);
            } else {
                return getRandomCoordinates();
            }
    }

    public int[] getRandomCoordinates() {
        Random rnd = new Random();
        int a = rnd.nextInt(3) + 1;
        int b = rnd.nextInt(3) + 1;
        return cells.getCells()[a][b].equals(" ") ? new int[]{a, b} : getCoordinates();
    }

    public int[] canWinNextStep(String s) {
        int count;
        for (int[][] arrays : cells.allVariants) {
            count = 0;
            for (int[] ints : arrays) {
                if (cells.getCells()[ints[0]][ints[1]].equals(s)) {
                    count++;
                }
            }
            if (count == 2) {
                for (int[] ints : arrays) {
                    if (cells.getCells()[ints[0]][ints[1]].equals(" ")) {
                        return ints;
                    }
                }
            }
        }
        return null;
    }

    @Override
    public void setSymbol(int[] coordinates) {
        cells.setValue(coordinates[0], coordinates[1], mySymbol);
    }

    @Override
    public String toString() {
        return showLevel;
    }
}
