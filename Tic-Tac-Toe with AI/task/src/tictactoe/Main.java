package tictactoe;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Cells cell = new Cells();
        Scanner scanner = new Scanner(System.in);

        Game game = new Game(cell, scanner);
        game.play();
    }
}
