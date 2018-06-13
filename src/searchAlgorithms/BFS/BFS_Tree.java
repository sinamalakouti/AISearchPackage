package searchAlgorithms.BFS;

import agents.Action;
import agents.Agent;
import agents.State;
import controller.MainProblem;
import problem.Problem;
import problem.Solution;
import searchAlgorithms.SearchAlgorithms;
import tree.Node;

import java.util.ArrayList;
import java.util.LinkedList;

/**
 * Created by sina on 6/6/18.
 */
public class BFS_Tree extends SearchAlgorithms implements Agent {


    public Solution solve(MainProblem mainProblem, State start) {

        Problem problem = ((Problem) mainProblem);

        LinkedList<Node> fringe = new LinkedList<Node>();

        fringe.addFirst(new Node(start));
        solution.visitedNodes++;
        solution.memoryUsage++;

        while (!fringe.isEmpty()) {

            Node currentNode = fringe.removeFirst();

            solution.expandedNodes++;


            if (problem.isGoal(currentNode.getState())) {
                solution.setBestPath(currentNode, (Problem.State) start);
                solution.cost = currentNode.getPathCost();
                return solution;
            }
            ArrayList<Action> actions = problem.actionsFor(currentNode.getState());
            for (int i = 0; i < actions.size(); i++) {


                State nxtState = problem.move(currentNode.getState(), actions.get(i));
                if (problem.isGoal(nxtState)) {
                    solution.setBestPath(currentNode, (Problem.State) start);
                    solution.cost = currentNode.getPathCost();
                    return solution;
                }

                Action act = actions.get(i);
                Node child = new Node(nxtState, currentNode, act, currentNode.getPathCost() + problem.stepCost(currentNode.getState(), nxtState, act),
                        currentNode.getDepth() + 1);
                fringe.addLast(child);
                solution.visitedNodes++;

            }

            solution.memoryUsage = Math.max(solution.memoryUsage, fringe.size());


        }

// not found
        return null;
    }


}
