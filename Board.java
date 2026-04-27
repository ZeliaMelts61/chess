

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Board {
    private Tile[][] board;
    private final HashMap<Location, Tile> tileloctionDictonary;
    private final List<ChessPiece> player1CapturedPieces;
    private final List<ChessPiece> player2CapturedPieces;
    private int currentPlayer;
    //creates a new board with standard chess layout
    @SuppressWarnings("OverridableMethodCallInConstructor")
    public Board(){
        currentPlayer=1;
        tileloctionDictonary = new HashMap<>();
        player1CapturedPieces = new ArrayList<>();
        player2CapturedPieces = new ArrayList<>();
        //parseIntoBoard(Constants.Boards.STANDARD.replaceAll("[\\s&&[^\\r\\n]]+", "")); // this removes all whitespace execpt for newlines. then parses into a board
        parseIntoBoard(Constants.Boards.STANDARD.replaceAll("[\\s&&[^\\r\\n]]+", "")); // this removes all whitespace execpt for newlines. then parses into a board
        
    }

    

/* 
    public Board(Tile[][] tiles){
        board = tiles;
    }
*/
    
    public void setPieceAtLocation(int row, int col, ChessPiece piece){
        board[row][col].movePieceToTile(piece);
    }
    public void setPieceAtLocation(Location location, ChessPiece piece){
        board[location.getRow()][location.getCol()].movePieceToTile(piece);
    }
    public void printTileLocationDict(){
        tileloctionDictonary.forEach((key, value) -> {
            System.out.println("Key: " + key + ", Value: " + value);
        });

    }
    public Tile[][] getBoardAsTiles(){
        return board;

    }

    public boolean movePiece(Tile startTile, Tile endTile, InputAndDisplay inputAndDisplay){
        List<Location> validMoves = startTile.getPieceOnTile().getValidMoves(board);
        List<Tile> tileList = new ArrayList<>();
        for (Location rowCol : validMoves) {
            Tile tile = getTileFromLocation(rowCol);
            if (tile!=null){
                tileList.add(tile);
            }
        }
        if (tileList.contains(endTile)){
            ChessPiece piece= startTile.getPieceOnTile();
            //castling
            switch (piece) {
                case King king -> {
                    Location endTileLocation = endTile.getLocation();
                    if (Math.abs(king.getCol() - endTileLocation.getCol())>1){
                        Rook rook;
                        Tile rookStartTile;
                        Tile rookEndTile;
                        if (endTileLocation.getCol()<=3){
                            //castle left
                            rookStartTile=board[king.getRow()][0];
                            rook = (Rook) rookStartTile.getPieceOnTile();
                            rookEndTile = board[king.getRow()][3];
                        } else {
                            //castle Right
                            rookStartTile=board[king.getRow()][7];
                            rook = (Rook) rookStartTile.getPieceOnTile();
                            rookEndTile = board[king.getRow()][5];
                        }
                        rook.setHasMoved(true);
                        rookStartTile.deletePieceFromTile();
                        rookEndTile.movePieceToTile(rook);
                        
                    }
                }
                case Pawn pawn -> {
                    Location endTileLocation = endTile.getLocation();
                    if(endTileLocation.getRow()==0 || endTileLocation.getRow() == 7){
                        piece=inputAndDisplay.promptPromotion(pawn, endTileLocation);
                        if (piece instanceof King) {
                            System.out.println("Heh you found the funny thing");
                            try {
                                Thread.sleep(500);
                            } catch (InterruptedException e) {
                                Thread.currentThread().interrupt();
                            }
                        }
                        startTile.movePieceToTile(piece);
                        
                    }
                }
                default -> {
                }
            }
            startTile.movePieceOffOfTile();
            ChessPiece captured = endTile.movePieceToTile(piece);
            if (piece != null) {
                piece.setHasMoved(true);
            }
            if (captured!=null){
                if (captured instanceof King) {
                    inputAndDisplay.gameOver(getCurrentPlayer());
                }
                if (getCurrentPlayer()==1){
                    player1CapturedPieces.add(captured);
                }else{
                    player2CapturedPieces.add(captured);
                }
            }
            return true;

        }
        return false;
    }

    public Tile getTileFromLocation(Location rowCol){
        // System.out.println("rowCol: " + rowCol);
        
        // System.out.println("tileloctionDictonary get result: " + tileloctionDictonary.getOrDefault(rowCol, null));
        return tileloctionDictonary.getOrDefault(rowCol, null);
    }

    public void parseIntoBoard(String textBoard) {
        emptyBoard();
        String[] rows=textBoard.split("\n");
        for(int i=0; i<8; i++){
            String row=rows[i];
            int player;
            DisplayWrapper display = new DisplayWrapper();
            if (i<4){
               player = 2;
               display.setColor(Constants.GameColors.PLAYER_ONE_COLOR);
            }else{
                player = 1;
                display.setColor(Constants.GameColors.PLAYER_TWO_COLOR);

            }
            for (int j=0; j<8; j++){
                char x = row.charAt(j);
                switch (x) {
                    case Constants.PieceDisplays.ROOK -> setPieceAtLocation(i,j,new Rook(i,j,display, player));
                    case Constants.PieceDisplays.BISHOP -> setPieceAtLocation(i,j,new Bishop(i,j,display, player));
                    case Constants.PieceDisplays.QUEEN -> setPieceAtLocation(i,j,new Queen(i,j,display, player));
                    case Constants.PieceDisplays.KING -> setPieceAtLocation(i,j,new King(i,j,display, player));
                    case Constants.PieceDisplays.PAWN -> setPieceAtLocation(i,j,new Pawn(i,j,display, player));
                    case Constants.PieceDisplays.KNIGHT -> setPieceAtLocation(i,j,new Knight(i,j,display, player));
                }
            }
        }
    }

    public void emptyBoard(){
        Tile[][] emptyBoard=new Tile[8][8];
        for (int i = 0; i<emptyBoard.length; i++){
            for (int j = 0; j<emptyBoard[i].length; j++){
                DisplayWrapper white = new DisplayWrapper(Constants.GameColors.WHITE_SQUARES, "", "", Constants.TileDisplays.UNOCCUPIED);
                DisplayWrapper black = new DisplayWrapper(Constants.GameColors.BLACK_SQUARES, "", "", Constants.TileDisplays.UNOCCUPIED);
                Tile newTile;
                if (((j+(i%2))%2)==0){
                    newTile=new Tile(i, j, white);
                }else{
                    newTile = new Tile(i, j, black);
                }
                emptyBoard[i][j]=newTile;
                tileloctionDictonary.put(new Location(i,j), newTile);
            }
        }
        board=emptyBoard;
    }

    public int getCurrentPlayer(){
        return currentPlayer;
    }

    public void nextPlayer(){
        currentPlayer=(currentPlayer%2)+1;
    }

    public List<ChessPiece> getPlayer2CapturedPieces() {
        return player2CapturedPieces;
    }

    public List<ChessPiece> getPlayer1CapturedPieces() {
        return player1CapturedPieces;
    }

    @Override
    public String toString() {
        if (board == null) {
            return "Board not initialized";
        }
        StringBuilder sb = new StringBuilder();
        for (Tile[] row : board) {
            for (Tile tile : row) {
                sb.append(tile.toString());
                sb.append(' ');
            }
            sb.append("\n");
        }
        return sb.toString();
    }

    public void printBoardWithGrid(){
        final String TopAndBottomAlgebraicNotation = "   a b c d e f g h\n"; 
        if (board == null) {
            System.out.println("Board not initialized");
        } else {
            StringBuilder sb = new StringBuilder();
            
            sb.append(Constants.AsciiEscapeCodes.Colors.ForgroundColors.BLACK);
            sb.append(TopAndBottomAlgebraicNotation);
            sb.append(Constants.AsciiEscapeCodes.Colors.ForgroundColors.RESET_FORGROUND_COLOR);
            for (int i = 0; i < board.length; i++) {
                Tile[] row = board[i];
                sb.append(Constants.AsciiEscapeCodes.Colors.ForgroundColors.BLACK);
                sb.append(8-i);
                sb.append("  ");
                sb.append(Constants.AsciiEscapeCodes.Colors.ForgroundColors.RESET_FORGROUND_COLOR);
                for (Tile tile : row) {
                    sb.append(tile.toString());
                    sb.append(' ');
                }
                sb.append(Constants.AsciiEscapeCodes.Colors.ForgroundColors.BLACK);
                sb.append(" ");
                sb.append(8-i);
                
                sb.append(Constants.AsciiEscapeCodes.Colors.ForgroundColors.RESET_FORGROUND_COLOR);
                sb.append("\n");
            }
            sb.append(Constants.AsciiEscapeCodes.Colors.ForgroundColors.BLACK);
            sb.append(TopAndBottomAlgebraicNotation);
            sb.append(Constants.AsciiEscapeCodes.Colors.ForgroundColors.RESET_FORGROUND_COLOR);
            System.out.print(sb.toString());
            
            
        }
    }
}