package datastructureproject;

import chess.bot.ChessBot;
import chess.engine.GameState;

public class AntonBot implements ChessBot { 
    private AntonBoard board;

    public AntonBot() {
        this.board = new AntonBoard();
    }

    @Override
    public String nextMove(GameState gs) {
        this.board = new AntonBoard();
        for (String move : gs.moves) {
            String start = move.substring(0, 2);
            String end = move.substring(2, 4);
            board.doMove(start, end);
        }
        this.board.printBoard();
        return this.board.getMoves().get(0);
    }

}