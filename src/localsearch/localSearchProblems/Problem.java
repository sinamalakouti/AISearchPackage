package localsearch.localSearchProblems;

import agents.State;
import controller.MainProblem;
import localsearch.LocalNode;

import java.util.ArrayList;
import java.util.PriorityQueue;

/**
 * Created by sina on 6/13/18.
 */
public interface Problem  extends MainProblem{


    PriorityQueue<LocalNode> getNeighbours(LocalNode currentNode);   // maxHeap

    public int calculateValue (State state);
    public void setInitialState( int numberOfColors);


}
