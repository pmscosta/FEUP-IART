package Solver;

import java.util.Comparator;
import java.util.HashSet;
import java.util.PriorityQueue;
import java.util.Set;

import Model.Klotski;

public class Greedy {
    private Set<Klotski> visited = new HashSet<>();

    private PriorityQueue<Klotski> priorityQueue = new PriorityQueue<Klotski>(11, new Comparator<Klotski>() {
        @Override
        public int compare(Klotski klotski, Klotski that) {
            int finalThis = klotski.calculateH();
            int finalThat = that.calculateH();

            if (finalThis < finalThat)
                return -1;
            else if (finalThis > finalThat)
                return 1;
            else
                return 0;
        }
    });

    public Greedy(Klotski map) {
        priorityQueue.add(map);
    }


    public void solve() {
        int steps = 0;
        while (!priorityQueue.isEmpty()) {
            steps++;
            Klotski klotski = priorityQueue.poll();

            if (klotski.isSolution()) {
                klotski.printSolution();
                System.out.println("\nSteps=" + steps);
                System.out.println("\nMoves=" + klotski.g + "\n");
                priorityQueue.clear();
                return;
            }

            for (Klotski nextPuzzle : klotski.getNextBoards()) {
                nextPuzzle.g = klotski.g;
                nextPuzzle.g += 1;

                if (!visited.contains(nextPuzzle)) {
                    nextPuzzle.parent = klotski;
                    priorityQueue.add(nextPuzzle);
                    visited.add(nextPuzzle);
                }

            }
        }

        System.out.println("Found no solution with " + steps + " steps");
    }
}
