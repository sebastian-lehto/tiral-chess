package datastructureproject;


import java.util.ArrayList;
import java.util.List;
import chess.bot.ChessBot;
import chess.engine.GameState;

/**
 * Use this class to write performance tests for your bot.
 * 
 */
public class PerformanceTest {

    private ChessBot bot = new AntonBot();
    private List<GameState> gsList = new ArrayList();

    public void setGsList(List<GameState> gsList) {
        this.gsList = gsList;
    }


    public static void main(String[] args) {
        AntonBot bot = new AntonBot();
        GameState gs = new GameState();
        gs.moves.add("g1f3");
        gs.moves.add(bot.nextMove(gs));
        gs.moves.add("f3h4");
        gs.moves.add(bot.nextMove(gs));
        gs.moves.add("h4f5");        
        gs.moves.add(bot.nextMove(gs));
        gs.moves.add("f5g7");        
        gs.moves.add(bot.nextMove(gs));
    }

}
