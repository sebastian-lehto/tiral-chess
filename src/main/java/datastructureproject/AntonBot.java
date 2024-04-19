package datastructureproject;

import chess.bot.ChessBot;
import chess.engine.GameState;

public class AntonBot implements ChessBot { 
    private AntonBoard board;
    private int depth;
    private Character side;
    private Character opponent;
    private String move;
    public boolean gameOver;
    
    public AntonBot() {
        this.board = new AntonBoard();
        this.depth = 5;
        this.side = 'b';
        this.opponent = 'w';
        this.move = "TBD";
        gameOver = false;
    }
    public void print() {
        this.board.printBoard();
    }
    @Override
    public String nextMove(GameState gs) {
        if (gs.getMoveCount() == 0) {
            this.side = 'w';
            this.opponent = 'b';
        } else {
            this.board.makeMove(gs.getLatestMove());
        }
        this.move = "a1a1";
        int eval = minimax(gs, this.depth, -100000, 100000, true);
        this.board.makeMove(this.move);
        if (this.move == "a1a1") this.gameOver = true;
        return this.move;
    }

    public int minimax(GameState gs, int currentDepth, int alpha, int beta, boolean maxing) {
        
        if (currentDepth == 0 || this.board.getMoves(side).isEmpty()) {
            if (side == 'b') return this.board.evaluation() * -1;
            return this.board.evaluation();
        }
        String bestMove = "";
        
        if (maxing) {
            int maxEval = -100000;
            for (String move : this.board.getMoves(this.side)) {
                gs.moves.add(move);
                board.makeMove(move);

                int eval = minimax(gs, currentDepth-1, alpha, beta, false);
                gs.moves.remove(gs.moves.size() - 1);
                board.undoMove();
                if (eval > maxEval) {
                    if (currentDepth == this.depth) {
                        bestMove = move; 
                        this.move = bestMove;
                    }
                    maxEval = eval;
                    
                    
                } 
                alpha = Math.max(alpha, eval);
                if (beta <= alpha) break;
            }
            
            return maxEval;
        } else {
            int minEval = 100000;
            for (String move : this.board.getMoves(this.opponent)) {
                gs.moves.add(move);
                board.makeMove(move);

                int eval = minimax(gs, currentDepth-1, alpha, beta, true);
                gs.moves.remove(gs.moves.size() - 1);
                board.undoMove();

                minEval = Math.min(minEval, eval);
                beta = Math.min(beta, eval);
                if (beta <= alpha) break;
            }

            return minEval;
        }
    }

}