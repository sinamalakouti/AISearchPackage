package searchAlgorithms.astar;

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
 * Created by sina on 6/7/18.
 */
public class Astar_Graph extends SearchAlgorithms implements Agent {


    @Override
    public Solution solve(Problem problem, Problem.State start) {

        PriorityQueue<Node> frontier = new PriorityQueue<>();
        ArrayList<State> explored = new ArrayList<>();

        solution = new Solution();
        Node currentNode = new Node(start);
        frontier.add(currentNode);
        solution.visitedNodes++;
        solution.memoryUsage++;

        while (!frontier.isEmpty()) {

            currentNode = frontier.poll();
            solution.expandedNodes++;

            if (problem.isGoal((Problem.State) currentNode.getState())) {
                solution.cost = currentNode.getPathCost();
                solution.setBestPath(currentNode, start);
                return solution;
            }

            ArrayList<Action> actions = problem.actionsFor(currentNode.getState());

            for (Action action : actions) {

                State childState = problem.move(currentNode.getState(), action);
                Node child = new Node(childState, currentNode, action, currentNode.getPathCost() +
                        problem.stepCost(currentNode.getState(), childState, action), currentNode.getDepth() + 1);
                child.setFn(child.getPathCost() + getHeuristic((Problem.State) currentNode.getState(), (Problem.State) childState));
                if (!explored.contains(childState))
                    if (!isExist(child, frontier)) {
                        frontier.add(child);
                        solution.visitedNodes++;
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

    public int getHeuristic(Problem.State source, Problem.State dest) {

        double distance = Math.pow(source.getRow() - dest.getRow(), 2) + Math.pow(source.getCol() - dest.getCol(), 2);


        return (int) Math.floor(Math.sqrt(distance));

    }


}
