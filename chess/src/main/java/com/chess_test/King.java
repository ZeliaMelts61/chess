package com.chess_test;
import java.util.ArrayList;
import java.util.List;

public class King extends ChessPiece {

    public King(int row, int col, DisplayWrapper display, int player) {
        display.setDisplayChar(Constants.PieceDisplays.KING);
        super(row, col, display, player);
    }
    public King(Location location, DisplayWrapper display, int player) {
        display.setDisplayChar(Constants.PieceDisplays.KING);
        super(location, display, player);
    }
    @Override
    public char getDefaultDisplayChar(){
        return Constants.PieceDisplays.KING;
    }

    @Override
    public List<Location> getValidMoves(Tile[][] board) {
        List<Location> moves = new ArrayList<>();
        int[][] directions = {
            {-1, 0}, // up
            {1, 0},  // down
            {0, -1}, // left
            {0, 1}   // right
        };
        // castling
        if (!hasMoved){
            boolean canCastleLeft=true;
            for (int i = 1; i<=3; i++){
                if(board[row][col-i].getPieceOnTile()!=null){
                    canCastleLeft=false;
                }
                if (canCastleLeft){
                    ChessPiece rook = board[row][0].getPieceOnTile();
                    if (rook!=null){
                        if(!rook.getHasMoved()){
                            moves.add(new Location(row, 2));
                        }
                    }
                }
            }
            boolean canCastleRight=true;
            for (int i = 1; i<=3; i++){
                if(board[row][col-i].getPieceOnTile()!=null){
                    canCastleRight=false;
                }
                if (canCastleRight){
                    ChessPiece rook = board[row][7].getPieceOnTile();
                    if (rook!=null){
                        if(!rook.getHasMoved()){
                            moves.add(new Location(row, 6));
                        }
                    }
                }
            }
            

        }
        for (int[] dir : directions) { //loops through the set of directions and finds the row and col in relation to the piece
            int r = row + dir[0];
            int c = col + dir[1];
            if (r >= 0 && r < 8 && c >= 0 && c < 8) { //only if location is on the board
                ChessPiece target = board[r][c].getPieceOnTile();
                if(target==null){
                    moves.add(new Location(r, c));
                } else if (target.getPlayer() != this.getPlayer()){
                    moves.add(new Location(r, c)); // capture
                } 
            }
        }
        return moves;
    }
}