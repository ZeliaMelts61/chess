public class Tile {
    //private char displayChar;
    
    private final DisplayWrapper display;
    private final DisplayWrapper movementTargetDisplay;
    private ChessPiece pieceOnTile;
    private final int row, col;
    private boolean displayMoveVisuals;
    //private char movementTargetDisplayChar;
    // private String movementTargetDisplayColor;
    public Tile(int row, int col, DisplayWrapper display /*black or white*/, ChessPiece pieceOnTile){
        this.row = row;
        this.col = col;
        //this.displayChar=displayChar;
        this.display=display.copy();
        this.pieceOnTile=pieceOnTile;
        this.displayMoveVisuals=false;
        this.movementTargetDisplay=new DisplayWrapper(Constants.TileDisplays.UNOCCUPIED_CAN_MOVE_TO);
        updateDisplay();
    }
    public Location getLocation(){
        return new Location(row,col);
    }


    public Tile(int row, int col, DisplayWrapper display){
        this(row, col, display, null);
    }

    private void updateDisplay(){
        if (isTileUnoccupied()){
            this.movementTargetDisplay.setDisplayChar(Constants.TileDisplays.UNOCCUPIED_CAN_MOVE_TO);
            this.movementTargetDisplay.setColor(Constants.GameColors.MOVE_HIGHLIGHT_COLOR);
        } else {
            this.movementTargetDisplay.setDisplayChar(Constants.TileDisplays.CAPTURE);
            this.movementTargetDisplay.setColor(Constants.GameColors.CAPTURE_HIGHLIGHT_COLOR);
        }
    }

    private void updatePieceLocation(){
        pieceOnTile.setLocation(row, col);
    }



    public ChessPiece movePieceToTile(ChessPiece piece){
        if (piece != null){
            ChessPiece captured = pieceOnTile;
            pieceOnTile=piece;
            updatePieceLocation();
            return captured;
        }
        return null;
        
    }

    public ChessPiece movePieceOffOfTile(){
        ChessPiece temp = pieceOnTile;
        deletePieceFromTile();
        return temp;
    }

    public void deletePieceFromTile() {
        pieceOnTile=null;
    }

    public ChessPiece getPieceOnTile() {
        return pieceOnTile;
    }

    public ChessPiece replacePieceOnTile(ChessPiece piece){
        ChessPiece temp = pieceOnTile;
        movePieceToTile(piece);
        return temp;
    }

    public boolean isTileUnoccupied(){
        return pieceOnTile==null;
    }

    public boolean isTileOccupied(){
        return !isTileUnoccupied();
    }

    public void setMoveDisplayEnable(boolean display){
        this.displayMoveVisuals=display;
    }
    public void enableMoveDisplayMode(){
        setMoveDisplayEnable(true);
    }
    public void disableMoveDisplayMode(){
        setMoveDisplayEnable(false);
    }

    

    @Override
    public String toString() {
        updateDisplay();
        if(displayMoveVisuals) {
            return movementTargetDisplay.get();           
        } else if (isTileOccupied()){
            return pieceOnTile.toString();
        }
        return display.get();
    }
}