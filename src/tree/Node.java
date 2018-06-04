package tree;

import agents.Action;
import agents.State;

import java.util.ArrayList;

/**
 * Created by sina on 6/4/18.
 */
public class Node {
//  attributes:

    private State state;
    private Node parent;
    private Action action;
    private  int pathCost;
    private  int depth;

//    constructors:

    public Node(State state, Node parent, Action action, int pathCost, int depth) {
        this.state = state;
        this.parent = parent;
        this.action = action;
        this.pathCost = pathCost;
        this.depth = depth;
    }

//    for root tree
    public Node(State state, Action action) {
        this.state = state;
        this.action = action;
        this.parent = null;
        this.pathCost = 0;
        this.depth = 0;
    }

//    helper methods
    public State getState() {

        return state;
    }

    public void setState(State state) {
        this.state = state;
    }

    public Node getParent() {
        return parent;
    }

    public void setParent(Node parent) {
        this.parent = parent;
    }

    public Action getAction() {
        return action;
    }

    public void setAction(Action action) {
        this.action = action;
    }

    public int getPathCost() {
        return pathCost;
    }

    public void setPathCost(int pathCost) {
        this.pathCost = pathCost;
    }

    public int getDepth() {
        return depth;
    }

    public void setDepth(int depth) {
        this.depth = depth;
    }
}
