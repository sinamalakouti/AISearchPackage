package localsearch.hillclimbing;

import agents.Agent;
import agents.State;
import controller.MainProblem;
import controller.MainSolution;
import localsearch.LocalNode;
import localsearch.LocalSearchAlgorithm;
import localsearch.localSearchProblems.Problem;

import java.util.PriorityQueue;
import java.util.Random;

/**
 * Created by sina on 6/13/18.
 */
public class StochasticHillClimbing extends LocalSearchAlgorithm implements Agent {




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

            boolean nodeChosed = false;

            LocalNode bestNeighbour = neighbours.poll();

            while(!nodeChosed && neighbours.size() > 0) {

                 bestNeighbour = neighbours.poll();
                 int deltaValue = bestNeighbour.getValue() - currentNode.getValue();
                 double p = (1 / Math.exp( - 1 * deltaValue));
                 double rand = Math.random();
                 if ( rand <=  p )
                     nodeChosed = true;


            }




            if (bestNeighbour.getValue() < currentNode.getValue()) {
                solution.finalState = currentNode.getState();
                solution.setBestPath(currentNode);
                solution.value = problem.calculateValue(solution.finalState);
                return solution;
            } else if (bestNeighbour.getValue() == currentNode.getValue()) {
                if (flatCounter == 1000) {
                    solution.finalState = currentNode.getState();
                    solution.setBestPath(currentNode);
                    solution.value = problem.calculateValue(solution.finalState);
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

