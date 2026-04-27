
import java.util.List;

public abstract class ChessPiece {
    protected int row;
    protected int col;
    protected boolean hasMoved;
    //protected char displayChar;
    // protected String color;
    // protected String BGcolor;
    // protected String style;
    protected int player;
    protected DisplayWrapper display;
    ChessPiece(int row, int col, DisplayWrapper display, int player) {
        this.row=row;
        this.col=col;
        // this.color=color;
        this.display=display.copy();
        this.player=player;
        this.hasMoved=false;
        initializeInactivePiece();
    }

    private void initializeInactivePiece() {
        setToInactivePiece();
    }

    ChessPiece(Location location, DisplayWrapper display, int player) {
        this(location.getRow(), location.getCol(), display,player);
    }
    

    public void setLocation(int row, int col){
        this.row=row;
        this.col=col;
    }

    public void setColor(String color) {
        display.setColor(color);
    }
    public String getColor(){
        return display.getColor();
    }
    public void setStyle(String style){
        display.setStyle(style);
    }
    public String getStyle(){
        return display.getStyle();
    }
    public void setBGcolor(String BGcolor) {
        display.setBGcolor(BGcolor);
    }
    public String getBGcolor() {
        return display.getBGcolor();
    }
    public char getDisplayChar() {
        return display.getDisplayChar();
    }

    public int getPlayer(){
        return player;
    }
    
    public void setToActivePiece(){
        display.setStyle(Constants.AsciiEscapeCodes.Styles.BOLD+Constants.AsciiEscapeCodes.Styles.UNDERLINE);
        display.setBGcolor(Constants.AsciiEscapeCodes.Colors.BackgroundColors.GREEN);
    }
    public void setToInactivePiece(){
        display.setStyle("");
        display.setBGcolor("");
    }
    public void setHasMoved(boolean x){
        hasMoved=x;
    }
    public boolean getHasMoved(){
        return hasMoved;
    }
    public int getRow(){
        return row;
    }
    public int getCol(){
        return col;
    }
    public DisplayWrapper getCopyOfDisplayWrapper(){
        return display.copy();
    }

    public abstract List<Location> getValidMoves(Tile[][] board);
    public abstract char getDefaultDisplayChar();
    

    @Override
    public String toString() {
        return display.get();
    }
    
}

