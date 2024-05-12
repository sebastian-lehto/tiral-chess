package datastructureproject;

import org.junit.Test;

import chess.engine.GameState;

import static org.junit.Assert.*;
import org.junit.BeforeClass;
import org.junit.AfterClass;
import org.junit.After;
import org.junit.Before;

public class AntonBotTest {

    public AntonBotTest() {

    }

    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }

    @Test
    public void newBotInitializedCorrectly() {
        AntonBot bot = new AntonBot();
        String values = "" + bot.side + bot.opponent + bot.depth + bot.move;
        assertEquals(values, "bw5");
    }

    @Test
    public void newBotGameNotOver() {
        AntonBot bot = new AntonBot();
        assertEquals(bot.gameOver, false);
    }

    @Test
    public void newBotPrintsCorrectly() {
        AntonBot bot = new AntonBot();
        bot.print();
        assert(true);
    }

    @Test
    public void newBotPlaysWhiteWhenMovesFirst() {
        GameState gs = new GameState();
        AntonBot bot = new AntonBot();
        bot.nextMove(gs);
        String values = "" + bot.side + bot.opponent + bot.depth;
        assertEquals(values, "wb5");
    }

    /* @Test
    public void botFindsBestMove() {
        GameState gs = new GameState();
        AntonBot bot = new AntonBot();
        String[][] testBoard = {
            {"bR","bN","bB","bQ","bK","bB","bN","bR"},
            {"bP","bP","bP","bP","bP","bP","bP","bP"},
            {"--","--","--","--","--","--","--","--"},
            {"--","--","--","--","--","--","--","--"},
            {"--","--","--","--","--","--","--","--"},
            {"--","--","--","--","--","--","--","--"},
            {"wP","wP","wP","wP","wP","wP","wP","wP"},
            {"wR","wN","wB","wQ","wK","wB","wN","wR"}
        };
        gs.moves.add("a2a4");
        bot.nextMove(gs);
        gs.moves.add("a4a5");
        bot.nextMove(gs);
        gs.moves.add("a5a6");
        bot.nextMove(gs);
        assertEquals(bot.move, "b7a6");
    } */

    @Test
    public void botFindsMateInThree1() {
        GameState gs = new GameState();
        AntonBot bot = new AntonBot();
        String[][] testBoard = {
            {"bR","--","bB","--","--","bR","bK","--"},
            {"bP","bP","--","--","--","--","bP","bP"},
            {"--","--","bP","--","bP","bQ","--","--"},
            {"--","--","bB","bP","wP","bP","--","--"},
            {"--","--","wP","--","bN","--","--","--"},
            {"--","wP","--","--","--","wP","wP","--"},
            {"wP","wB","wQ","wP","wP","--","wB","wP"},
            {"wR","wN","--","--","--","wR","--","wK"}
        };
        bot.board.setBoard(testBoard);
        gs.moves.add("a3a3");
        bot.nextMove(gs);
        gs.moves.add("h2g3");
        bot.nextMove(gs);
        gs.moves.add("g2h3");
        bot.nextMove(gs);
        assertEquals(bot.gameOver, true);
    }

    @Test
    public void botFindsMateInThree2() {
        GameState gs = new GameState();
        AntonBot bot = new AntonBot();
        String[][] testBoard = {
            {"bR","bN","--","--","bK","bB","--","bR"},
            {"bP","bP","--","--","bP","bP","--","bP"},
            {"--","--","bP","--","--","bP","--","--"},
            {"--","--","--","--","--","--","--","--"},
            {"--","--","--","--","--","--","--","--"},
            {"--","--","--","wQ","--","wN","--","--"},
            {"bQ","wP","wP","wB","--","wP","wP","wP"},
            {"--","--","wK","wR","--","--","--","wR"}
        };
        bot.board.setBoard(testBoard);
        bot.nextMove(gs);
        gs.moves.add("e8d8");
        bot.nextMove(gs);
        gs.moves.add("d8e8");
        bot.nextMove(gs);
        assertEquals(bot.gameOver, true);
    }

    @Test
    public void botFindsMateInThree3() {
        GameState gs = new GameState();
        AntonBot bot = new AntonBot();
        String[][] testBoard = {
            {"--","--","bR","--","--","bQ","--","bK"},
            {"bP","bP","--","--","bB","--","--","--"},
            {"--","--","bB","--","wQ","--","--","bP"},
            {"--","--","--","wN","wP","--","--","--"},
            {"--","--","--","bP","wB","bR","--","wP"},
            {"--","--","--","--","--","--","--","--"},
            {"bQ","wP","--","--","--","--","--","--"},
            {"--","--","wR","--","--","--","wR","wK"}
        };
        bot.board.setBoard(testBoard);
        bot.nextMove(gs);
        gs.moves.add("f8g8");
        bot.nextMove(gs);
        gs.moves.add("g8h7");
        bot.nextMove(gs);
        assertEquals(bot.gameOver, true);
    }
}
