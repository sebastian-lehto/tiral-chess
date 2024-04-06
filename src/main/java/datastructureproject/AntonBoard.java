package datastructureproject;

import java.util.ArrayList;

import chess.engine.GameState;

public class AntonBoard {
    String[][] board = {
        {"bR","bN","bQ","--","bK","bB","bN","bR"},
        {"bP","--","bP","bP","bP","bP","bP","bP"},
        {"bB","bP","--","--","--","--","--","--"},
        {"--","--","--","--","--","--","--","--"},
        {"--","--","--","wP","--","wB","--","--"},
        {"--","--","--","--","wP","wN","--","--"},
        {"wP","wP","wP","--","--","wP","wP","wP"},
        {"wR","wN","--","wQ","wK","wB","--","wR"}
    };
    ArrayList<int[]> pins = new ArrayList<>();
    ArrayList<int[]> checks = new ArrayList<>();
    int[] whiteKingSquare = {7, 4};
    int[] blackKingSquare = {0, 4};
    Boolean[] whiteCastleRights = {true, true};
    Boolean[] blackCastleRights = {true, true};
    

    public ArrayList<String> getMovesForSquare(int row, int col, Character side) {
        Character color = 'b';
        Character opponent = 'w';
        if (side == 'w') {
            color = 'w';
            opponent = 'b';
        }
        ArrayList<String> moves = new ArrayList<String>();
        Character piece = this.board[row][col].charAt(1);
        Character squareColor = this.board[row][col].charAt(0);
        String square = getRank(col)+(8-row);
        if (piece == '-' || squareColor == opponent) return moves;
        String blockers = "o"+color;

        if (piece == 'P') {
            int step = (color == 'b') ? 1 : -1;
            if (targetColor(row+step, col) == '-') {
                moves.add(square+getRank(col)+(8-row-step));
                if (targetColor(row+step+step, col) == '-' && (row == 1 || row == 6)) {
                    moves.add(square+getRank(col)+(8-row-step-step));
                }
            }
            if (targetColor(row+step, col-1) == opponent) {
                moves.add(square+getRank(col-1)+(8-row-step));
            }
            if ( targetColor(row+step, col+1) == opponent) {
                moves.add(square+getRank(col+1)+(8-row-step));
            }
        
        }

        if (piece == 'R') {
            int[][] dirs = {{1, 0}, {0, 1}, {-1, 0}, {0, -1}};
            for (int[] dir : dirs) {
                for (int i = 1; i < 8; i++) {
                    int newCol = col + (dir[0] * i);
                    int newRow = row + (dir[1] * i);

                    if (blockers.indexOf(targetColor(newRow, newCol)) != -1) {
                        break;
                    }
                    moves.add(square+getRank(newCol)+(8-newRow));
                    if (targetColor(newRow, newCol) == opponent) break;
                }
            }
        }

        if (piece == 'B') {
            int[][] dirs = {{1, 1}, {-1, 1}, {-1, -1}, {1, -1}};
            for (int[] dir : dirs) {
                for (int i = 1; i < 8; i++) {
                    int newCol = col + (dir[0] * i);
                    int newRow = row + (dir[1] * i);

                    if (blockers.indexOf(targetColor(newRow, newCol)) != -1) {
                        break;
                    }
                    moves.add(square+getRank(newCol)+(8-newRow));
                    if (targetColor(newRow, newCol) == opponent) break;
                }
            }
        }

        if (piece == 'N') {
            int[][] dirs = {{2, 1}, {2, -1}, {-2, 1}, {-2, -1}, {1, 2}, {1, -2}, {-1, 2}, {-1, -2},};
            for (int[] dir : dirs) {
                int newCol = col + (dir[0]);
                int newRow = row + (dir[1]);

                if (targetColor(newRow, newCol) == opponent) {
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

                    if (blockers.indexOf(targetColor(newRow, newCol)) != -1) {
                        break;
                    }
                    moves.add(square+getRank(newCol)+(8-newRow));
                    if (targetColor(newRow, newCol) == opponent) break;
                }
            }
        }

        if (piece == 'K') {
            int[][] dirs = {{1, 0}, {0, 1}, {-1, 0}, {0, -1}, {1, 1}, {-1, 1}, {-1, -1}, {1, -1}};

            for (int[] dir : dirs) {
                int newCol = col + (dir[0]);
                int newRow = row + (dir[1]);

                if (blockers.indexOf(targetColor(newRow, newCol)) == -1) {
                    moves.add(square+getRank(newCol)+(8-newRow));
                }
            }
        }
        return moves;
    }

    public void doMove(String move) {
        String start = move.substring(0, 2);
        String end = move.substring(2, 4);
        int scol = getRankNumber(start.charAt(0));
        int srow = 8 - Integer.valueOf(start.substring(1, 2));
        int ecol = getRankNumber(end.charAt(0));
        int erow = 8 - Integer.valueOf(end.substring(1, 2));
        String mover = this.board[srow][scol];
        this.board[srow][scol] = "--";
        this.board[erow][ecol] = mover;
        if (mover.charAt(1) == 'P' && (erow == 0 || erow == 7)) {
            String queen = mover.replace("P", "Q");
            this.board[erow][ecol] = queen;
        }
        if (mover.charAt(1) == 'K') {
            if (mover.charAt(0) == 'w') {
                this.whiteKingSquare[0] = erow;
                this.whiteKingSquare[1] = ecol;
            } else {
                this.blackKingSquare[0] = erow;
                this.blackKingSquare[1] = ecol;
            }
        }
    }

