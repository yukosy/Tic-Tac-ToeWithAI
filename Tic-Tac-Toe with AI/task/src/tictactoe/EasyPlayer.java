package tictactoe;

import java.util.Random;

public class EasyPlayer implements Player{
    private String mySymbol;
    private String opponentSymbol;
    private final Cells cells;
    private final String showLevel = "Making move level \"easy\"";

    public EasyPlayer(Cells cells) {
        this.cells = cells;
    }

    public String getMySymbol() {
        return mySymbol;
    }

    @Override
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
        return getRandomCoordinates();
    }

    public int[] getRandomCoordinates() {
        Random rnd = new Random();
        int a = rnd.nextInt(3) + 1;
        int b = rnd.nextInt(3) + 1;
        return cells.getCells()[a][b].equals(" ") ? new int[]{a, b} : getCoordinates();
    }

    public void setSymbol(int[] coordinates) {
        cells.setValue(coordinates[0], coordinates[1], getMySymbol());
    }

    @Override
    public String toString() {
        return showLevel;
    }
}
