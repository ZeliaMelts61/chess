import java.util.ArrayList;
import java.util.List;

public class Pawn extends ChessPiece {
    //private boolean hasMoved;
    //private int startRow, startCol;

    public Pawn(int row, int col, DisplayWrapper display, int player) {
        display.setDisplayChar(Constants.PieceDisplays.PAWN);
        super(row,col,display,player);
    }
    

    public Pawn(Location location, DisplayWrapper display, int player) {
        display.setDisplayChar(Constants.PieceDisplays.PAWN);

        super(location, display, player);
    }
    @Override
    public char getDefaultDisplayChar(){
        return Constants.PieceDisplays.PAWN;
    }

    @Override
    public List<Location> getValidMoves(Tile[][] board) {
        List<Location> moves = new ArrayList<>();
        int[][] directions;
        if (player == 1) {
            
            if (!hasMoved){
                directions = new int[][] {
                    {-2, 0}, // up 2
                    {-1, 0}, // up
                    {-1, 1}, // up-right
                    {-1, -1} // up-left
                };
            } else {
                directions = new int[][] {
                    {-1, 0}, // up
                    {-1, 1}, // up-right
                    {-1, -1} // up-left
                };
            }
        } else {
            if (!hasMoved){
                directions = new int[][] {
                    {1, 0}, // down
                    {2, 0}, // down
                    {1, 1}, // down-right
                    {1, -1} // down-left
                };
            } else {
                directions = new int[][] {
                    {1, 0}, // down
                    {1, 1}, // down-right
                    {1, -1} // down-left
                };
            }
        }   

        for (int[] dir : directions) { //loops through the set of directions and finds the row and col in relation to the piece
            int r = row + dir[0];
            int c = col + dir[1];
            if (r >= 0 && r < 8 && c >= 0 && c < 8) { //only if location is on the board
                ChessPiece target = board[r][c].getPieceOnTile();
                if(target==null && dir[1]==0){
                    moves.add(new Location(r, c));
                } else if (target !=null && target.getPlayer() != this.getPlayer() && dir[1]!=0){
                    moves.add(new Location(r, c)); // capture
                }
            }
        }
        return moves;
    }
}