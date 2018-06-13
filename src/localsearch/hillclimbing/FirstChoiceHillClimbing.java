package localsearch.hillclimbing;

import agents.Agent;
import agents.State;
import controller.MainProblem;
import controller.MainSolution;
import localsearch.LocalNode;
import localsearch.LocalSearchAlgorithm;
import localsearch.localSearchProblems.Problem;

import java.util.PriorityQueue;

/**
 * Created by sina on 6/13/18.
 */
public class FirstChoiceHillClimbing extends LocalSearchAlgorithm implements Agent {



    private int flatCounter = 0 ;

    @Override
    public MainSolution solve(MainProblem mainProblem, State start) {

        Problem problem = ((Problem) mainProblem);
        Solution solution =  new Solution();
        LocalNode currentNode = new LocalNode(start);
        currentNode.setValue(problem.calculateValue(start));
        while (true) {
            LocalNode neighbour = problem.getFirstBetterNeighbour(currentNode);
            if (neighbour == null)
                return  null;
            solution.numberOfVisitedNodes ++;

            LocalNode bestNeighbour = neighbour;
            if (bestNeighbour.getValue() > currentNode.getValue()) {
                System.out.println(currentNode.getState().to_String());
                System.out.println(currentNode.getValue());
                solution.finalState = currentNode.getState();
                solution.value = problem.calculateValue(solution.finalState);
                return solution;
            } else if (bestNeighbour.getValue() == currentNode.getValue()) {
                if (flatCounter == 100) {
                    System.out.println(currentNode.getState().to_String());
                    solution.finalState = currentNode.getState();
                    solution.value = problem.calculateValue(solution.finalState);
                    System.out.println(currentNode.getValue());
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


