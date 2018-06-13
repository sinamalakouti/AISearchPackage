package localsearch.localSearchProblems;

import agents.State;
import localsearch.LocalNode;

import java.util.PriorityQueue;

/**
 * Created by sina on 6/13/18.
 */
public class GraphColoring implements Problem {

        int [][] adjMatix ;
        int numberOfNodes = 0 ;
        int numberOfColors = 0;
        private State initialState;
        private State finalState;


    public GraphColoring(int[][] adjMatix ,int n , int m) {
        this.adjMatix = adjMatix;
        this.numberOfNodes = n;
        this.numberOfColors = m;
    }

    @Override
    public PriorityQueue<LocalNode> getNeighbours(LocalNode currentNode) {

        PriorityQueue<LocalNode> neighbours = new PriorityQueue<>();
        for (int i = 0 ; i<numberOfNodes ; i ++){
            for (int j = 0 ; j< numberOfColors ;j++){


                int [] colors = ((State)currentNode.getState()).getGraph();
                if(colors[i] != j){
                    State newState = new State();
                    newState.setGraph(colors.clone());
                    newState.getGraph()[i] = j;
                    LocalNode neighbour = new LocalNode(newState);
                    neighbour.setValue(calculateValue(newState));
                    neighbour.setParent(currentNode);
                    neighbours.add(neighbour);
                }


            }


        }
        return neighbours;
    }

    @Override
    public int calculateValue(agents.State state) {
        return calculateNumberOfInversions(state);
    }

    @Override
    public void setInitialState(int numberOfColors) {
        State start= new State();
        int [] graph = new int[numberOfNodes];
//        // TODO: 6/13/18
        graph[0] =0;
        graph[1] =1;
        graph[2] =0;
        graph[3] =0;

        start.setGraph(graph);
        this.initialState = start;
    }

    public State getFinalState() {
        return finalState;
    }

    public void setFinalState(State finalState) {
        this.finalState = finalState;
    }

    public State getInitialState(){
        if(this.initialState == null)
            setInitialState(numberOfColors);
        return this.initialState;
    }

    public int calculateNumberOfInversions(agents.State state){

        int [] graph = ((State) state).getGraph();

        int numberOfInversions = 0;
        for (int i=0; i < numberOfNodes; i++){
            for (int j=0; j< numberOfNodes ; j++){


                if(i != j && adjMatix[i][j] == 1 && ((State) state).getGraph()[i] == ((State) state).getGraph()[j])
                    numberOfInversions ++;
            }


        }

        return numberOfInversions;

    }


    public class State implements agents.State {

        private int[] graph;

        @Override
        public String to_String() {
            String str = "";
            for (int i =0 ; i<numberOfNodes ; i++)
                str += graph[i] + " ";

            return str;

        }

        public int[] getGraph() {
            return graph;
        }

        public void setGraph(int[] graph) {
            this.graph = graph;
        }

        @Override
        public boolean equals(Object obj) {
            for (int i = 0; i < graph.length; i++) {
                if (graph[i] != ((State) obj).getGraph()[i])
                    return false;
            }
            return true;
        }
    }
}
