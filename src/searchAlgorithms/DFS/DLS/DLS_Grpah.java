package searchAlgorithms.DFS.DLS;

import agents.Action;
import agents.Agent;
import agents.State;
import problem.Problem;
import problem.Solution;
import searchAlgorithms.SearchAlgorithms;
import tree.Node;

import java.util.ArrayList;
import java.util.Objects;
import java.util.Stack;

/**
 * Created by sina on 6/6/18.
 */
public class DLS_Grpah extends SearchAlgorithms implements Agent{


    private int limit;

    private ArrayList<State> visited = new ArrayList<>();

    public DLS_Grpah(int limit) {
        this.limit = limit;
    }



    @Override
    public Solution solve(Problem problem, State start) {
        Stack<Node> frontier = new Stack<>();

        frontier.push(new Node(start));

        solution.memoryUsage++;
        visited.add(frontier.peek().getState());
        Object res = recursiveDLS(frontier.pop(), problem, limit);

        if (res != null && ! res.getClass().isInstance(String.class))
            return  (Solution) res;
        else return  null;

    }


    public Object recursiveDLS (Node node, Problem problem, int limit){


        if (problem.isGoal(node.getState()))
            return solution;
//        cutt off occured
        else  if (limit == 0 )return "cutOff";
        else {

            boolean cutOffOccured = false;
            for (Action action: problem.actionsFor(node.getState())) {
                State nextState = problem.move(node.getState(), action);
                Node child  = new Node(nextState,node,action, node.getPathCost() + problem.stepCost(node.getState(),nextState,action), node.getDepth() + 1);
                if (visited(child.getState(),visited) == false) {
                    visited.add(child.getState());
                    Object result = recursiveDLS(child, problem, limit - 1);

                    if (result != null && Objects.class.isInstance(String.class)) {
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


    private boolean visited (State state , ArrayList<State> visited ){
// TODO :

        if(visited.contains(state))
            return true;
        return  false;

    }


    @Override
    public Object execute(Object p) {
        return null;
    }

}
