package tictactoe;

public interface Player {

    void turn();
    int[] getCoordinates();
    void setSymbol(int[] coordinates);
    void setOpponentSymbol(String opponentSymbol);
    void setMySymbol(String mySymbol);

}
