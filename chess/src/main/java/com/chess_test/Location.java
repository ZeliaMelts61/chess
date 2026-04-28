package com.chess_test;
public class Location {
    private final int row;
    private final int col;
    public Location(int row, int col){
        this.row=row;
        this.col=col;
    }
    public int getCol() {
        return col;
    }
    public int getRow() {
        return row;
    }
    public int[] getLocationAsArray(){
        return new int[]{row,col};
    }
    
    @Override
    public String toString(){
        return "Location: ["+row+","+col+"]";
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof Location location) {
            return location.getCol()==this.col && location.getRow()==this.row;
        }
        return false;
    }

    @Override
    public int hashCode() {
        int result = Integer.hashCode(row);
        result = 31 * result + Integer.hashCode(col);
        return result;
    }
}
