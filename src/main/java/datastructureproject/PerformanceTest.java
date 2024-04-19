package datastructureproject;


import java.util.ArrayList;
import java.util.List;
import chess.bot.ChessBot;
import chess.engine.GameState;
import java.util.Scanner;
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
        Scanner s  = new Scanner(System.in);
        while (!bot.gameOver) {
            bot.print();
            gs.moves.add(s.nextLine());
            String move = bot.nextMove(gs);
            gs.moves.add(move);
            System.out.println(move);
            if (bot.gameOver) {
                System.out.println("CHECKMATE");
                bot.print();
                break;
            }
        }
        s.close();
    }

}
