package datastructureproject;

public class Move {
    public String start;
        public String end;
        public int scol;
        public int srow;
        public int ecol;
        public int erow;
        public String mover;
        public String target;

    public Move(String move, String[][] board) {
        start = move.substring(0, 2);
        end = move.substring(2, 4);
        scol = ("abcdefgh").indexOf(start.charAt(0));
        srow = 8 - Integer.valueOf(start.substring(1, 2));
        ecol = ("abcdefgh").indexOf(end.charAt(0));
        erow = 8 - Integer.valueOf(end.substring(1, 2));
        mover = board[srow][scol];
        target = board[erow][ecol];

    }
}
