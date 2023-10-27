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
    Board gameboard;

    TicToeGame(){
        gameboard = new Board(3);
        SymbolX symbolX = new SymbolX();
        SymbolO symbolO = new SymbolO();
        Player player1 = new Player("Player1", symbolX);
        Player player2 = new Player("Player2", symbolO);

        players.add(player1);
        players.add(player2);
    }

    public void startGame(){
        boolean noWinner = true;

        while(noWinner){
            gameboard.printBoard();
            boolean isFreeSpace = gameboard.checkFreeCellInBoard();

            if(!isFreeSpace){
                noWinner = false;
                continue;
            }
            Player playerTurn = players.removeFirst();
            System.out.print("Player:" + playerTurn.name + " Enter row,column: ");
            Scanner scanner = new Scanner(System.in);
            String input = scanner.nextLine();
            String []values = input.split(",");
            int row = Integer.parseInt(values[0]);
            int column = Integer.parseInt(values[1]);

            boolean isSymbolAdded = gameboard.addSymbol(row, column, playerTurn.symbol);

              if(!isSymbolAdded){
                   players.addFirst(playerTurn);
                   System.out.println("Please Try Again!");
              } else {
                  boolean isPlayerWin = checkIsPlayerWin(row, column, playerTurn.symbol);

                  if(isPlayerWin){
                      System.out.println(playerTurn.name+" "+"win the game");
                      gameboard.printBoard();
                      noWinner = false;
                  }
                  players.addLast(playerTurn);
              }

        }

        if(noWinner){
            gameboard.printBoard();
            System.out.println("Match has Tied. Please Restart Game!");
        }
    }

    private boolean checkIsPlayerWin(int row, int column, PlayingSymbol symbol){
        boolean isRowMatch = true;
        boolean isColumnMatch = true;
        boolean isDigonalMatch = true;
        boolean isAntiDiagonalMatch = true;

        //row check
        for(int i=0; i<gameboard.size; i++){
            if (gameboard.board[row][i] != symbol) {
                isRowMatch = false;
                break;
            }
        }
        //column check
        for(int i=0; i<gameboard.size; i++){
            if (gameboard.board[i][column] != symbol) {
                isColumnMatch = false;
                break;
            }
        }
        //diagonal check
        for(int i=0; i<gameboard.size; i++){
            if (gameboard.board[i][i] != symbol) {
                isDigonalMatch = false;
                break;
            }
        }
        //anti-diagonal check

        for(int i=0; i<gameboard.size; i++){
            if (gameboard.board[i][gameboard.size-i-1] != symbol) {
                isAntiDiagonalMatch = false;
                break;
            }
        }

        return  isRowMatch || isColumnMatch || isDigonalMatch || isAntiDiagonalMatch;
    }
}
