package tictactoe;

public class HardPlayer implements Player {
    private String mySymbol;
    private String opponentSymbol;
    private final Cells cells;
    private final String showLevel = "Making move level \"hard\"";
    public static int sum;

    public HardPlayer(Cells cells) {
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

    @Override
    public void turn() {
        System.out.println(showLevel);
        int[] getTurn = getCoordinates();
        setSymbol(getTurn);
    }

    @Override
    public int[] getCoordinates() {
        if (canWinNextStep(getMySymbol()) != null) {
            return canWinNextStep(getMySymbol());
        } else if (canWinNextStep(getOpponentSymbol()) != null) {
            return canWinNextStep(getOpponentSymbol());
        } else {
            return minimax();
        }
    }

    public int[] minimax() {
        int bestResul = -1_000_000;
        int a = 0;
        int b = 0;
        for (int i = 1; i < cells.getCells().length; i++) {
            for (int j = 1; j < cells.getCells()[i].length; j++) {
                if (cells.getValue(i, j).equals(" ")) {
                    cells.setValue(i, j, getMySymbol());
                    sum = 0;
                    int max = getResult(0, false);
                    cells.setValue(i, j, " ");
                    if (max > bestResul) {
                        bestResul = max;
                        a = i;
                        b = j;
                    }
                }
            }
        }
        return new int[]{a, b};
    }

    public int getResult(int depth, boolean isMax) {
        int result = analysisCells(cells);
        int myNumber = getMySymbol().equals("X") ? 1 : 2;
        int opponentNumber = myNumber == 1 ? 2 : 1;
        if (result == myNumber) {
            return sum + 10;
        }
        if (result == opponentNumber) {
            return sum - 10;
        }
        if (result == 3) {
            return sum;
        }
        if (isMax) {
            for (int i = 1; i < cells.getCells().length; i++) {
                for (int j = 1; j < cells.getCells()[i].length; j++) {
                    if (cells.getValue(i, j).equals(" ")) {
                        cells.setValue(i, j, getMySymbol());
                        sum += getResult(depth + 1, false);
                        cells.setValue(i, j, " ");
                    }
                }
            }
        } else {
            for (int i = 1; i < cells.getCells().length; i++) {
                for (int j = 1; j < cells.getCells()[i].length; j++) {
                    if (cells.getValue(i, j).equals(" ")) {
                        cells.setValue(i, j, getOpponentSymbol());
                        sum += getResult(depth + 1, true);
                        cells.setValue(i, j, " ");
                    }
                }
            }
        }
        return sum;
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
        cells.setValue(coordinates[0], coordinates[1], getMySymbol());
    }

    public int analysisCells(Cells cells) {
        boolean isFull = true;
        for (int[][] ints : cells.allVariants) {
            if (!cells.getCells()[ints[0][0]][ints[0][1]].equals(" ")) {
                if (cells.getCells()[ints[0][0]][ints[0][1]].equals(cells.getCells()[ints[1][0]][ints[1][1]])) {
                    if (cells.getCells()[ints[1][0]][ints[1][1]].equals(cells.getCells()[ints[2][0]][ints[2][1]])) {
                        return cells.getCells()[ints[0][0]][ints[0][1]].equals("X") ? 1 : 2; //strings[0] + " wins"
                    }
                }
            }
            if (isFull) {
                if (!cells.getCells()[ints[0][0]][ints[0][1]].equals(" ")) {
                    if (!cells.getCells()[ints[1][0]][ints[1][1]].equals(" ")) {
                        if (cells.getCells()[ints[2][0]][ints[2][1]].equals(" ")) {
                            isFull = false;
                        }
                    } else {
                        isFull = false;
                    }
                } else {
                    isFull = false;
                }
            }
        }
        return isFull ? 3 : 4; //"Draw" : "Game not finished";
    }

    @Override
    public String toString() {
        return showLevel;
    }
}
