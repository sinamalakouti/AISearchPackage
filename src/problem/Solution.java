package problem;

import tree.Node;

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

    public Solution() {
        visitedNodes = 0;
        expandedNodes = 0;
        bestPath = new LinkedList<>();
        cost = 0;
        memoryUsage = 0;
    }

    @Override
    public String toString() {
        String str = "";
        str = "visited Nodes :" + visitedNodes + "\n expanded nodes : "+ expandedNodes +"\n cost : "+ cost + "\nmemory usage : " + memoryUsage+" \n path is :" ;

        int counter = 0 ;
        for (int i =0 ; i < bestPath.size() ; i++){

            counter ++;
            str += bestPath.get(i).getState().to_String() + " -> ";
        }
        System.out.println(counter);
        return str;
    }

    public void setBestPath(Node goal, Problem1.State start){
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
