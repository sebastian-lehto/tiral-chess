package datastructureproject;

import static org.junit.Assert.*;

import org.junit.Test;


public class AntonBoardTest {

    public AntonBoardTest() {

    }

    @Test
    public void newBoardSetUpCorrectly() {
        String boardString = "";
        AntonBoard ab = new AntonBoard();
        for (String[] row : ab.board) {
            for (String piece : row) {
                boardString += piece;
            }
        }

        String correctString = "";
        String[][] correctBoard = {
            {"bR","bN","bB","bQ","bK","bB","bN","bR"},
            {"bP","bP","bP","bP","bP","bP","bP","bP"},
            {"--","--","--","--","--","--","--","--"},
            {"--","--","--","--","--","--","--","--"},
            {"--","--","--","--","--","--","--","--"},
            {"--","--","--","--","--","--","--","--"},
            {"wP","wP","wP","wP","wP","wP","wP","wP"},
            {"wR","wN","wB","wQ","wK","wB","wN","wR"}
        };
        for (String[] row : correctBoard) {
            for (String piece : row) {
                correctString += piece;
            }
        }

        assertEquals(boardString, correctString);
    }
}
