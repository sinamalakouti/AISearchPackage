package searchAlgorithms.bidirectional;

import agents.Action;
import agents.Agent;
import agents.State;
import problem.Problem;
import problem.Solution;
import searchAlgorithms.SearchAlgorithms;
import tree.Node;

import java.util.ArrayList;

/**
 * Created by sina on 6/7/18.
 */
public class Bidirectional_Tree extends SearchAlgorithms implements Agent {


    ArrayList<Node> destFrontier = new ArrayList<>();
    ArrayList<Node> srcFrontier = new ArrayList<>();


    Node startNode ;
    Node finalNode;

    @Override
    public Solution solve(Problem problem, Problem.State start) {

        Node frontCurrentNode = new Node(start);
        Node backCurrentNode = new Node(problem.getFinalState().get(0));

        solution = new Solution();



        srcFrontier.add(frontCurrentNode);
        destFrontier.add(backCurrentNode);
        solution.visitedNodes +=2;
        solution.memoryUsage +=2;


        startNode  = frontCurrentNode;
        finalNode = new Node(backCurrentNode.getState());


        while (!srcFrontier.isEmpty() && ! destFrontier.isEmpty()){

            if(!srcFrontier.isEmpty()){

                frontCurrentNode = srcFrontier.remove(0);
                solution.expandedNodes++;
                if(problem.isGoal(frontCurrentNode.getState()) || checker(frontCurrentNode, destFrontier) ){
                    backCurrentNode = getNext(frontCurrentNode,destFrontier);
                    Node child = new Node(backCurrentNode);


                    Node parent = frontCurrentNode;
                    Node child1 = new Node(backCurrentNode.getParent());
                    backCurrentNode.setParent(parent.getParent());

                    int cost = backCurrentNode.getPathCost();
                    while (child1.getParent() != null){
                        Node temp = new Node(child1.getParent());
                        child1.setParent(backCurrentNode);
                        backCurrentNode = new Node(child1);
                        child1 = new Node(temp);
                        if(child1.getState().equals(finalNode.getState()))
                            finalNode.setParent(backCurrentNode);


                    }


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
                    solution.visitedNodes++;





                }


            }

            if (!destFrontier.isEmpty()){
                Node tempNode = new Node(backCurrentNode.getState(),backCurrentNode.getParent(),backCurrentNode.getAction(),backCurrentNode.getPathCost(),backCurrentNode.getDepth());
                tempNode.setParent(backCurrentNode.getParent());
                backCurrentNode = destFrontier.remove(0);
                solution.expandedNodes++;

                if (  start.equals(backCurrentNode.getState()) || checker(backCurrentNode,srcFrontier)){

                    if(! backCurrentNode.getState().equals(start))
                        tempNode.setParent(backCurrentNode);

                    Node parent = getNext(backCurrentNode,srcFrontier);
                    Node child = new Node(backCurrentNode.getParent());
                    backCurrentNode.setParent(parent.getParent());

                    int cost = backCurrentNode.getPathCost();
                    while (child.getParent() != null){
                        Node temp = new Node(child.getParent());
                        child.setParent(backCurrentNode);
                        backCurrentNode = new Node(child);
                        child = new Node(temp);
                        if(child.getState().equals(finalNode.getState()))
                            finalNode.setParent(backCurrentNode);


                    }

                    solution.setBestPath(finalNode,  start);
                    solution.cost = cost * -1 + parent.getPathCost() ;
                    return  solution;
                }

                ArrayList<Action> U_inv = problem.reverseActionsFor(backCurrentNode.getState());

                for ( Action u_inv : U_inv){
                    State parentState = problem.reverseMove(backCurrentNode.getState(), u_inv);
                    Node parent = new Node(parentState,backCurrentNode,u_inv,backCurrentNode.getPathCost() -
                            problem.stepCost(parentState,backCurrentNode.getState(),u_inv), backCurrentNode.getDepth() - 1);




                        destFrontier.add(parent);
                    solution.visitedNodes++;

//                        backCurrentNode.setParent(parent);

                        if(backCurrentNode.getState().equals(finalNode.getState())) {
                            finalNode.setParent(parent);

                    }


                }



            }



                solution.memoryUsage = Math.max(solution.memoryUsage , destFrontier.size() + srcFrontier.size());
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
