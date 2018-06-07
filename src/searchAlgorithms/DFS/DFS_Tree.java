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
public class DFS_Tree extends SearchAlgorithms implements Agent {

    public DFS_Tree() {
        solution = new Solution();
    }

    @Override
    public Solution solve(Problem problem, Problem.State start) {

        Stack<Node> frontier = new Stack<>();

        frontier.push(new Node(start));
        solution.visitedNodes++;
        solution.memoryUsage++;

        while (!frontier.isEmpty()) {

            Node currentNode = frontier.pop();
            solution.expandedNodes++;

//            TODO : path cost , best path;
            if (problem.isGoal(currentNode.getState()))
                return solution;

            ArrayList<Action> actions = problem.actionsFor(currentNode.getState());

            for (Action action : actions) {
                State nexState = problem.move(currentNode.getState(), action);
                Node nextNode = new Node(nexState, currentNode, action, currentNode.getPathCost() +
                        problem.stepCost(currentNode.getState(), nexState, action), currentNode.getDepth() + 1);
                frontier.push(nextNode);
                solution.visitedNodes++;


            }

            solution.memoryUsage = Math.max(solution.memoryUsage, frontier.size());


        }


        return null;
    }


    @Override
    public Object execute(Object p) {
        return null;
    }


}
