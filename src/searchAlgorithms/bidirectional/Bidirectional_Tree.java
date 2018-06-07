package searchAlgorithms.bidirectional;

import agents.Action;
import agents.Agent;
import agents.State;
import problem.Problem;
import problem.Problem1;
import problem.Solution;
import searchAlgorithms.SearchAlgorithms;
import tree.Node;

import java.util.ArrayList;

/**
 * Created by sina on 6/7/18.
 */
public class Bidirectional_Tree extends SearchAlgorithms implements Agent {
    ArrayList<Node> destFrontier;
    ArrayList<Node> srcFrontier;


    @Override
    public Solution solve(Problem problem, State start) {

        Node frontCurrentNode = new Node(start);
        Node backCurrentNode = new Node(problem.getFinalState());

        srcFrontier.add(frontCurrentNode);
        destFrontier.add(backCurrentNode);


        Node startNode  = frontCurrentNode;
        Node finalNode = backCurrentNode;

        solution = new Solution();

        while (!srcFrontier.isEmpty() && ! destFrontier.isEmpty()){

            if(!srcFrontier.isEmpty()){

                frontCurrentNode = srcFrontier.remove(0);
                if(problem.isGoal(frontCurrentNode.getState()) || checker(frontCurrentNode, destFrontier) ){
                    Node child = getNext(frontCurrentNode,destFrontier);
//                    child.setParent(frontCurrentNode);
                    child.setParent(frontCurrentNode.getParent());
                    solution.setBestPath(finalNode,  start);
                    solution.cost = frontCurrentNode.getPathCost() + child.getPathCost() * -1;
                    return solution;

                }

                ArrayList<Action> U = problem.actionsFor(frontCurrentNode.getState());
                for (Action u: U) {
                    State childState = problem.move(frontCurrentNode.getState() , u);
                    Node child = new Node(childState,frontCurrentNode,u,frontCurrentNode.getPathCost() +
                            problem.stepCost(frontCurrentNode.getState(),childState,u),frontCurrentNode.getDepth() + 1);
                    srcFrontier.add(child);


                }
                
                
            }

            if (!destFrontier.isEmpty()){

                backCurrentNode = destFrontier.remove(0);
                if (  start.equals(backCurrentNode.getState()) || checker(backCurrentNode,srcFrontier)){

                    Node parent = getNext(backCurrentNode,srcFrontier);
                    backCurrentNode.setParent(parent.getParent());
                    solution.setBestPath(finalNode,  start);
                    solution.cost = backCurrentNode.getPathCost() * -1 + parent.getPathCost() ;
                    return  solution;
                }

                ArrayList<Action> U_inv = problem.reverseActionsFor(backCurrentNode.getState());

                for ( Action u_inv : U_inv){
                    State parentState = problem.reverseMove(backCurrentNode.getState(), u_inv);
                    Node parent = new Node(parentState,null,u_inv,backCurrentNode.getPathCost() -
                    problem.stepCost(parentState,backCurrentNode.getState(),u_inv), backCurrentNode.getDepth() - 1);
                    backCurrentNode.setParent(parent);

                    destFrontier.add(parent);

                }



            }




        }
        return  null;
    }
    public boolean checker(Node node , ArrayList<Node> list){

        for (int i =0 ; i< list.size() ; i++)
            if (list.get(i).getState().equals(node.getState()))
                return true;
        return false;
    }

    public Node getNext( Node node , ArrayList<Node> list) {

        for (int i =0 ; i< list.size() ; i++)
            if (list.get(i).getState().equals(node.getState()))
                return list.get(i);
        return null;
    }




    @Override
    public Object execute(Object p) {
        return null;
    }


}
