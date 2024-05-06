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

    @Test
    public void botFindsBestMove() {
        GameState gs = new GameState();
        AntonBot bot = new AntonBot();

        gs.moves.add("a2a4");
        bot.nextMove(gs);
        gs.moves.add("a4a5");
        bot.nextMove(gs);
        gs.moves.add("a5a6");
        bot.nextMove(gs);
        assertEquals(bot.move, "b7a6");
    }

    @Test
    public void botFindsMate() {
        GameState gs = new GameState();
        AntonBot bot = new AntonBot();

        gs.moves.add("g2g4");
        bot.nextMove(gs);
        gs.moves.add("f2f3");
        bot.nextMove(gs);
        assertEquals(bot.gameOver, true);
    }
}
