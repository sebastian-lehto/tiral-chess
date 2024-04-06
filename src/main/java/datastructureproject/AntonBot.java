package datastructureproject;

import chess.bot.ChessBot;
import chess.engine.GameState;

import java.util.ArrayList;
import java.util.Random;

public class AntonBot implements ChessBot { 
    private AntonBoard board;
    private Random random;
    private int depth;
    private Character side;
    private Character opponent;
    private String move;
    
    public AntonBot() {
        this.board = new AntonBoard();
        this.random = new Random();
        this.depth = 5;
        this.side = 'b';
        this.opponent = 'w';
        this.move = "NO BEST MOVE YET";
    }
    public void print(GameState gs) {
        this.board = new AntonBoard();
        for (String move : gs.moves) {
            this.board.doMove(move);
        }
        this.board.printBoard();
    }
    @Override
    public String nextMove(GameState gs) {
        this.board = new AntonBoard();
        if (gs.getMoveCount() == 0) {
            this.side = 'w';
            this.opponent = 'b';
        }
        for (String m : gs.moves) {
            board.doMove(m);
        }
        int eval = minimax(gs, this.depth, -100000, 100000, true);
        this.board.doMove(this.move);
        return this.move;
    }

    public int minimax(GameState gs, int currentDepth, int alpha, int beta, boolean maxing) {
        this.board = new AntonBoard();
        
        for (String m : gs.moves) {
            board.doMove(m);
        }
        if (currentDepth == 0 || this.board.getMoves(side).isEmpty()) {
            if (side == 'b') return this.board.evaluate() * -1;
            return this.board.evaluate();
        }
        String bestMove = "";
        
        if (maxing) {
            int maxEval = -100000;
            for (String move : this.board.getMoves(this.side)) {
                gs.moves.add(move);
                board.doMove(move);

                int eval = minimax(gs, currentDepth-1, alpha, beta, false);
                gs.moves.remove(gs.moves.size() - 1);
                if (eval > maxEval) {
                    if (currentDepth == this.depth) {
                        bestMove = move; 
                        this.move = bestMove;
                        System.out.println("best move_ " + move + " eval: " + eval);
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
                board.doMove(move);

                int eval = minimax(gs, currentDepth-1, alpha, beta, true);
                gs.moves.remove(gs.moves.size() - 1);
                minEval = Math.min(minEval, eval);
                beta = Math.min(beta, eval);
                if (beta <= alpha) break;
            }
            if (minEval == 100000) { 
                System.out.println("NO MOVES FOR OPPONENT HERE: ");
                print(gs);
            }

            return minEval;
        }
    }

}