package com.chess_test;



public class Main
{
    public static void main(String[] args) {
        clearConsole();
        System.out.println("Welcome to my chess game (enter to continue)");
        System.out.println("I coded this entirely by myself, with the slighest bit of help from Ai to help me understand things better");

        InputAndDisplay.tutorial();
        
        Board board = new Board();
        InputAndDisplay inputAndDisplay = new InputAndDisplay(board);
        while (!inputAndDisplay.isGameOver()){
            inputAndDisplay.runMove();
        }
        while (inputAndDisplay.promptPlayAgain()){
            clearConsole();
            InputAndDisplay.tutorial();
            board = new Board();
            inputAndDisplay=new InputAndDisplay(board);
            while (!inputAndDisplay.isGameOver()){
                inputAndDisplay.runMove();
            }
        }

    }

    public static void clearConsole() {
        System.out.print(Constants.AsciiEscapeCodes.Clear.ENTIRE_SCREEN);
        System.out.flush();
    }

}