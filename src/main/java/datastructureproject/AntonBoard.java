package datastructureproject;

import java.util.ArrayList;
import java.util.Collections;

import chess.engine.GameState;

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

    int[] whiteKingSquare = {7, 4};
    int[] blackKingSquare = {0, 4};
    Boolean[] whiteCastleRights = {true, true};
    Boolean[] blackCastleRights = {true, true};
    ArrayList<Move> moveLog = new ArrayList<Move>();
    

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

        else if (piece == 'N') {
            int[][] dirs = {{2, 1}, {2, -1}, {-2, 1}, {-2, -1}, {1, 2}, {1, -2}, {-1, 2}, {-1, -2},};
            for (int[] dir : dirs) {
                int newCol = col + (dir[0]);
                int newRow = row + (dir[1]);

                if (targetColor(newRow, newCol) == opponent) {
                    moves.add(square+getRank(newCol)+(8-newRow));
                }
            }
            return moves;
        }

        else if (piece == 'B') {
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

        else if (piece == 'R') {
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

        else if (piece == 'Q') {
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

        else if (piece == 'K') {
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

    public void makeMove(String notation) {
        Move move = new Move(notation, this.board);
        

        this.board[move.srow][move.scol] = "--";
        this.board[move.erow][move.ecol] = move.mover;
        if (move.mover.charAt(1) == 'P' && (move.erow == 0 || move.erow == 7)) {
            String queen = move.mover.replace("P", "Q");
            this.board[move.erow][move.ecol] = queen;
        }
        if (move.mover.charAt(1) == 'K') {
            if (move.mover.charAt(0) == 'w') {
                this.whiteKingSquare[0] = move.erow;
                this.whiteKingSquare[1] = move.ecol;
            } else {
                this.blackKingSquare[0] = move.erow;
                this.blackKingSquare[1] = move.ecol;
            }
        }
        this.moveLog.add(move);
    }
    public void undoMove() {
        if (moveLog.isEmpty()) return;

        Move latestMove = this.moveLog.get(this.moveLog.size() - 1);
        this.board[latestMove.srow][latestMove.scol] = latestMove.mover;
        this.board[latestMove.erow][latestMove.ecol] = latestMove.target;
        if (latestMove.mover.charAt(1) == 'K') {
            if (latestMove.mover.charAt(0) == 'w') {
                this.whiteKingSquare[0] = latestMove.srow;
                this.whiteKingSquare[1] = latestMove.scol;
            } else {
                this.blackKingSquare[0] = latestMove.srow;
                this.blackKingSquare[1] = latestMove.scol;
            }
        }
        this.moveLog.remove(this.moveLog.size() - 1);
    }
    private boolean validate(String move, Character side) {  
        
        makeMove(move);
        
        int row = (side == 'b') ? blackKingSquare[0] : whiteKingSquare[0];
        int col = (side == 'b') ? blackKingSquare[1] : whiteKingSquare[1];
        int kingDanger = evaluateSquare(side, row, col);
        
        boolean valid = kingDanger == 0;

        undoMove();
        return valid;
    }

    public int evaluation() {
        int eval = 0;
        for (int i = 0; i < 8; i ++) {
            for (int j = 0; j < 8; j++) {
                 Character color = this.board[i][j].charAt(0);
                 if (color == '-') continue;
                 Character piece = this.board[i][j].charAt(1);
                 int value = 0;
                 boolean opTurn = this.moveLog.get(this.moveLog.size()-1).mover == "" + color;

                 switch (piece) {
                    case 'P':
                        value = 10;
                        break;
                    case 'B':
                        value = 30;
                        int threat = evaluateSquare(color, i, j);
                        if (opTurn && threat != 0) value = 10;
                        if (i >= 2 && i <= 5) value++;
                        if (j >= 2 && j <= 5) value++;
                        break;
                    case 'N':
                        value = 30;
                        threat = evaluateSquare(color, i, j);
                        if (opTurn && threat != 0) value = 10;
                        if (i >= 2 && i <= 5) value++;
                        if (j >= 2 && j <= 5) value++;
                        break;
                    case 'R':
                        value = 50;
                        threat = evaluateSquare(color, i, j);
                        if (opTurn && threat != 0) value = 10;
                        if (i >= 2 && i <= 5) value++;
                        if (j >= 2 && j <= 5) value++;
                        break;
                    case 'Q':
                        value = 90;
                        break;
                    case 'K':
                        value = 1000;
                        if (evaluateSquare(color, i, j) != 0) value = 850;
                        break;
                    default:
                        value = 0;
                        break;
                 }

                 if (color == 'b') value *= -1;
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
        moves.sort((m1, m2) -> Character.compare(m1.charAt(3), m2.charAt(3)));
        if (side == 'w') Collections.reverse(moves);
        return moves;
    }
    private Character targetColor(int row, int col) {
        if (!onBoard(col, row)) return 'o';
        return this.board[row][col].charAt(0);
    }
    private boolean onBoard(int col, int row) {
        return (col > -1 && col < 8 && row > -1 && row < 8);
    }
    private int evaluateSquare(Character side, int row, int col) {
        int checks = 0;
        Character opponent = (side == 'b') ? 'w' : 'b';
        int step = (side == 'b') ? 1 : -1;

        if (onBoard(row+step, col-1) && this.board[row+step][col-1] == (""+opponent+"P")) {
            checks++;
        }
        if (onBoard(row+step, col+1) && this.board[row+step][col+1] == (""+opponent+"P")) {
            checks++;
        }
        int[][] dirs = {{1, 0}, {0, 1}, {-1, 0}, {0, -1}, {1, 1}, {-1, 1}, {-1, -1}, {1, -1}};
            for (int[] dir : dirs) {
                int blocking = 0;
                for (int i = 1; i < 8; i++) {
                    int targetCol = col + (dir[0] * i);
                    int targetRow = row + (dir[1] * i);
                    if (!onBoard(targetCol, targetRow)) break;
                    Character targetPiece = this.board[targetRow][targetCol].charAt(1);
                    Character targetColor = this.board[targetRow][targetCol].charAt(0);
                    if (targetColor == side) {
                        blocking++;
                        if (blocking >= 2) break;
                    }
                    if (targetColor == opponent) {
                        int direction = Math.abs(dir[0] + dir[1]);
                        if (targetPiece == 'N' || targetPiece == 'P') break;
                        if ((targetPiece == 'R' && direction != 1) || (targetPiece == 'B' && direction == 1)) break;
                        if ((targetPiece == 'R') || (targetPiece == 'B') || targetPiece == 'Q') {
                            if (blocking == 0) {
                                checks++;
                                break;
                            }
                        }
                    }
                }
            }
        int[][] ndirs = {{2, 1}, {2, -1}, {-2, 1}, {-2, -1}, {1, 2}, {1, -2}, {-1, 2}, {-1, -2}};
        for (int[] dir : ndirs) {
            int targetCol = col + (dir[1]);
            int targetRow = row + (dir[0]);
            if (!onBoard(targetCol, targetRow)) continue;
            Character targetPiece = this.board[targetRow][targetCol].charAt(1);
            Character targetColor = this.board[targetRow][targetCol].charAt(0);
            if (targetColor == opponent && targetPiece == 'N') {
                checks++;
            }
        }
        return checks;
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

    
}
