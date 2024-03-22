package datastructureproject;

import java.util.ArrayList;

public class AntonBoard {
    String[][] board = {
        {"bR","bN","bB","bQ","bK","bB","bN","bR"},
        {"bP","bP","bP","bP","bP","bP","bP","bP"},
        {"--","--","--","--","--","--","--","--"},
        {"--","--","--","--","--","--","--","--"},
        {"--","--","--","--","--","--","--","--"},
        {"--","--","--","--","--","--","--","--"},
        {"wP","wP","wP","wP","wP","wP","wP","wP"},
        {"wR","wN","wB","wQ","wK","wB","wN","wR"}
    };
    ArrayList<int[]> pins = new ArrayList<>();
    ArrayList<int[]> checks = new ArrayList<>();
    int[] whiteKingSquare = {7, 4};
    int[] blackKingSquare = {0, 4};
    Boolean[] whiteCastleRights = {true, true};
    Boolean[] blackCastleRights = {true, true};

    public ArrayList<String> getMovesForSquare(int row, int col) {
        ArrayList<String> moves = new ArrayList<String>();
        Character piece = this.board[row][col].charAt(1);
        Character color = this.board[row][col].charAt(0);
        String square = getRank(col)+(8-row);
        if (piece == '-' || color == 'w') return moves;

        if (piece == 'P') {
            int step = (color == 'b') ? 1 : -1;
            if (onBoard(col, row+step) && this.board[row+step][col].charAt(0) == '-') {
                moves.add(square+getRank(col)+(8-row+step));
                if (onBoard(col, row+step+step) && this.board[row+step+step][col].charAt(0) == '-') {
                    moves.add(square+getRank(col)+(8-row+step+step));
                }
            }
            if (onBoard(col, row+step)) {
                if (col-1 > -1 && this.board[row+step][col-1].charAt(0) != '-') {
                    if (this.board[row+step][col-1].charAt(0) != color) {
                        moves.add(square+getRank(col-1)+(8-row+step));
                    }
                }
                if (col+1 < 8 && this.board[row+step][col+1].charAt(0) != '-') {
                    if (this.board[row+step][col+1].charAt(0) != color) {
                        moves.add(square+getRank(col+1)+(8-row+step));
                    }
                }
            }
        }

        if (piece == 'R') {
            int[][] dirs = {{1, 0}, {0, 1}, {-1, 0}, {0, -1}};
            for (int[] dir : dirs) {
                for (int i = 1; i < 8; i++) {
                    int newCol = col + (dir[0] * i);
                    int newRow = row + (dir[1] * i);

                    if (!onBoard(newCol, newRow) || this.board[newRow][newCol].charAt(0) == color) {
                        break;
                    }
                    moves.add(square+getRank(newCol)+(8-newRow));

                    if (this.board[newRow][newCol].charAt(0) != '-') break;
                }
            }
        }

        if (piece == 'B') {
            int[][] dirs = {{1, 1}, {-1, 1}, {-1, -1}, {1, -1}};
            for (int[] dir : dirs) {
                for (int i = 1; i < 8; i++) {
                    int newCol = col + (dir[0] * i);
                    int newRow = row + (dir[1] * i);

                    if (!onBoard(newCol, newRow) || this.board[newRow][newCol].charAt(0) == color) {
                        break;
                    }
                    moves.add(square+getRank(newCol)+(8-newRow));

                    if (this.board[newRow][newCol].charAt(0) != '-') break;
                }
            }
        }

        if (piece == 'N') {
            int[][] dirs = {{2, 1}, {2, -1}, {-2, 1}, {-2, -1}, {1, 2}, {1, -2}, {-1, 2}, {-1, -2},};
            for (int[] dir : dirs) {
                int newCol = col + (dir[0]);
                int newRow = row + (dir[1]);

                if (onBoard(newCol, newRow) && this.board[newRow][newCol].charAt(0) != color) {
                    moves.add(square+getRank(newCol)+(8-newRow));
                }
            }
        }

        if (piece == 'Q') {
            int[][] dirs = {{1, 0}, {0, 1}, {-1, 0}, {0, -1}, {1, 1}, {-1, 1}, {-1, -1}, {1, -1}};
            for (int[] dir : dirs) {
                for (int i = 1; i < 8; i++) {
                    int newCol = col + (dir[0] * i);
                    int newRow = row + (dir[1] * i);

                    if (!onBoard(newCol, newRow) || this.board[newRow][newCol].charAt(0) == color) {
                        break;
                    }
                    moves.add(square+getRank(newCol)+(8-newRow));

                    if (this.board[newRow][newCol].charAt(0) != '-') break;
                }
            }
        }

        if (piece == 'K') {
            int[][] dirs = {{1, 0}, {0, 1}, {-1, 0}, {0, -1}, {1, 1}, {-1, 1}, {-1, -1}, {1, -1}};

            for (int[] dir : dirs) {
                int newCol = col + (dir[0]);
                int newRow = row + (dir[1]);

                if (!onBoard(newCol, newRow) || this.board[newRow][newCol].charAt(0) == color) {
                    break;
                }
                moves.add(square+getRank(newCol)+(8-newRow));
            }
        }


        return moves;
    }

