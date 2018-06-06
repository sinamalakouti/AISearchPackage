package searchAlgorithms.UCS;

import agents.Action;
import agents.Agent;
import agents.State;
import problem.Problem;
import problem.Problem1;
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
    public Solution solve(Problem problem, State start) {

        PriorityQueue<Node> frontier = new PriorityQueue<Node>();
        ArrayList<State> explored = new ArrayList<>();
        solution = new Solution();

        Node currentnode = new Node(start);
        frontier.add(currentnode);
        solution.memoryUsage++;
        while (!frontier.isEmpty()){
            currentnode = frontier.poll();
            if (problem.isGoal(currentnode.getState())) {

                solution.setBestPath(currentnode, (Problem1.State) start);
                solution.cost = currentnode.getPathCost();
                return solution;
            }
            ArrayList<Action> actions = problem.actionsFor(currentnode.getState());
            for ( Action action : actions){
                State childState = problem.move(currentnode.getState(), action);
                Node child = new Node(childState,currentnode, action, currentnode.getPathCost() +
                        problem.stepCost(currentnode.getState(),childState,action), currentnode.getDepth() + 1);

                if( ! isExist(childState ,frontier, explored)){
                    System.out.println("no");

                    frontier.add(child);
                }else {

                    Node temp = getNode(childState,frontier);

                    if(child.getPathCost() < temp.getPathCost())
                        frontier.add(child);
                    else
                        frontier.add(temp);


                }
            }
        }
//



        return null;
    }


    @Override
    public Object execute(Object p) {
        return null;
    }

    public boolean isExist(State  next, PriorityQueue<Node> frontier, ArrayList<State> explored){
//TODO :
        Iterator it = frontier.iterator();
        while (it.hasNext()) {
            if (  ((Node)it.next()).getState().equals(next)  ) {
                System.out.println("fukc");
                return true;
            }
            else
                System.out.println("yes");
        }

        System.out.println("lmao");

        for (int i =0 ; i< explored.size() ; i ++)
        {

           if ( next.equals(explored.get(i)))
               return true;

        }


        return  false;

    }
    public Node getNode(State  next, PriorityQueue<Node> frontier){
//TODO :
        Iterator it = frontier.iterator();
        while (it.hasNext()) {
            Node node = ((Node) it.next());
            if (  node.getState().equals(next)  ) {
//                TODO : CHECK
                it.remove();
                return node;
            }
        }


        return  null;

    }
}
