package searchAlgorithms.DFS.DLS;

import agents.Action;
import agents.Agent;
import agents.State;
import controller.MainProblem;
import problem.Problem;
import problem.Solution;
import searchAlgorithms.SearchAlgorithms;
import sun.applet.Main;
import tree.Node;

import java.util.ArrayList;
import java.util.Objects;
import java.util.Stack;

/**
 * Created by sina on 6/6/18.
 */
public class DLS_Grpah extends SearchAlgorithms implements Agent {


    private int limit;

    private ArrayList<State> visited = new ArrayList<>();

    public DLS_Grpah(int limit) {
        this.limit = limit;
    }


    @Override
    public Solution solve(MainProblem mainProblem, State start) {

        Problem problem = ((Problem) mainProblem);
        Stack<Node> frontier = new Stack<>();
        solution = new Solution();
        frontier.push(new Node(start));

        solution.memoryUsage++;
        visited.add(frontier.peek().getState());
        Object res = recursiveDLS(frontier.pop(), problem, limit);

        if (res != null && !res.getClass().equals(String.class))
            return (Solution) res;
        else return null;

    }


    public Object recursiveDLS(Node node, Problem problem, int limit) {

        solution.expandedNodes++;
        solution.visitedNodes++;
        solution.memoryUsage = Math.max(solution.memoryUsage, visited.size());
        if (!visited.contains(node.getState()))
            visited.add(node.getState());
        if (problem.isGoal(node.getState())) {
            solution.setBestPath(node, (Problem.State) problem.getInitialState());
            solution.cost = node.getPathCost();
            return solution;
        }
//        cutt off occured
        else if (limit == 0) return "cutOff";
        else {

            boolean cutOffOccured = false;
            for (Action action : problem.actionsFor(node.getState())) {


                State nextState = problem.move(node.getState(), action);
                Node child = new Node(nextState, node, action, node.getPathCost() +
                        problem.stepCost(node.getState(), nextState, action), node.getDepth() + 1);

                if (visited(child.getState(), visited) == false) {
                    visited.add(child.getState());
                    Object result = recursiveDLS(child, problem, limit - 1);


                    if (result != null && result.getClass().equals(String.class)) {
                        if (((String) result).compareTo("cutOff") == 0)
                            cutOffOccured = true;
                    } else if (result != null)
                        return result;
                }


            }

            if (cutOffOccured == true)
                return "cutOff";
            else return null;


        }
    }


    private boolean visited(State state, ArrayList<State> visited) {

        if (visited.contains(state))
            return true;
        return false;

    }


}