    public void doMove(String start, String end) {
        int scol = getRankNumber(start.substring(0, 1));
        int srow = 8 - Integer.valueOf(start.substring(1, 2));
        int ecol = getRankNumber(end.substring(0, 1));
        int erow = 8 - Integer.valueOf(end.substring(1, 2));
        String mover = this.board[srow][scol];
        this.board[srow][scol] = "--";
        this.board[erow][ecol] = mover;

        if (mover.charAt(1) == 'K') {
            if (mover.charAt(0) == 'w') {
                this.whiteKingSquare[0] = erow;
                this.whiteKingSquare[1] = ecol;
            } else {
                this.blackKingSquare[0] = erow;
                this.blackKingSquare[1] = ecol;
            }
        }
        updateChecksAndPins();

    }

    public ArrayList<String> getMoves() {
        
        ArrayList<String> moves = new ArrayList<String>();
        for (int i = 0; i < 8; i ++) {
            for (int j = 0; j < 8; j++) {
                 moves.addAll(getMovesForSquare(i, j));
            }
        }
        return moves;
    }

    private boolean onBoard(int col, int row) {
        return (col > -1 && col < 8 && row > -1 && row < 8);
    }
    private void updateChecksAndPins() {
        int row = blackKingSquare[0];
        int col = blackKingSquare[1];

        int[][] dirs = {{1, 0}, {0, 1}, {-1, 0}, {0, -1}, {1, 1}, {-1, 1}, {-1, -1}, {1, -1}};
            for (int[] dir : dirs) {
                int blocking = 0;
                int[] pin = {-1, -1};
                for (int i = 1; i < 8; i++) {
                    int newCol = col + (dir[0] * i);
                    int newRow = row + (dir[1] * i);
                    if (!onBoard(newCol, newRow)) break;
                    Character piece = this.board[newRow][newCol].charAt(1);
                    Character color = this.board[newRow][newCol].charAt(0);
                    if (color == 'b') {
                        blocking++;
                        if (blocking >= 2) break;
                        pin[0] = newRow;
                        pin[1] = newCol;
                    }
                    if (color == 'w') {
                        int direction = Math.abs(dir[0] + dir[1]);
                        if (piece == 'R' && direction == 1 || piece == 'B' && direction != 1 || piece == 'Q') {
                            if (blocking == 0) {
                                int[] check = {newRow, newCol};
                                this.checks.add(check);
                                break;
                            } else {
                                this.pins.add(pin);
                                break;
                            }
                        }
                    }
                }
            }
        int[][] ndirs = {{2, 1}, {2, -1}, {-2, 1}, {-2, -1}, {1, 2}, {1, -2}, {-1, 2}, {-1, -2},};
        for (int[] dir : ndirs) {
            int newCol = col + (dir[0]);
            int newRow = row + (dir[1]);
            if (!onBoard(newCol, newRow)) break;
            Character piece = this.board[newRow][newCol].charAt(1);
            Character color = this.board[newRow][newCol].charAt(0);
            if (onBoard(newCol, newRow) && color == 'w' && piece == 'N') {
                int[] check = {newRow, newCol};
                this.checks.add(check);
                System.out.println("KNIGHT CHECK!!!!!!!!!!!!");
                break;
            }
        }

    }
    public void printBoard() {
        for (String[] line : this.board) {
            for (String sq : line) {
                System.out.print(sq);
            }
            System.out.println();
        }
        System.out.println("________________________");
    }
    private String getRank(int col) {
        switch (col) {
            case 0:
                return "a";
            case 1:
                return "b";
            case 2:
                return "c";
            case 3:
                return "d";
            case 4:
                return "e";
            case 5:
                return "f";
            case 6:
                return "g";
            case 7:
                return "h";
        
            default:
                return "-1";
        }
    }

    private int getRankNumber(String col) {
        switch (col) {
            case "a":
                return 0;
            case "b":
                return 1;
            case "c":
                return 2;
            case "d":
                return 3;
            case "e":
                return 4;
            case "f":
                return 5;
            case "g":
                return 6;
            case "h":
                return 7;
        
            default:
                return -1;
        }
    }
}
