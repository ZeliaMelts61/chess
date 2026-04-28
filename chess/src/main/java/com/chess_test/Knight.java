package com.chess_test;
import java.util.ArrayList;
import java.util.List;

public class Knight extends ChessPiece {

    public Knight(int row, int col, DisplayWrapper display, int player) {
        display.setDisplayChar(Constants.PieceDisplays.KNIGHT);
        
        super(row,col,display,player);

    }

    public Knight(Location location, DisplayWrapper display, int player) {
        display.setDisplayChar(Constants.PieceDisplays.KNIGHT);

        super(location,display,player);
    }
    @Override
    public char getDefaultDisplayChar(){
        return Constants.PieceDisplays.KNIGHT;
    }

    @Override
    public List<Location> getValidMoves(Tile[][] board) {
        List<Location> moves = new ArrayList<>();
        int[][] directions = {
            {-2, -1}, {-2, 1},
            {-1, -2}, {-1, 2},
            {1, -2},  {1, 2},
            {2, -1},  {2, 1}
        };

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