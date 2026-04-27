
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
        System.out.println("Thank You for playing\nGame made entirely by \n\t- Zelia ");
        

        
    }

    public boolean isGameOver(){
        return gameOver;
    }
    public static void clearConsole() {
        System.out.print(Constants.AsciiEscapeCodes.Clear.ENTIRE_SCREEN);
        System.out.flush();
    }
}
