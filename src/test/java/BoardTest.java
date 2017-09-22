import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.*;

public class BoardTest {
    private Board board;
    private Board goalBoard;

    @Before
    public void setUp() {
        board = new Board(new int[][] {
                {0, 1, 3},
                {4, 2, 5},
                {7, 8, 6}
        });

        goalBoard = new Board(new int[][]{
                {1, 2, 3},
                {4, 5, 6},
                {7, 8, 0}
        });
    }

    @Test
    public void itShouldHaveValidDimension() {
        assertEquals(3, board.dimension());
    }

    @Test
    public void itShouldRecognizeGoalBoard() {
        assertFalse(board.isGoal());
        assertTrue(goalBoard.isGoal());

        Board notGoalBoard = new Board(new int[][]{
                {1, 2, 3},
                {4, 5, 6},
                {8, 7, 0}
        });
        assertFalse(notGoalBoard.isGoal());
    }

    @Test
    public void hammingDistance() {
        assertEquals(4, board.hamming());
        assertEquals(0, goalBoard.hamming());
    }

    @Test
    public void manhattanDistance() {
        assertEquals(4, board.manhattan());
        assertEquals(0, goalBoard.manhattan());

        Board board2 = new Board(new int[][]{
                {0, 1, 3},
                {4, 2, 5},
                {7, 8, 6}
        });

        assertEquals(4, board2.manhattan());
    }

    @Test
    public void twin() {
        Board board = new Board(new int[][] {
                {0, 1},
                {4, 2},
        });

        Board expectedTwin = new Board(new int[][]{
                {0, 4},
                {1, 2}
        });

        Board twin = board.twin();

        assertEquals(expectedTwin, twin);
    }

    @Test
    public void neighbors() {
        Board[] expectedNeighbors = {
                new Board(new int[][]{
                        {4, 1, 3},
                        {0, 2, 5},
                        {7, 8, 6}
                }),
                new Board(new int[][]{
                        {1, 0, 3},
                        {4, 2, 5},
                        {7, 8, 6}
                }),
        };

        ArrayList<Board> neighbors = new ArrayList<>();
        for(Board n : board.neighbors()) {
            neighbors.add(n);
        }

        assertArrayEquals(expectedNeighbors, neighbors.toArray());
    }
}
