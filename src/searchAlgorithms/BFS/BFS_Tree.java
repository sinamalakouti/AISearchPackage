package searchAlgorithms.BFS;

import agents.Action;
import agents.Agent;
import agents.State;
import problem.Problem;
import problem.Solution;
import searchAlgorithms.SearchAlgorithms;
import tree.Node;

import java.util.ArrayList;
import java.util.LinkedList;

/**
 * Created by sina on 6/6/18.
 */
public class BFS_Tree<S, A> extends SearchAlgorithms implements Agent<S, A>  {



    public Solution solve(Problem problem, State start) {


        LinkedList<Node> fringe = new LinkedList<Node>();

        fringe.addFirst(new Node(start));
        solution.visitedNodes++;
        solution.memoryUsage++;


        while (!fringe.isEmpty()) {

            Node currentNode = fringe.removeFirst();
// TODO:            solution.bestPath.addFirst();
            solution.expandedNodes++;


//   TODO:          solution.cost ++;
            if (problem.isGoal(currentNode.getState())) {
                return solution;
            }
            ArrayList<Action> actions = problem.actionsFor(currentNode.getState());
            for (int i = 0; i < actions.size(); i++) {
//               todo path cost


                State nxtState = problem.move(currentNode.getState(), actions.get(i));
                if (problem.isGoal(nxtState)) {
                    return solution;
                }

                Action act = actions.get(i);
                fringe.addLast(new Node(nxtState, currentNode, act, currentNode.getPathCost() + problem.stepCost(currentNode.getState(), nxtState, act),
                        currentNode.getDepth() + 1));
                solution.visitedNodes++;

            }

            solution.memoryUsage = Math.max(solution.memoryUsage, fringe.size());


        }

// not found
        return null;
    }

    @Override
    public A execute(S p) {
        return null;
    }
}
