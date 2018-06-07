package searchAlgorithms.BFS;

import agents.Action;
import agents.Agent;
import agents.State;
import problem.Problem;
import problem.Problem1;
import problem.Solution;
import searchAlgorithms.SearchAlgorithms;
import tree.Node;

import java.util.ArrayList;
import java.util.LinkedList;

/**
 * Created by sina on 6/6/18.
 */
public class BFS_Graph<S, A> extends SearchAlgorithms implements Agent<S, A> {


    public Solution solve(Problem problem, Problem.State start) {


        LinkedList<Node> fringe = new LinkedList<Node>();
        LinkedList<State> explored = new LinkedList<State>();
        solution = new Solution();
        fringe.addFirst(new Node(start));

        solution.memoryUsage++;
        while (!fringe.isEmpty()) {

            Node currentNode = fringe.removeFirst();
// TODO:            solution.bestPath.addFirst();
            solution.expandedNodes++;
            solution.visitedNodes++;
            explored.add(currentNode.getState());


//   TODO:          solution.cost ++;
            if (problem.isGoal(currentNode.getState())) {
                solution.setBestPath(currentNode, (Problem1.State) start);
                System.out.println("here");
                System.out.println(currentNode.getState().to_String());
                solution.cost = currentNode.getPathCost();
                return solution;
            }
            ArrayList<Action> actions = problem.actionsFor(currentNode.getState());
            for (int i = 0; i < actions.size(); i++) {
//               todo path cost


                State nxtState = problem.move(currentNode.getState(), actions.get(i));
                if (problem.isGoal(nxtState)) {
                   Node nex =  new Node(nxtState, currentNode, actions.get(i), currentNode.getPathCost() + problem.stepCost(currentNode.getState(), nxtState, actions.get(i)),
                            currentNode.getDepth() + 1);
                    solution.setBestPath(nex,  start);
                    solution.cost = nex.getPathCost();
                    return solution;
                }

                Action act = actions.get(i);
                fringe.addLast(new Node(nxtState, currentNode, act, currentNode.getPathCost() + problem.stepCost(currentNode.getState(), nxtState, act),
                        currentNode.getDepth() + 1));


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


    private boolean visited(State childState, LinkedList<State> explored, LinkedList<Node> frontier) {

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
}
