package searchAlgorithms.UCS;

import agents.Action;
import agents.Agent;
import agents.State;
import problem.Problem;
import problem.Solution;
import searchAlgorithms.SearchAlgorithms;
import tree.Node;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.PriorityQueue;

/**
 * Created by sina on 6/6/18.
 */
public class UCS_Graph extends SearchAlgorithms implements Agent {

    @Override
    public Solution solve(Problem problem, Problem.State start) {


        PriorityQueue<Node> frontier = new PriorityQueue<Node>();
        ArrayList<State> explored = new ArrayList<>();
        solution = new Solution();

        Node currentnode = new Node(start);
        frontier.add(currentnode);
        solution.memoryUsage++;
        while (!frontier.isEmpty()) {
            currentnode = frontier.poll();

            solution.expandedNodes++;
            if (problem.isGoal(currentnode.getState())) {

                solution.setBestPath(currentnode, (Problem.State) start);
                solution.cost = currentnode.getPathCost();
                return solution;
            }
            ArrayList<Action> actions = problem.actionsFor(currentnode.getState());
            for (Action action : actions) {
                State childState = problem.move(currentnode.getState(), action);
                Node child = new Node(childState, currentnode, action, currentnode.getPathCost() +
                        problem.stepCost(currentnode.getState(), childState, action), currentnode.getDepth() + 1);
                if (!explored.contains(childState))
                    if (!isExist(child, frontier)) {
                        solution.visitedNodes++;
                        explored.add(childState);
                        frontier.add(child);
                    }
            }

            solution.memoryUsage = Math.max(solution.memoryUsage, frontier.size() + explored.size());
        }


        return null;
    }

    public boolean isExist(Node next, PriorityQueue<Node> frontier) {

        Iterator it = frontier.iterator();

        while (it.hasNext()) {
            Node temp = (Node) it.next();
            if (next.equals(temp.getState())) {
                if (next.getPathCost() < temp.getPathCost()) {
                    frontier.remove(temp);
                    return false;
                } else
                    return true;
            }

        }
        return false;

    }

}
