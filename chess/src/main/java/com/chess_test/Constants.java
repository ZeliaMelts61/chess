package com.chess_test;
public final class Constants {
    public static final class AsciiEscapeCodes {
        public static final class Colors {
            public static final class ForgroundColors{
                public static final String RESET_FORGROUND_COLOR  = "\u001B[0m";
                public static final String BLACK   = "\u001B[30m";
                public static final String RED     = "\u001B[31m";
                public static final String GREEN   = "\u001B[32m";
                public static final String YELLOW  = "\u001B[33m";
                public static final String BLUE    = "\u001B[34m";
                public static final String MAGENTA = "\u001B[35m";
                public static final String CYAN    = "\u001B[36m";
                public static final String WHITE   = "\u001B[37m";
            } 
            public static final class  BackgroundColors{
                public static final String RESET_BACKGROUND_COLOR = "\u001B[49m";
                public static final String BLACK   = "\u001B[40m";
                public static final String RED     = "\u001B[41m";
                public static final String GREEN   = "\u001B[42m";
                public static final String YELLOW  = "\u001B[43m";
                public static final String BLUE    = "\u001B[44m";
                public static final String MAGENTA = "\u001B[45m";
                public static final String CYAN    = "\u001B[46m";
                public static final String WHITE   = "\u001B[47m";
            }
        }
        public static final class Styles {
            public static final String BOLD  = "\u001B[1m";
            public static final String UNDERLINE  = "\u001B[4m";
            public static final String REVERSED  = "\u001B[7m";
            public static final String ITALICS  = "\u001b[3m";
        }
        public static final class Clear {
            public static final String CURSOR_TO_END = "\u001B[0J";
            public static final String CURSOR_TO_BEGINNING = "\u001B[1J";
            public static final String ENTIRE_SCREEN = "\u001B[2J";
            public static final String CURSOR_TO_END_OF_LINE = "\u001B[0K";
            public static final String CURSOR_TO_START_OF_LINE = "\u001B[1K";
            public static final String ENTIRE_LINE = "\u001B[2K";
        }
        public static final String RESET_ALL = "\u001B[0m";
        
    }
    
    

    public static final class PieceDisplays {
        /*
        public static final char Rook = '?';
        public static final char Bishop = '?';
        public static final char Knight = '?';
        public static final char King = '?';
        public static final char Queen = '?';
        public static final char Pawn = '?';
        */

        public static final char ROOK = 'R';
        public static final char BISHOP = 'B';
        public static final char KNIGHT = 'N';
        public static final char KING = 'K';
        public static final char QUEEN = 'Q';
        public static final char PAWN = 'P';
    }

    public static final class TileDisplays {
        //public static final char Unoccupied = '?';
        public static final char UNOCCUPIED = '=';
        public static final char UNOCCUPIED_CAN_MOVE_TO = 'o';
        public static final char CAPTURE = 'x';
    }
    /*
    ????????
    ????????
    ?????????????????
    ?????????????????
    ?????????????????
    ?????????????????
    ????????
    ????????
    */


    public static final class GameColors {
        public static final String WHITE_SQUARES = AsciiEscapeCodes.Colors.ForgroundColors.YELLOW;
        public static final String BLACK_SQUARES = AsciiEscapeCodes.Colors.ForgroundColors.WHITE;
        public static final String PLAYER_ONE_COLOR = AsciiEscapeCodes.Colors.ForgroundColors.MAGENTA;
        public static final String PLAYER_TWO_COLOR = AsciiEscapeCodes.Colors.ForgroundColors.CYAN;
        public static final String MOVE_HIGHLIGHT_COLOR = AsciiEscapeCodes.Colors.ForgroundColors.GREEN;
        public static final String CAPTURE_HIGHLIGHT_COLOR = AsciiEscapeCodes.Colors.ForgroundColors.RED;
    }

    public static final class Boards {
        /*
        public static final String Standard = """
            ????????
            ????????
            ?????????????????
            ?????????????????
            ?????????????????
            ?????????????????
            ????????
            ????????""";
        */
        public static final String STANDARD = """
            RNBQKBNR
            PPPPPPPP
            ````````
            ````````
            ````````
            ````````
            PPPPPPPP
            RNBQKBNR""";

        public static final String TEST = """
            RNBQKBN`
            PPPPPPP`
            ````````
            ``P`````
            ```K```P
            ````````
            PPPPPPPP
            R```K``R""";

        
    }
}