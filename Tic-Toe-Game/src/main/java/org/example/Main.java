package org.example;

public class Main {
    public static void main(String[] args) {
        TicToeGame game = new TicToeGame();

        game.initializeGame(3);

        System.out.println("game winner is: " + game.startGame());

    }
}