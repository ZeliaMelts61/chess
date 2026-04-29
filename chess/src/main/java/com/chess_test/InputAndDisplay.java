package com.chess_test;
import java.util.List;
import java.util.Scanner;

public class InputAndDisplay {
    private static final Scanner input = new Scanner(System.in);
    private final Board board;
    private boolean gameOver;

    public InputAndDisplay(Board board) {
        this.board=board;
    }
    
    public void printBoard(){
        board.printBoardWithGrid();
    }
//piece
    // private boolean moveSucceded(Tile startTile, Tile endTile){
    //     return board.movePiece(startTile, endTile);
    // }

    private Tile promptTile(){
        String line; // check for a chess notation (eg. a2)
        while (true) {
            System.out.println("Enter a location: ");
            line = input.nextLine();
            line = line.strip().toLowerCase();
            if (line.length() == 2) {
                //System.out.println("length is 2");
                char file = line.charAt(0);
                char rank = line.charAt(1);
                if(Character.isDigit(rank)&&"abcdefgh".contains(String.valueOf(file))){
                    //System.out.println("rank and file are valid");
                    int row = 8-((rank - '0')); 
                    int col = ("abcdefgh".indexOf(file));
                    Location location = new Location(row,col);
                    //System.out.println("Location: " + location);
                    Tile tile = board.getTileFromLocation(location);
                    System.out.println("tile: " + tile);
                    if (tile!=null){
                        return tile;
                    }
                }
            }
            System.out.println("Invalid Location, use Rank-file notation (eg, a1, c8, d3)");
        }
    }

    public void runMove(){
        Tile startTile;
        ChessPiece piece;
        List<Location> validMoves;
        clearConsole();
        board.printBoardWithGrid();
        while (true) { 
            while (true){

                System.out.println("Player " + board.getCurrentPlayer() + ":\nWhat Piece do you want to move?");
                startTile=promptTile();
                piece = startTile.getPieceOnTile();
                if (piece!=null){
                    if (piece.getPlayer()!=board.getCurrentPlayer()){
                        System.out.println("That piece is not yours");
                        continue;
                    }
                    break;
                } else {
                    System.out.println("There is no piece on that tile.");
                }
            }
            if (piece != null) {
                validMoves = piece.getValidMoves(board.getBoardAsTiles());
                if (validMoves.isEmpty()){
                    System.out.println("This piece has no valid moves");
                    //continue;
                } else { 
                    break;
                }
            }
        }

        validMoves.forEach((location) -> {
            board.getTileFromLocation(location).enableMoveDisplayMode();
        });
        if (piece!=null){
            piece.setToActivePiece();
        }
        clearConsole();
        board.printBoardWithGrid();
        
        while (true) { 
            System.out.println("Where do you want to move this piece?");
            Tile endTile=promptTile();
            if (!validMoves.contains(endTile.getLocation())){
                System.out.println("That is not a valid move");
                
            } else {
                validMoves.forEach((location) -> {
                    board.getTileFromLocation(location).disableMoveDisplayMode();
                });
                if (piece!=null){
                    piece.setToInactivePiece();
                }   
                board.movePiece(startTile, endTile, this);
                board.nextPlayer();
                break;
            }

        }
        
    }

    @SuppressWarnings({"CallToThreadSleepInLoop"})
    public ChessPiece promptPromotion(Pawn promotingPawn,Location promotionLocation){
        System.out.println("Player " + board.getCurrentPlayer() + ":");
        while (true){
            
            System.out.println("What would you like to promote this pawn to?");
            String line = input.nextLine().toUpperCase();
            if (line.length() == 1){
                //System.out.println("valid length");
                DisplayWrapper display = promotingPawn.getCopyOfDisplayWrapper();
                int player = promotingPawn.getPlayer();
                switch (line.charAt(0)) {
                    case Constants.PieceDisplays.ROOK -> {
                        return new Rook(promotionLocation,display, player);
                    }
                    case Constants.PieceDisplays.BISHOP -> {
                        return new Bishop(promotionLocation,display, player);
                    }
                    case Constants.PieceDisplays.QUEEN -> {
                        return new Queen(promotionLocation,display, player);
                    }
                    case Constants.PieceDisplays.KING -> {
                        return new King(promotionLocation,display, player);
                    }
                    case Constants.PieceDisplays.KNIGHT -> {
                        return new Knight(promotionLocation,display, player);
                    }
                }
            }
            System.out.println("That is not a valid choice. Use notation like the board such as Q for queen");
        } 
    }

