import java.util.ArrayList;
import java.util.Arrays;

public class Board {
    private int[][] blocks;

    public Board(int[][] blocks) {
        this.blocks = new int[blocks.length][blocks.length];
        for (int i = 0; i < blocks.length; i++) {
            for (int j = 0; j < blocks.length; j++) {
                this.blocks[i][j] = blocks[i][j];
            }
        }
    }

    public int dimension() {
        return blocks.length;
    }

    public int hamming() {
        int distance = 0;

        for (int i = 0; i < dimension() * dimension() - 1; i++) {
            int col = i % dimension();
            int row = i / dimension();

            if (blocks[row][col] != i + 1) distance++;
        }

        return distance;
    }

    public int manhattan() {
        int distance = 0;

        for (int i = 0; i < dimension() * dimension(); i++) {
            int col = i % dimension();
            int row = i / dimension();

            int block = blocks[row][col];
            if (block == 0) continue;
            int expectedCol = colForBlock(block);
            int expectedRow = rowForBlock(block);

            distance += manhattanDistance(col, row, expectedCol, expectedRow);
        }

        return distance;
    }

    public boolean isGoal() {
        return hamming() == 0;
    }

    public Board twin() {
        Board duplicate = duplicate();
        int[] p = firstTwoBlocks();

        duplicate.blocks[p[0]][p[1]] = blocks[p[2]][p[3]];
        duplicate.blocks[p[2]][p[3]] = blocks[p[0]][p[1]];

        return duplicate;
    }

    private int[] firstTwoBlocks() {
        int[] points = {-1, -1, -1, -1};

        for (int i = 0; i < blocks.length; i++) {
            for (int j = 0; j < blocks.length; j++) {
                if (blocks[i][j] != 0) {
                    if (points[0] == -1) {
                        points[0] = i;
                        points[1] = j;
                    } else if (points[2] == -1) {
                        points[2] = i;
                        points[3] = j;
                    } else {
                        return points;
                    }
                }
            }
        }

        return points;
    }

    public boolean equals(Object y) {
        if (y == null) return false;

        if (!y.getClass().equals(this.getClass())) return false;

        return Arrays.deepEquals(blocks, ((Board)y).blocks);
    }

    public Iterable<Board> neighbors() {
        ArrayList<Board> neighbors = new ArrayList<>();
        int[] emptyBlockCoordinates = findEmptyRowCol();
        int row = emptyBlockCoordinates[0];
        int col = emptyBlockCoordinates[1];

        if (row > 0) {
            Board neighbor = duplicate();
            neighbor.blocks[row - 1][col] = blocks[row][col];
            neighbor.blocks[row][col] = blocks[row - 1][col];
            neighbors.add(neighbor);
        }
        if (row < dimension() - 1) {
            Board neighbor = duplicate();
            neighbor.blocks[row][col] = blocks[row + 1][col];
            neighbor.blocks[row + 1][col] = blocks[row][col];
            neighbors.add(neighbor);
        }
        if (col > 0) {
            Board neighbor = duplicate();
            neighbor.blocks[row][col - 1] = blocks[row][col];
            neighbor.blocks[row][col] = blocks[row][col - 1];
            neighbors.add(neighbor);
        }
        if (col < dimension() - 1) {
            Board neighbor = duplicate();
            neighbor.blocks[row][col + 1] = blocks[row][col];
            neighbor.blocks[row][col] = blocks[row][col + 1];
            neighbors.add(neighbor);
        }

        return neighbors;
    }

    public String toString() {
        StringBuilder builder = new StringBuilder();

        builder.append(String.format("%d\n", dimension()));
        for (int i = 0; i < dimension(); i++) {
            for (int j = 0; j < dimension(); j++) {
                builder.append(String.format("%2d ", blocks[i][j]));
            }
            builder.append("\n");
        }

        return builder.toString();
    }

    private int manhattanDistance(int x0, int y0, int x1, int  y1) {
        return Math.abs(x0 - x1) + Math.abs(y0 - y1);
    }

    private int rowForBlock(int i) {
        if (i == 0) return dimension() - 1;
        return (i - 1) / dimension();
    }

    private int colForBlock(int i) {
        if (i == 0) return dimension() - 1;
        return (i - 1) % dimension();
    }

    private Board duplicate() {
        int[][] duplicate = new int[blocks.length][blocks.length];

        for (int i = 0; i < blocks.length; i++) {
            for (int j = 0; j < blocks.length; j++) {
                duplicate[i][j] = blocks[i][j];
            }
        }

        return new Board(duplicate);
    }

    private int[] findEmptyRowCol() {
        for (int i = 0; i < dimension(); i++) {
            for (int j = 0; j < dimension(); j++) {
                if (blocks[i][j] == 0) return new int[]{i, j};
            }
        }
        throw new RuntimeException();
    }
}