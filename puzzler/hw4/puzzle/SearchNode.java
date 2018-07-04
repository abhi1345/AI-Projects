package hw4.puzzle;
//import java.util.*;
//import java.lang.*;
//import java.io.*;

public class SearchNode implements Comparable<SearchNode> {
    WorldState worldState;
    int movesFromInit;
    SearchNode prevNode;
    int priority;

    public SearchNode(WorldState state, int numMoves, SearchNode previous) {
        worldState = state;
        movesFromInit = numMoves;
        prevNode = previous;
        priority = state.estimatedDistanceToGoal() + numMoves;
    }

    public int compareTo(SearchNode b) {
        return this.priority - b.priority;
    }

}