    public void gameOver(int winner){
        clearConsole();
        System.out.println("Player " + winner + " WINS!!!");
        gameOver=true;
        
        List<ChessPiece> p1CapturedList = board.getPlayer1CapturedPieces();
        List<ChessPiece> p2CapturedList = board.getPlayer2CapturedPieces();
        System.out.println("Player 1 captured " +  p1CapturedList.size() + " piece(s): ");
        p1CapturedList.forEach((piece) -> {
            System.out.print(piece+" ");
        });

        System.out.println("\nPlayer 2 captured " +  p2CapturedList.size() + " piece(s): ");
        p2CapturedList.forEach((piece) -> {
            System.out.print(piece+" ");
        });

        System.out.println("The winning board is: ");
        board.printBoardWithGrid();
        System.out.println("Thank You for playing");
        credits();
        input.nextLine();
        

        
    }

    public boolean isGameOver(){
        return gameOver;
    }
    public boolean promptPlayAgain(){
        while (true) { 
            System.out.println("Would you like to play again? (y/n)");
            String line = input.nextLine().toLowerCase().strip();
            if(line.equals("yes") || line.equals("y")){
                return true;
            } else if (line.equals("no") || line.equals("n")){
                return false;
            }
        }
        
    }

    public static void credits(){
        System.out.println(Constants.AsciiEscapeCodes.Styles.ITALICS + "This game was made by Zelia under an MIT License");
        System.out.println(Constants.AsciiEscapeCodes.Styles.ITALICS + "My contact information is Email: ZeliaMelts@gmail.com\nGithub: ZeliaMelts61\nSource Code Repo: https://github.com/ZeliaMelts61/chess" + Constants.AsciiEscapeCodes.RESET_ALL);
    }
    public static void tutorial(){
        System.out.println("Would you like an explanation of how the game works? (y/n)");
        String line = input.nextLine().strip();
        if(!(line.equals("n") || line.equals("no"))){
            Board exampleBoard = new Board();
            System.out.println("UI: ");
            System.out.println(Constants.PieceDisplays.PAWN + " = Pawn");
            System.out.println(Constants.PieceDisplays.ROOK + " = Rook");
            System.out.println(Constants.PieceDisplays.KNIGHT + " = Knight");
            System.out.println(Constants.PieceDisplays.QUEEN + " = Queen");
            System.out.println(Constants.PieceDisplays.KING + " = King");
            input.nextLine();
            System.out.println(Constants.TileDisplays.UNOCCUPIED + " = Unoccupied tile");
            System.out.println(Constants.TileDisplays.UNOCCUPIED_CAN_MOVE_TO + " = Unoccupied tile that the current peice can move to");
            System.out.println(Constants.TileDisplays.CAPTURE + " = Tile with a peice that you can capture");
            input.nextLine();
            System.err.println(Constants.GameColors.PLAYER_ONE_COLOR + "Player 1 " + Constants.AsciiEscapeCodes.RESET_ALL + "is the Player that starts at the bottom of the board");
            System.err.println(Constants.GameColors.PLAYER_TWO_COLOR + "Player 2 " + Constants.AsciiEscapeCodes.RESET_ALL + "is the Player that starts at the top of the board");
            input.nextLine();
            System.out.println("Movement: ");
            System.out.println("Moving is easy, there are 3 steps to moving a piece:");
            exampleBoard.printBoardWithGrid();
            System.out.println("Step 1: When prompted to select a piece Enter the loctaion of the piece you want to move");
            System.out.println("For example to move player 1's A pawn, you would enter \"a2\"");
            input.nextLine();
            exampleBoard.getBoardAsTiles()[5][0].enableMoveDisplayMode();
            exampleBoard.getBoardAsTiles()[4][0].enableMoveDisplayMode();
            exampleBoard.getBoardAsTiles()[6][0].getPieceOnTile().setToActivePiece();
            System.out.println("Step 2: once a piece is selected you will be able to see its avalible moves");
            exampleBoard.printBoardWithGrid();
            input.nextLine();
            System.out.println("Step 3: Now you will select the square that you want the move the piece to");
            System.out.println("For example to move player 1's A pawn forward two spaces you would enter \"a4\"");
            exampleBoard.movePiece(exampleBoard.getBoardAsTiles()[6][0], exampleBoard.getBoardAsTiles()[4][0]);
            exampleBoard.getBoardAsTiles()[5][0].disableMoveDisplayMode();
            exampleBoard.getBoardAsTiles()[4][0].disableMoveDisplayMode();
            exampleBoard.printBoardWithGrid();
            input.nextLine();
            System.out.println("Your piece has now been moved!");
            input.nextLine();
            System.out.println("My chess game is special");
            System.out.println("In my chess game you cannont do En-passant. \nI decided to code it like that because I thought it was funny");
            System.out.println("There is also another secret change from chess that I am leaving it up to you to find");
            System.out.println("Have fun and enjoy the game");
            credits();
            
        }
        input.nextLine();
        clearConsole();
        

        
    }
    public static void clearConsole() {
        System.out.print(Constants.AsciiEscapeCodes.Clear.ENTIRE_SCREEN);
        System.out.flush();
    }
}
