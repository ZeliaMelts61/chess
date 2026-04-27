import java.util.ArrayList;
import java.util.List;

public class Queen extends ChessPiece {

    public Queen(int row, int col, DisplayWrapper display, int player) {
        display.setDisplayChar(Constants.PieceDisplays.QUEEN);
        super(row,col,display,player);
    }
    public Queen(Location location, DisplayWrapper display, int player) {
        display.setDisplayChar(Constants.PieceDisplays.QUEEN);
        super(location,display,player);
    }

    @Override
    public char getDefaultDisplayChar(){
        return Constants.PieceDisplays.QUEEN;
    }

    @Override
    public List<Location> getValidMoves(Tile[][] board) {
        List<Location> moves = new ArrayList<>();
        int[][] directions = {
            {-1, 0}, // up
            {1, 0},  // down
            {0, -1}, // left
            {0, 1},   // right
            {-1, -1}, // up-left
            {-1, 1},  // up-right
            {1, -1}, // down-left
            {1, 1}   // down-right
        };

        for (int[] dir : directions) { //loops through the set of directions and finds the row and col in relation to the piece
            int r = row + dir[0];
            int c = col + dir[1];
            while (r >= 0 && r < 8 && c >= 0 && c < 8) { //only while location is on the board
                ChessPiece target = board[r][c].getPieceOnTile();
                if(target==null){
                    moves.add(new Location(r, c));
                } else if (target.getPlayer() != this.getPlayer()){
                    moves.add(new Location(r, c)); // capture
                } else {
                    break; //one of your own pieces is in the way
                }
                //continues progressing through the straight line
                r += dir[0]; 
                c += dir[1]; 
            }
        }
        return moves;
    }
}