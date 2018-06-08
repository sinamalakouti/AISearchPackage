package searchAlgorithms.DFS.DLS;

import agents.Action;
import agents.Agent;
import agents.State;
import problem.Problem;
import problem.Solution;
import searchAlgorithms.SearchAlgorithms;
import tree.Node;

import java.util.Stack;

/**
 * Created by sina on 6/6/18.
 */
public class DLS_Tree extends SearchAlgorithms implements Agent {

    private int limit;


    public DLS_Tree(int limit) {
        this.limit = limit;
    }

    @Override

    public Solution solve(Problem problem, Problem.State start) {
        Stack<Node> frontier = new Stack<>();

        frontier.push(new Node(start));
        solution.memoryUsage++;
        Object res = recursiveDLS(frontier.pop(), problem, limit);

        if (res != null && !res.getClass().isInstance(String.class))
            return (Solution) res;
        else return null;

    }


    public Object recursiveDLS(Node node, Problem problem, int limit) {


        solution.expandedNodes++;
        solution.visitedNodes++;


        if (problem.isGoal(node.getState())) {
            solution.setBestPath(node, (Problem.State) problem.getInitialState());
            solution.cost = node.getPathCost();
            return solution;
        }
//        cut off occurred
        else if (limit == 0) return "cutOff";
        else {

            boolean cutOffOccured = false;
            for (Action action : problem.actionsFor(node.getState())) {


                State nextState = problem.move(node.getState(), action);
                Node child = new Node(nextState, node, action, node.getPathCost() + problem.stepCost(node.getState(), nextState, action), node.getDepth() + 1);


                Object result = recursiveDLS(child, problem, limit - 1);


                if (result != null && result.getClass().equals(String.class)) {
                    if (((String) result).compareTo("cutOff") == 0)
                        cutOffOccured = true;
                } else if (result != null)
                    return result;


            }

            if (cutOffOccured == true)
                return "cutOff";
            else return null;


        }
    }


}
