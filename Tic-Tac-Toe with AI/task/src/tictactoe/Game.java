package tictactoe;

import java.util.Scanner;

public class Game {
    private Player playerOne;
    private Player playerTwo;
    private final Cells cells;
    private final Scanner scanner;

    public Game(Cells cells, Scanner scanner) {
        this.cells = cells;
        this.scanner = scanner;
    }

    public Player getPlayerOne() {
        return playerOne;
    }

    public void setPlayerOne(Player playerOne) {
        this.playerOne = playerOne;
    }

    public Player getPlayerTwo() {
        return playerTwo;
    }

    public void setPlayerTwo(Player playerTwo) {
        this.playerTwo = playerTwo;
    }

    public void play() {
        while (true) {
            checkInput();
            cells.createCells();
            while (!checkWin()) {
                getPlayerOne().turn();
                System.out.println(cells);
                if (checkWin()) {
                    break;
                }
                getPlayerTwo().turn();
                System.out.println(cells);
            }
        }
    }

    public void checkInput() {
        while(true) {
            System.out.print("Input command: > ");
            String[] input = scanner.nextLine().split("\\s");
            if (!checkInputCommand(input)) {
                System.out.println("Bad parameters!");
                continue;
            }
            if (input[0].equals("exit")) {
                System.exit(0);
            }
            else if (checkInputPLayers(input[1])) {
                this.setPlayerOne(whoIsPlayer(input[1]));
                playerOne.setMySymbol("X");
                playerOne.setOpponentSymbol("O");
                if (checkInputPLayers(input[2])) {
                    this.setPlayerTwo(whoIsPlayer(input[2]));
                    playerTwo.setMySymbol("O");
                    playerTwo.setOpponentSymbol("X");
                    break;
                } else {
                    System.out.println("Bad parameters!");
                }
            } else {
                System.out.println("Bad parameters!");
            }
        }
    }

    public boolean checkInputCommand(String[] input) {
        return (input[0].equals("exit") || (input[0].equals("start")) && (input.length == 3));
    }

    public boolean checkInputPLayers(String s) {
        return s.equals("user") || s.equals("easy") || s.equals("medium") || s.equals("hard");
    }

    public Player whoIsPlayer(String s) {
        return switch (s) {
            case "easy" -> new EasyPlayer(cells);
            case "medium" -> new MediumPlayer(cells);
            case "hard" -> new HardPlayer(cells);
            default -> new HumanPlayer(scanner, cells);
        };
    }

    public int analysisCells() {
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

    public boolean checkWin() {
        switch (analysisCells()) {
            case 4:
                return false;
            case 1:
                System.out.println("X wins");
                return true;
            case 2:
                System.out.println("O wins");
                return true;
            case 3:
                System.out.println("Draw");
                return true;
        }
        return true;
    }
}
