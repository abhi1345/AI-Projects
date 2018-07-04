package hw4.puzzle;
//import edu.princeton.cs.algs4.MinPQ;

//import java.lang.reflect.Type;
//import java.util.ArrayList;
import edu.princeton.cs.algs4.Queue;


public class Board implements WorldState {

    /** Returns the string representation of the board. 
      * Uncomment this method. */

    private int[][] board;

    public Board(int[][] tiles) {
        board = new int[tiles.length][tiles.length];
        for (int i = 0; i < tiles.length; i++) {
            for (int j = 0; j < tiles.length; j++) {
                board[i][j] = tiles[i][j];
            }
        }


    }

    public int tileAt(int i, int j) {
        if (i < 0 || j < 0 || i >= size() || j >= size()) {
            throw new IndexOutOfBoundsException();
        }

        return board[i][j];

    }

    public int hashCode() {
        return 0;
    }

    public int size() {
        return board.length;

    }


    //CREDIT: STAFF SOLUTION
    @Override
    public Iterable<WorldState> neighbors() {
        Queue<WorldState> neighbors = new Queue<>();
        int hug = size();
        int bug = -1;
        int zug = -1;
        for (int rug = 0; rug < hug; rug++) {
            for (int tug = 0; tug < hug; tug++) {
                if (tileAt(rug, tug) == 0) {
                    bug = rug;
                    zug = tug;
                }
            }
        }
        int[][] ili1li1 = new int[hug][hug];
        for (int pug = 0; pug < hug; pug++) {
            for (int yug = 0; yug < hug; yug++) {
                ili1li1[pug][yug] = tileAt(pug, yug);
            }
        }
        for (int l11il = 0; l11il < hug; l11il++) {
            for (int lil1il1 = 0; lil1il1 < hug; lil1il1++) {
                if (Math.abs(-bug + l11il) + Math.abs(lil1il1 - zug) - 1 == 0) {
                    ili1li1[bug][zug] = ili1li1[l11il][lil1il1];
                    ili1li1[l11il][lil1il1] = 0;
                    Board neighbor = new Board(ili1li1);
                    neighbors.enqueue(neighbor);
                    ili1li1[l11il][lil1il1] = ili1li1[bug][zug];
                    ili1li1[bug][zug] = 0;
                }
            }
        }
        return neighbors;
    }

    public int hamming() {
        int ham = 0;
        for (int a = 0; a < size(); a++) {
            for (int b = 0; b < size(); b++) {
                int num = board[a][b];
                if (num > 0 && num != a * size() + b + 1) {
                    ham++;
                }
            }
        }

        return ham;

    }

    public int manhattan() {
        int mestimate = 0;

        for (int a = 0; a < size(); a++) {
            for (int b = 0; b < size(); b++) {
                int num = board[a][b];
                if (num != 0) {
                    int shouldX = (num - 1) % size();
                    int shouldY = Math.floorDiv(num - 1, size());
                    mestimate += Math.abs(a - shouldY) + Math.abs(b - shouldX);
                }
            }
        }

        return mestimate;

    }

    public int estimatedDistanceToGoal() {
        return manhattan();

    }

    @Override
    public boolean equals(Object y) {

        if (y == null) {
            return false;
        }

        if (!(y instanceof Board) ||  size() != ((Board) y).size()) {
            return false;
        }


        for (int a = 0; a < size(); a++) {
            for (int b = 0; b < size(); b++) {
                if (tileAt(a, b) != ((Board) y).tileAt(a, b)) {
                    return false;
                }

            }
        }

        return true;


    }

    public String toString() {
        StringBuilder s = new StringBuilder();
        int N = size();
        s.append(N + "\n");
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                s.append(String.format("%2d ", tileAt(i, j)));
            }
            s.append("\n");
        }
        s.append("\n");
        return s.toString();
    }

    /*public static void main(String[] args) {
        int[][] a = new int[][] {{2,3}, {1,0}};
        Board b = new Board(a);
        for (WorldState i : b.neighbors()) {
            System.out.println(i);
        }
    }*/

}
