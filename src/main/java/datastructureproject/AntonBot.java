package datastructureproject;

import java.util.ArrayList;

import chess.bot.ChessBot;
import chess.engine.GameState;

public class AntonBot implements ChessBot { 
    public AntonBoard board;
    public int depth;
    public Character side;
    public Character opponent;
    public String move;
    public boolean gameOver;
    
    public AntonBot() {

        this.board = new AntonBoard();
        this.depth = 5;
        this.side = 'b';
        this.opponent = 'w';
        this.move = "";
        gameOver = false;

    }

    /* The method print prints out the board */
    public void print() {
        this.board.printBoard();
    }

    /* The method nextMove either parses the latest move or sets the bot to play the first move as white.
     * Then it uses the minimax function to find the best move.
     * Then it checks if the game is over
     * 
     * @param gs        the GameState object that contains all moves
     * 
     * @return          the best move
     */
    @Override
    public String nextMove(GameState gs) {

        if (gs.getMoveCount() == 0) {
            this.side = 'w';
            this.opponent = 'b';
        } else {
            this.board.makeMove(gs.getLatestMove());
        }

        ArrayList<String> moves = this.board.getMoves(side);
        int eval = minimax(this.depth, -100000, 100000, true, moves);
        this.board.makeMove(this.move);
        if (eval == 10005 || eval == -10005) this.gameOver = true;
        return this.move;
    }

    /* The minimax function */
    public int minimax(int currentDepth, int alpha, int beta, boolean maxing, ArrayList<String> moves) {
        if (currentDepth == 0) {
            if (side == 'b') return this.board.evaluation() * -1;
            return this.board.evaluation();
        }
        
        if (maxing) {
            int maxEval = -100000;
            for (String move : moves) {
                
                this.board.makeMove(move);
                ArrayList<String> newMoves = this.board.getMoves(opponent);

                if (newMoves.isEmpty()) {
                    if (currentDepth == this.depth) {
                        this.move = move;
                    }
                    board.undoMove();
                    maxEval = 10000 + currentDepth;
                    return 10000 + currentDepth;
                }
                
                int eval = minimax(currentDepth-1, alpha, beta, false, newMoves);
                
                board.undoMove();
                
                if (eval > maxEval) {
                    if (currentDepth == this.depth) {
                        this.move = move;
                    }
                    maxEval = eval;
                    
                } 
                alpha = Math.max(alpha, eval);
                if (beta <= alpha) break;
            }
            
            return maxEval;

        } else {
            int minEval = 100000;
            for (String move : moves) {
                this.board.makeMove(move);
                ArrayList<String> newMoves = this.board.getMoves(side);
                
                if (newMoves.isEmpty()) {
                    board.undoMove();
                    minEval = -10000 - currentDepth;
                    return -10000 - currentDepth;
                }
                
                int eval = minimax(currentDepth-1, alpha, beta, true, newMoves);
                
                board.undoMove();

                minEval = Math.min(minEval, eval);
                beta = Math.min(beta, eval);
                if (beta <= alpha) break;
            }

            return minEval;
        }
    }

}