    private boolean validate(String move, Character side) {
        String start = move.substring(0, 2);
        String end = move.substring(2, 4);
        int scol = getRankNumber(start.charAt(0));
        int srow = 8 - Integer.valueOf(start.substring(1, 2));
        int ecol = getRankNumber(end.charAt(0));
        int erow = 8 - Integer.valueOf(end.substring(1, 2));
        String mover = this.board[srow][scol];
        String target = this.board[erow][ecol];
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
        updateChecksAndPins(side);
        boolean valid = this.checks.size() == 0;
    
        this.board[srow][scol] = mover;
        this.board[erow][ecol] = target;
        if (mover.charAt(1) == 'K') {
            if (mover.charAt(0) == 'w') {
                this.whiteKingSquare[0] = srow;
                this.whiteKingSquare[1] = scol;
            } else {
                this.blackKingSquare[0] = srow;
                this.blackKingSquare[1] = scol;
            }
        }
        updateChecksAndPins(side);
        return valid;

    }

    public int evaluate() {
        int eval = 0;
        for (int i = 0; i < 8; i ++) {
            for (int j = 0; j < 8; j++) {
                 Character colour = this.board[i][j].charAt(0);
                 Character piece = this.board[i][j].charAt(1);
                 int value = 0;

                 switch (piece) {
                    case 'P':
                        value = 1;
                        break;
                    case 'B':
                        value = 3;
                        break;
                    case 'N':
                        value = 3;
                        break;
                    case 'R':
                        value = 5;
                        break;
                    case 'Q':
                        value = 9;
                        break;
                    case 'K':
                        value = 10000;
                        break;
                    default:
                        value = 0;
                        break;
                 }

                 if (colour == 'b') value *= -1;
                 eval += value;
            }
        }
        return eval;
    }

    public ArrayList<String> getMoves(Character side) {
        ArrayList<String> moves = new ArrayList<String>();
        for (int i = 0; i < 8; i ++) {
            for (int j = 0; j < 8; j++) {
                 moves.addAll(getMovesForSquare(i, j, side));
            }
        }
        moves.removeIf(x -> !validate(x, side));
        return moves;
    }
    private Character targetColor(int row, int col) {
        if (!onBoard(col, row)) return 'o';
        return this.board[row][col].charAt(0);
    }
    private boolean onBoard(int col, int row) {
        return (col > -1 && col < 8 && row > -1 && row < 8);
    }
    private void updateChecksAndPins(Character side) {
        this.pins = new ArrayList<>();
        this.checks = new ArrayList<>();
        Character color = 'b';
        Character opponent = 'w';
        int row = blackKingSquare[0];
        int col = blackKingSquare[1];

        if (side == 'w') {
            color = 'w';
            opponent = 'b';
            row = whiteKingSquare[0];
            col = whiteKingSquare[1];
        }

        int step = (color == 'b') ? 1 : -1;
        if (onBoard(row+step, col-1) && this.board[row+step][col-1] == (""+opponent+"P")) {
            int[] check = {row+step, col-1};
            this.checks.add(check);
        }
        if (onBoard(row+step, col+1) && this.board[row+step][col+1] == (""+opponent+"P")) {
            int[] check = {row+step, col+1};
            this.checks.add(check);
        }
        int[][] dirs = {{1, 0}, {0, 1}, {-1, 0}, {0, -1}, {1, 1}, {-1, 1}, {-1, -1}, {1, -1}};
            for (int[] dir : dirs) {
                int blocking = 0;
                int[] pin = {-1, -1};
                for (int i = 1; i < 8; i++) {
                    int newCol = col + (dir[0] * i);
                    int newRow = row + (dir[1] * i);
                    if (!onBoard(newCol, newRow)) break;
                    Character targetPiece = this.board[newRow][newCol].charAt(1);
                    Character targetColor = this.board[newRow][newCol].charAt(0);
                    if (targetColor == color) {
                        blocking++;
                        if (blocking >= 2) break;
                        pin[0] = newRow;
                        pin[1] = newCol;
                    }
                    if (targetColor == opponent) {
                        int direction = Math.abs(dir[0] + dir[1]);
                        if ((targetPiece == 'R' && direction != 1) || (targetPiece == 'B' && direction == 1) || targetPiece == 'N' || targetPiece == 'P') {
                            break;
                        }
                        if ((targetPiece == 'R' && direction == 1) || (targetPiece == 'B' && direction != 1) || targetPiece == 'Q') {
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
        int[][] ndirs = {{2, 1}, {2, -1}, {-2, 1}, {-2, -1}, {1, 2}, {1, -2}, {-1, 2}, {-1, -2}};
        for (int[] dir : ndirs) {
            int newCol = col + (dir[1]);
            int newRow = row + (dir[0]);
            if (!onBoard(newCol, newRow)) continue;
            Character targetPiece = this.board[newRow][newCol].charAt(1);
            Character targetColor = this.board[newRow][newCol].charAt(0);
            if (onBoard(newCol, newRow) && targetColor == opponent && targetPiece == 'N') {
                int[] check = {newRow, newCol};
                this.checks.add(check);
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

    private int getRankNumber(Character col) {
        switch (col) {
            case 'a':
                return 0;
            case 'b':
                return 1;
            case 'c':
                return 2;
            case 'd':
                return 3;
            case 'e':
                return 4;
            case 'f':
                return 5;
            case 'g':
                return 6;
            case 'h':
                return 7;
        
            default:
                return -1;
        }
    }
}
