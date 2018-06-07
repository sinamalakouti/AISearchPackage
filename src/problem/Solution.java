package problem;

import agents.Action;
import agents.State;
import tree.Node;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Stack;

/**
 * Created by sina on 6/4/18.
 */
public class Solution {

    public int visitedNodes ;
    public int expandedNodes;
    public LinkedList<Node> bestPath;
    public int cost;
    public double memoryUsage;
    public Problem problem ;

    public Solution() {
        visitedNodes = 0;
        expandedNodes = 0;
        bestPath = new LinkedList<>();
        cost = 0;
        memoryUsage = 0;
    }

    @Override
    public String toString() {
        if (problem.getClass().equals(Problem1.class))
            return to_String1();
        else
            return to_String2();


    }


    public String to_String1(){

        String str = "";
        str = "visited Nodes :" + visitedNodes + "\n expanded nodes : "+ expandedNodes +"\n cost : "+ cost + "\nmemory usage : " + memoryUsage+" \n path is :" ;

        int counter = 0 ;
        for (int i =0 ; i < bestPath.size() ; i++){

            counter ++;
            if ( i != bestPath.size() - 1)
                str += bestPath.get(i).getState().to_String() + " -> ";
            else
                str += bestPath.get(i).getState().to_String() ;

        }
        System.out.println(counter);
        return str;

    }

    private String to_String2(){

        String str = "";
        str = "visited Nodes :" + visitedNodes + "\n expanded nodes : "+ expandedNodes +"\n cost : "+ cost + "\nmemory usage : " + memoryUsage+" \n path is :\n" ;
        String result = "";
        int counter = 0 ;
        for (int i =0 ; i < bestPath.size() ; i++){

            counter ++;
            if ( i != bestPath.size() - 1) {
                str += bestPath.get(i).getState().to_String() + " \n" + "\n";
                    result+= problem.parseAction((Problem.State)bestPath.get(i).getState(),(Problem.State)bestPath.get(i+1).getState()) + " \t";
            }
            else {
                str += bestPath.get(i).getState().to_String();
            }

        }
        System.out.println(counter);
        return (str + "\nactions are :\t"+ result);



    }


    public void setBestPath(Node goal, State start){
        Node currentNode = goal;
        Node parent = goal.getParent();
        Stack<Node> path = new Stack<>();

        while(currentNode != null) {
            path.push(currentNode);
            currentNode = parent;
            if (currentNode != null)
            parent = currentNode.getParent();
        }
        while (!path.isEmpty())
            bestPath.add(path.pop());

    }
}
