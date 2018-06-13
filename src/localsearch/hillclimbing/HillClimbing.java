package localsearch.hillclimbing;

import agents.Action;
import agents.Agent;
import agents.State;
import controller.MainProblem;
import controller.MainSolution;
import localsearch.LocalNode;
import localsearch.LocalSearchAlgorithm;
import localsearch.localSearchProblems.Problem;


import java.util.ArrayList;
import java.util.PriorityQueue;

/**
 * Created by sina on 6/13/18.
 */
public class HillClimbing extends LocalSearchAlgorithm  implements Agent{


    private int flatCounter = 0 ;

    @Override
    public MainSolution solve(MainProblem mainProblem, State start) {

        Problem problem = ((Problem) mainProblem);
        Solution solution =  new Solution();
        LocalNode currentNode = new LocalNode(start);
        currentNode.setValue(problem.calculateValue(start));
        while (true) {

            PriorityQueue<LocalNode> neighbours = problem.getNeighbours(currentNode);

            solution.numberOfVisitedNodes += neighbours.size();

            LocalNode bestNeighbour = neighbours.poll();
            if (bestNeighbour.getValue() > currentNode.getValue()) {
                System.out.println(currentNode.getState().to_String());
                return solution;
            } else if (bestNeighbour.getValue() == currentNode.getValue()) {
                if (flatCounter == 100) {
                    System.out.println(currentNode.getState().to_String());
                    solution.finalState = currentNode.getState();
                    return solution;
                } else {
                    flatCounter++;
                    solution.numberOfExpanedNodes++;

                    currentNode = bestNeighbour;
                }
            } else {
                flatCounter = 0;
                currentNode = bestNeighbour;
            }
        }



    }
}
