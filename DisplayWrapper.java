public class DisplayWrapper {
    private String color, BGcolor, style;
    private char displayChar;
    public DisplayWrapper(String color, String BGcolor, String style, char displayChar) {
        this.color=color;
        this.BGcolor=BGcolor;
        this.style=style;
        this.displayChar=displayChar;
    }
    public DisplayWrapper(char displayChar) {
        this.color="";
        this.BGcolor="";
        this.style="";
        this.displayChar=displayChar;
    }
    public DisplayWrapper() {
        this.color="";
        this.BGcolor="";
        this.style="";
        this.displayChar=' ';
    }
    public void setFormatting(String color, String BGcolor, String style){
        this.color=color;
        this.BGcolor=BGcolor;
        this.style=style;
    }
    public void setAll(String color, String BGcolor, String style, char displayChar){
        this.color=color;
        this.BGcolor=BGcolor;
        this.style=style;
        this.displayChar=displayChar;
    }

    public void setBGcolor(String BGcolor) {
        this.BGcolor = BGcolor;
    }

    public String getBGcolor() {
        return BGcolor;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getColor() {
        return color;
    }

    public void setStyle(String style) {
        this.style = style;
    }

    public String getStyle() {
        return style;
    }

    public void setDisplayChar(char displayChar) {
        this.displayChar = displayChar;
    }

    public char getDisplayChar() {
        return displayChar;
    }
    
    public void print(){
        System.out.print(color+BGcolor+style+displayChar+Constants.AsciiEscapeCodes.RESET_ALL);
    }
    public String get(){
        return color+BGcolor+style+displayChar+Constants.AsciiEscapeCodes.RESET_ALL;
    }

    @Override
    public String toString() {
        return get();
    }
    
    public DisplayWrapper copy(){
        return new DisplayWrapper(color,BGcolor,style,displayChar);
    }
    
}
