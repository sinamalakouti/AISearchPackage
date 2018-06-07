package searchAlgorithms.DFS;

import agents.Action;
import agents.Agent;
import agents.State;
import problem.Problem;
import problem.Solution;
import searchAlgorithms.SearchAlgorithms;
import tree.Node;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Stack;

/**
 * Created by sina on 6/6/18.
 */
public class DFS_Graph extends SearchAlgorithms implements Agent {


    @Override
    public Solution solve(Problem problem, Problem.State start) {

        Stack<Node> frontier = new Stack<>();
        ArrayList<State> explored = new ArrayList<>();
        solution = new Solution();

        frontier.push(new Node(start));
        solution.memoryUsage++;

        while (!frontier.isEmpty()) {

            Node currentNode = frontier.pop();
            solution.expandedNodes++;
            explored.add(currentNode.getState());

//            TODO : path cost , best path;
            if (problem.isGoal(currentNode.getState())) {
                solution.setBestPath(currentNode, (Problem.State) start);
                solution.cost = currentNode.getPathCost();
                return solution;
            }

            ArrayList<Action> actions = problem.actionsFor(currentNode.getState());

            for (Action action : actions) {


                State nexState = problem.move(currentNode.getState(), action);
                Node nextNode = new Node(nexState, currentNode, action, currentNode.getPathCost() +
                        problem.stepCost(currentNode.getState(), nexState, action), currentNode.getDepth() + 1);

                if(! visited(nexState,explored , frontier)) {
                    frontier.push(nextNode);
                    solution.visitedNodes++;
                }

            }

            solution.memoryUsage = Math.max(solution.memoryUsage, frontier.size()+ explored.size());


        }


        return null;
    }

    private boolean visited(State childState, ArrayList<State> explored, Stack<Node> frontier) {

//     todo : contains
        if (explored.contains(childState))
            return true;
        for (Node node : frontier) {

            if (node.getState().equals(childState)) {
                return true;

            }
        }

        return false;
    }
    @Override
    public Object execute(Object p) {
        return null;
    }

}
