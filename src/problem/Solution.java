package problem;

import tree.Node;

import java.util.LinkedList;

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
}
