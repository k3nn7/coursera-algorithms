import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.*;

public class SolverTest {
    private Solver solver;

    @Before
    public void setUp() {
        Board board = new Board(new int[][] {
                {1, 2, 3},
                {4, 5, 6},
                {0, 7, 8}
        });

        solver = new Solver(board);
    }

    @Test
    public void solve() {
        Board[] expectedMoves = {
                new Board(new int[][] {
                        {1, 2, 3},
                        {4, 5, 6},
                        {0, 7, 8}
                }),
                new Board(new int[][]{
                        {1, 2, 3},
                        {4, 5, 6},
                        {7, 0, 8}
                }),
                new Board(new int[][]{
                        {1, 2, 3},
                        {4, 5, 6},
                        {7, 8, 0}
                }),
        };

        ArrayList<Board> moves = new ArrayList<>();
        for (Board m : solver.solution()) {
            moves.add(m);
        }

        assertTrue(solver.isSolvable());
        assertEquals(2, solver.moves());
        assertArrayEquals(expectedMoves, moves.toArray());
    }

    @Test
    public void unsolvablePuzzle() {
        Board board = new Board(new int[][] {
                {2, 1, 3},
                {4, 5, 6},
                {0, 7, 8}
        });

       solver = new Solver(board);

       assertFalse(solver.isSolvable());
       assertNull(solver.solution());
    }
}
