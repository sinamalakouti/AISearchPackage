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
            if (bestNeighbour.getValue() < currentNode.getValue()) {

                solution.finalState = currentNode.getState();
                solution.setBestPath(currentNode);
                solution.value = problem.calculateValue(solution.finalState);
                return solution;
            } else if (bestNeighbour.getValue() == currentNode.getValue()) {
                if (flatCounter == 0) {
                    solution.finalState = currentNode.getState();
                    solution.value = problem.calculateValue(solution.finalState);
                    solution.setBestPath(currentNode);
                    return solution;
                } else {
                        flatCounter++;
                        solution.numberOfExpanedNodes++;
                    LocalNode parent = new LocalNode(currentNode.getState());
                    parent.setParent(currentNode.getParent());
                    bestNeighbour.setParent(parent);

                    currentNode = bestNeighbour;
                }
            } else {
                flatCounter = 0;
                solution.numberOfExpanedNodes++;
                LocalNode parent = new LocalNode(currentNode.getState());
                parent.setParent(currentNode.getParent());
                bestNeighbour.setParent(parent);
                currentNode = bestNeighbour;
            }
        }



    }
}


