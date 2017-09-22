import edu.princeton.cs.algs4.MinPQ;

import java.util.ArrayDeque;
import java.util.Comparator;
import java.util.Deque;

public class Solver {
    private MinPQ<Node> pq, pq2;
    private Deque<Board> moves2;
    private boolean isSolvable = false;

    public Solver(Board initial) {
        moves2 = new ArrayDeque<>();
        pq = new MinPQ<>(8, new HammingComparator());
//        pq2 = new MinPQ<>(8, new HammingComparator());

        Node initialNode = new Node();
        initialNode.board = initial;
        initialNode.previous = null;
        initialNode.moves = 0;
        initialNode.cost = initialNode.board.manhattan();
        pq.insert(initialNode);

        Node initialNode2 = new Node();
        initialNode2.board = initial.twin();
        initialNode2.previous = null;
        initialNode2.moves = 0;
        initialNode2.cost = initialNode2.board.manhattan();
        pq.insert(initialNode2);

        while (true) {
            Node currentMove = pq.delMin();
//            Node currentMove2 = pq2.delMin();

//            if (currentMove2.board.isGoal()) return;

            if (currentMove.board.isGoal()) {
                isSolvable = true;
                Node n = currentMove;
                while (n != null) {
                    moves2.addFirst(n.board);
                    n = n.previous;
                }

                break;
            }
            for (Board neighbour : currentMove.board.neighbors()) {
                if (currentMove.previous != null && neighbour.equals(currentMove.previous.board)) continue;
                Node node = new Node();
                node.board = neighbour;
                node.previous = currentMove;
                node.moves = currentMove.moves + 1;
                node.cost = node.board.manhattan();
                pq.insert(node);
            }

//            for (Board neighbour : currentMove2.board.neighbors()) {
//                if (currentMove.previous != null && neighbour.equals(currentMove2.previous.board)) continue;;
//                Node node = new Node();
//                node.board = neighbour;
//                node.previous = currentMove2;
//                node.moves = currentMove2.moves + 1;
//                node.cost = node.board.manhattan();
//                pq2.insert(node);
//            }
        }

        if (moves2.peekFirst().equals(initial)) {
            isSolvable = true;
        } else {
            isSolvable = false;
            moves2.clear();
        }
    }

    public boolean isSolvable() {
        return isSolvable;
    }

    public int moves() {
        return moves2.size() - 1;
    }

    public Iterable<Board> solution() {
        if (!isSolvable()) return null;
        return moves2;
    }

    private class Node {
        Board board;
        int moves;
        int cost;
        Node previous;
    }

    private class HammingComparator implements Comparator<Node> {
        @Override
        public int compare(Node node1, Node node2) {
            int thisCost = node1.cost + node1.moves;
            int otherCost = node2.cost + node2.moves;

            if (thisCost < otherCost) {
                return -1;
            } else if (thisCost > otherCost) {
                return 1;
            }

            return 0;
        }
    }
}
