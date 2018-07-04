package hw4.puzzle;

import edu.princeton.cs.algs4.MinPQ;

import java.util.ArrayList;
//import java.util.Comparator;
//import java.util.*;

public class Solver {
    private MinPQ<SearchNode> boardHeap;
    private ArrayList<WorldState> winners;

    public Solver(WorldState initial) {
        boardHeap = new MinPQ<>();
        SearchNode current = new SearchNode(initial, 0, null);
        winners = new ArrayList<>();

        while (!current.worldState.isGoal()) {
            for (WorldState a : current.worldState.neighbors()) {
                if (current.prevNode == null || !a.equals(current.prevNode.worldState)) {
                    SearchNode currNode = new SearchNode(a, current.movesFromInit + 1, current);
                    boardHeap.insert(currNode);
                }
            }
            current = boardHeap.delMin();
        }

        if (current.worldState.isGoal()) {
            boardHeap.insert(current);
        }

        while (current.worldState != initial) {
            winners.add(0, current.worldState);
            current = current.prevNode;
        }
        winners.add(0, initial);
    }


    public int moves() {
        return winners.size() - 1;
    }


    public Iterable<WorldState> solution() {
        return winners;
    }
}
