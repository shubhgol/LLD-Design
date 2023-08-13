package org.example;

import Modal.Board;
import Modal.Player;
import Modal.PlayingSymbol;
import Modal.SymbolType;

import java.util.Deque;
import java.util.LinkedList;
import java.util.Scanner;

public class TicToeGame {
    Deque<Player> players = new LinkedList<>();
    Board gameBoard;

    void initializeGame(int size){
        gameBoard = new Board(3);
        PlayingSymbol symbolX = new PlayingSymbol(SymbolType.X);
        PlayingSymbol symbolO = new PlayingSymbol(SymbolType.O);
        Player player1 = new Player("player1", symbolX);
        Player player2 = new Player("player2", symbolO);

        players.add(player1);
        players.add(player2);
    }

    public   String startGame(){
        boolean noWinner = true;
        while(noWinner){
            boolean isFreeSpace = gameBoard.isFreeSpaceExist();
            gameBoard.printBoard();
            if(!isFreeSpace){
                noWinner = false;
                continue;
            }

            Player playerTurn = players.removeFirst();
            System.out.print("Player:" + playerTurn.playerName + " Enter row,column: ");

            Scanner inputScanner = new Scanner(System.in);
            String s = inputScanner.nextLine();
            String values[] = s.split(" ");
            int row;
            int column;
            try {
                row = Integer.valueOf(values[0]);
                column = Integer.valueOf(values[1]);
            } catch (NumberFormatException e){
                System.out.println("Invalid Input, try again");
                players.addFirst(playerTurn);
                continue;
            }
            boolean isPlayerSymbolAddedSuccessfully = gameBoard.addSymbol(row, column, playerTurn.symbol);

            if(!isPlayerSymbolAddedSuccessfully){
                System.out.println("Incorrect position chosen, try again");
                players.addFirst(playerTurn);
                continue;
            }

            players.addLast(playerTurn);

            boolean winner = isThereWinner(row, column,playerTurn.symbol);

            if(winner){
                gameBoard.printBoard();
                return playerTurn.playerName;
            }

        }


        return "Tie";

    }


    public boolean isThereWinner(int row, int column, PlayingSymbol playingSymbol) {

        boolean rowMatch = true;
        boolean columnMatch = true;
        boolean diagonalMatch = true;
        boolean antiDiagonalMatch = true;

        //need to check in row
        for(int i=0;i<gameBoard.size;i++) {

            if(gameBoard.board[row][i] == null || gameBoard.board[row][i].symbol.name() != playingSymbol.symbol.name()) {
                rowMatch = false;
            }
        }

        //need to check in column
        for(int i=0;i<gameBoard.size;i++) {

            if(gameBoard.board[i][column] == null || gameBoard.board[i][column].symbol.name() != playingSymbol.symbol.name()) {
                columnMatch = false;
            }
        }

        //need to check diagonals
        for(int i=0, j=0; i<gameBoard.size;i++,j++) {
            if (gameBoard.board[i][j] == null || gameBoard.board[i][j].symbol.name() != playingSymbol.symbol.name()) {
                diagonalMatch = false;
            }
        }

        //need to check anti-diagonals
        for(int i=0, j=gameBoard.size-1; i<gameBoard.size;i++,j--) {
            if (gameBoard.board[i][j] == null || gameBoard.board[i][j].symbol.name() != playingSymbol.symbol.name()) {
                antiDiagonalMatch = false;
            }
        }

        return rowMatch || columnMatch || diagonalMatch || antiDiagonalMatch;
    }



}
