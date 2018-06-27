package localsearch.hillclimbing;

import agents.State;
import controller.MainSolution;
import localsearch.LocalNode;
import tree.Node;

import java.util.ArrayList;
import java.util.Stack;

/**
 * Created by sina on 6/13/18.
 */
public class Solution extends MainSolution{

    public State finalState;
    public  int value;
    public  int numberOfExpanedNodes;
    public  int numberOfVisitedNodes;
    private ArrayList<LocalNode> bestPath = new ArrayList<>();

    public void setBestPath(LocalNode goal) {
        LocalNode currentNode = goal;
        LocalNode parent = goal.getParent();
        Stack<LocalNode> path = new Stack<>();

        while (currentNode != null) {

            path.push(currentNode);
            currentNode = parent;
            if (currentNode != null)
                parent = currentNode.getParent();
        }
        while (!path.isEmpty())
            bestPath.add(path.pop());

    }

    public ArrayList<LocalNode> getBestPath() {
        return bestPath;
    }


    public String to_strString_HilClimbing(){

        String str = finalState.to_String();
        str +="\nNumber of visited :\t"+numberOfVisitedNodes;
        str+="\nNumber of Expanded :\t"+ numberOfExpanedNodes;

        str+="\n path is as follows:\n";
        for (int i =0 ; i < bestPath.size() ; i++){

            str+= bestPath.get(i).getState().to_String() + "\n";
        }

        return str;


    }
}
