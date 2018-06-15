package localsearch.localSearchProblems;

import agents.State;
import com.sun.org.apache.regexp.internal.RE;
import localsearch.LocalNode;

import java.util.ArrayList;
import java.util.PriorityQueue;
import java.util.Random;

/**
 * Created by sina on 6/15/18.
 */
public class Keyboard implements  Problem {


    int A = 0 ;
    int B = 1 ;
    int C = 2 ;
    int D = 3 ;
    int E = 4 ;
    int F = 5 ;
    int G = 6 ;
    int H = 7 ;
    int I = 8 ;
    int J = 9 ;
    int K = 10;
    int L = 11 ;
    int M = 12 ;
    int N = 13 ;
    int O = 14 ;
    int P = 15 ;
    int Q = 16 ;
    int R = 17 ;
    int S = 18 ;
    int T = 19 ;
    int U = 20 ;
    int V = 21 ;
    int W = 22 ;
    int X = 23 ;
    int Y = 24 ;
    int Z = 25 ;



    @Override
    public PriorityQueue<LocalNode> getNeighbours(LocalNode currentNode) {
        return null;
    }

    @Override
    public int calculateValue(agents.State state) {

//       todo :  part a:

//        todo: part b:
        return 0;
    }

    @Override
    public void setInitialState() {
//        TODO :
    }

    @Override
    public agents.State getInitialState() {
        return getKStates(1).get(0).getState();
    }

    @Override
    public LocalNode getFirstBetterNeighbour(LocalNode currentNode) {
        return null;
    }

    @Override
    public agents.State getFinalState() {
        return null;
    }

    @Override
    public ArrayList<Integer> createChromosome(agents.State state) {
        State  s = (State) state;
        ArrayList<Integer> chromosome = new ArrayList<>();

        for ( int i = 0 ; i< 13 ; i++)
            chromosome.add(s.leftSide.get(i));

        for ( int i = 0 ; i< 13 ; i++)
            chromosome.add(s.rightSide.get(i));

        return chromosome;

    }

    @Override
    public ArrayList<Integer> getPossibleChromosomeValues() {

        ArrayList<Integer> keys = new ArrayList<>();
        keys.add(A);
        keys.add(B);
        keys.add(C);
        keys.add(D);
        keys.add(E);
        keys.add(F);
        keys.add(G);
        keys.add(H);
        keys.add(I);
        keys.add(J);
        keys.add(K);
        keys.add(L);
        keys.add(M);
        keys.add(N);
        keys.add(O);
        keys.add(P);
        keys.add(Q);
        keys.add(R);
        keys.add(S);
        keys.add(T);
        keys.add(U);
        keys.add(V);
        keys.add(W);
        keys.add(X);
        keys.add(Y);
        keys.add(Z);
        return keys;

    }

    @Override
    public agents.State chromosomeToState(ArrayList<Integer> chromosoe) {
        ArrayList<Integer> leftKey = new ArrayList<>();
        ArrayList<Integer> rightKey = new ArrayList<>();
        for (int i = 0 ; i < 13 ; i ++)
            leftKey.add(chromosoe.get(i));

        for (int i = 13 ; i < 26 ; i ++)
            rightKey.add(chromosoe.get(i));

        State state = new State(leftKey,rightKey);
        return state;
    }


    @Override
    public ArrayList<LocalNode> getKStates(int k) {

        ArrayList<LocalNode> states = new ArrayList<>();

        int lk =0;
        int rk =0;
        ArrayList<Integer> leftKey = new ArrayList<>();
        ArrayList<Integer> rightKey = new ArrayList<>();
        boolean leftFinish = false;
        boolean rightFinish = false;

        while ( lk < 13 || rk < 13) {
            Random rand = new Random();


            if (lk < 13 ){
                int key = rand.nextInt(25);

            if ( ! leftKey.contains(key) && !rightKey.contains(key))
                leftKey.add(key);
                lk ++;
            }else {
                leftFinish = true;
            }
            if(rk < 13){
                int key = rand.nextInt(25);
                if ( ! leftKey.contains(key) && !rightKey.contains(key))
                    rightKey.add(key);

                rk++;
            }else
                rightFinish = true;
            if ( leftFinish && rightFinish)
            {
                State state = new State(leftKey,rightKey);
                LocalNode node = new LocalNode(state);
                node.setValue(calculateValue(state));
                states.add(node);

            }
        }


        return states;

    }

    public class State implements agents.State{


        public ArrayList<Integer> leftSide = new ArrayList<Integer>();
        public ArrayList<Integer> rightSide = new ArrayList<Integer>();

        public State(ArrayList<Integer>leftSide, ArrayList<Integer> rightSide) {

            this.leftSide = leftSide;
            this.rightSide = rightSide;
        }


        @Override
        public String to_String() {
            return null;
        }

        @Override
        public boolean equals(Object obj) {
            State otherState = ((State) obj);
            for (int i = 0 ; i < 13 ; i ++){

                if ( leftSide.get(i) != otherState.leftSide.get(i) || rightSide.get(i) != otherState.rightSide.get(i))
                    return false;
            }
            return true;
        }
    }
}
