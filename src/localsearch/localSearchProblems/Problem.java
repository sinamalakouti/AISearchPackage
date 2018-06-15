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
    public void setInitialState();
    public State getInitialState();
    public  LocalNode getFirstBetterNeighbour(LocalNode currentNode);
    public agents.State getFinalState();
    public ArrayList<Integer> createChromosome(State state);
    public ArrayList<Integer> getPossibleChromosomeValues();
    public State chromosomeToState(ArrayList<Integer> chromosoe);
    public ArrayList<LocalNode> getKStates(int k );
    }
