package problem;

import agents.State;
import controller.MainSolution;
import localsearch.localSearchProblems.GraphColoring;
import tree.Node;

import java.util.LinkedList;
import java.util.Stack;

/**
 * Created by sina on 6/4/18.
 */
public class Solution  extends MainSolution{

    public int visitedNodes;
    public int expandedNodes;
    public LinkedList<Node> bestPath;
    public int cost;
    public double memoryUsage;
    public Problem problem;

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
        else if(problem.getClass().equals(Problem2.class) || problem.getClass().equals(Problem3.class))
            return to_String2();
        else if ( problem.getClass().equals(GraphColoring.class))
            return "";

        return "";


    }


    public String to_String1() {

        String str = "";
        str = " visited Nodes: " + visitedNodes + "\n expanded nodes :" + expandedNodes + "\n cost : " + cost + "\n memory usage : " + memoryUsage + " \n path is :";
        String result = "";

        int counter = 0;
        for (int i = 0; i < bestPath.size(); i++) {

            counter++;
            if (i != bestPath.size() - 1) {
                str += bestPath.get(i).getState().to_String() + " -> ";
                result += problem.parseAction((Problem.State) bestPath.get(i).getState(), (Problem.State) bestPath.get(i + 1).getState()) + " \t";
            } else
                str += bestPath.get(i).getState().to_String();

        }

        return str + "\n result is :\t" + result;

    }

    private String to_String2() {

        String str = "";
        str = " visited Nodes :" + visitedNodes + "\n expanded nodes : " + expandedNodes + "\n cost : " + cost + "\n memory usage : " + memoryUsage + " \n path is :\n";
        String result = "";
        int counter = 0;
        for (int i = 0; i < bestPath.size(); i++) {

            counter++;
            if (i != bestPath.size() - 1) {
                str += bestPath.get(i).getState().to_String() + " \n" + "\n";
                result += problem.parseAction((Problem.State) bestPath.get(i).getState(), (Problem.State) bestPath.get(i + 1).getState()) + " \t";
            } else {
                str += bestPath.get(i).getState().to_String();
            }

        }
        return (str + "\nactions are :\t" + result);


    }


    public void setBestPath(Node goal, State start) {
        Node currentNode = goal;
        Node parent = goal.getParent();
        Stack<Node> path = new Stack<>();

        while (currentNode != null) {
            path.push(currentNode);
            currentNode = parent;
            if (currentNode != null)
                parent = currentNode.getParent();
        }
        while (!path.isEmpty())
            bestPath.add(path.pop());

    }
}
