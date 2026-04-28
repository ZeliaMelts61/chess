package com.chess_test;


public class Main
{
    public static void main(String[] args) {
        // Scanner input = new Scanner(System.in);
        // Board board = new Board();
        // Tile startTile = board.getTileFromLocation(new Location(0,1));
        // System.out.println("Initial Board: \n");
        // board.printBoardWithGrid();

        // input.nextLine();
        // clearConsole();

        // // board.printTileLocationDict();
        // ChessPiece piece = startTile.getPieceOnTile();
        // List<Location> validMoves=piece.getValidMoves(board.getBoardAsTiles());
        // validMoves.forEach((location) -> {
        //     board.getTileFromLocation(location).enableMoveDisplayMode();
        // });
        // piece.setToActivePiece();
        // board.printBoardWithGrid();

        // input.nextLine();
        // clearConsole();

        // // System.out.println("Valid Moves: " + validMoves);
        // board.movePiece(startTile, board.getTileFromLocation(validMoves.get(0)));
        // piece.setToInactivePiece();
        // validMoves.forEach((location) -> {
        //     board.getTileFromLocation(location).disableMoveDisplayMode();
        // });
        // board.printBoardWithGrid();

        // input.nextLine();
        // clearConsole();


        Board board = new Board();
        InputAndDisplay inputAndDisplay = new InputAndDisplay(board);
        while (!inputAndDisplay.isGameOver()){
            inputAndDisplay.runMove();
        }

    }

    public static void clearConsole() {
        System.out.print(Constants.AsciiEscapeCodes.Clear.ENTIRE_SCREEN);
        System.out.flush();
    }

}