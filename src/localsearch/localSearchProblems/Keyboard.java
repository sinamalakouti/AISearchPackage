package localsearch.localSearchProblems;

import agents.State;
import com.sun.org.apache.regexp.internal.RE;
import com.sun.tools.javac.util.List;
import localsearch.LocalNode;

import java.util.*;

/**
 * Created by sina on 6/15/18.
 */
public class Keyboard implements Problem {


    final int A = 0;
    final int B = 1;
    final int C = 2;
    final int D = 3;
    final int E = 4;
    final int F = 5;
    final int G = 6;
    final int H = 7;
    final int I = 8;
    final int J = 9;
    final int K = 10;
    final int L = 11;
    final int M = 12;
    final int N = 13;
    final int O = 14;
    final int P = 15;
    final int Q = 16;
    final int R = 17;
    final int S = 18;
    final int T = 19;
    final int U = 20;
    final int V = 21;
    final int W = 22;
    final int X = 23;
    final int Y = 24;
    final int Z = 25;


    private static final String[] MOST_USED = new String[]{
            "E", "T", "A", "I", "N", "O", "S", "H", "R"
    };

    private static final String[] KEEP_FAR = new String[]{
            "TH", "ER", "ON", "AN", "RE", "HE", "IN", "ED"
    };

    @Override
    public PriorityQueue<LocalNode> getNeighbours(LocalNode currentNode) {
        return null;
    }

    private int convertCharToInt(String word) {
        switch (word.toUpperCase()) {
            case "E":
                return E;
            case "T":
                return T;
            case "A":
                return A;
            case "I":
                return I;
            case "N":
                return N;
            case "O":
                return O;
            case "S":
                return S;
            case "H":
                return H;
            case "R":
                return R;
            case "D":
                return D;

        }

        return -1;
    }

    private String convertIntToC(int val) {

        switch (val) {
            case A:
                return "A";
            case B:
                return "B";
            case C:
                return "C";
            case D:
                return "D";
            case E:
                return "E";
            case F:
                return "F";
            case G:
                return "G";
            case H:
                return "H";
            case I:
                return "I";
            case J:
                return "J";
            case K:
                return "K";
            case L:
                return "L";
            case M:
                return "M";
            case N:
                return "N";
            case O:
                return "O";
            case P:
                return "P";
            case Q:
                return "Q";
            case R:
                return "R";
            case S:
                return "S";
            case T:
                return "T";
            case U:
                return "U";
            case V:
                return "V";
            case W:
                return "W";
            case X:
                return "X";
            case Y:
                return "Y";
            case Z:
                return "Z";

        }


        System.out.println("fuck");
        return "fuck";

    }

    @Override
    public int calculateValue(agents.State state) {

        state = ((State) state);
        int farCounter = 0;
        int mostUsedInLeft = 0;
        int mostUsedInRight = 0;

        for (String character : MOST_USED) {
            Integer used = convertCharToInt(character);
            if (((State) state).leftSide.contains(used)) {
                mostUsedInLeft++;
            } else if ( ((State) state).rightSide.contains(used)) {
                mostUsedInRight++;
            }else {
                System.out.println("ho no");
                System.exit(0);

            }
        }

        for (String far : KEEP_FAR) {
            String firstch = far.charAt(0)+"";
            String secondch = far.charAt(1) +"";

            Integer first = convertCharToInt(firstch);
            Integer second = convertCharToInt(secondch);


            if (((State) state).rightSide.contains(first)) {
                if (((State) state).leftSide.contains(second)) {
                    farCounter++;
                }
            } else if (((State) state).rightSide.contains(second)) {
                if (((State) state).leftSide.contains(first)) {
                    farCounter++;
                }
            }
        }

        return (9 - Math.abs(mostUsedInLeft - mostUsedInRight)) + farCounter;
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
        State s = (State) state;
        ArrayList<Integer> chromosome = new ArrayList<>();

        for (int i = 0; i < 13; i++)
            chromosome.add(s.leftSide.get(i));

        for (int i = 0; i < 13; i++)
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

        for (int i = 0; i < 13; i++)
            leftKey.add(chromosoe.get(i));

        for (int i = 13; i < 26; i++)
            rightKey.add(chromosoe.get(i));

        State state = new State(leftKey, rightKey);
        return state;
    }


    @Override
    public ArrayList<LocalNode> getKStates(int k) {

        ArrayList<LocalNode> states = new ArrayList<>();




        for (int i = 0; i < k; i++) {
            ArrayList<Integer> list = new ArrayList<>(Arrays.asList(0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23, 24, 25));

            boolean leftFinish = false;
            boolean rightFinish = false;
            int lk = 0;
            int rk = 0;
            ArrayList<Integer> leftKey = new ArrayList<>();
            ArrayList<Integer> rightKey = new ArrayList<>();
            while (lk < 13 || rk < 13) {
                Random rand = new Random();


                if (lk < 13) {
                    int key = 0;
                    if (list.size() != 1)
                        key = rand.nextInt(list.size() );

                    if (!leftKey.contains(list.get(key)) && !rightKey.contains(list.get(key))) {
                        leftKey.add(list.get(key));
                        list.remove(list.indexOf(list.get(key)));
                        lk++;
                    }

                } else {
                    leftFinish = true;
                }
                if (rk < 13) {

                    int key = 0;
                    if (list.size() != 1)
                        key = rand.nextInt(list.size() );


                    if (!leftKey.contains(list.get(key)) && !rightKey.contains(list.get(key))) {
                        rightKey.add(list.get(key));
                        list.remove(list.indexOf(list.get(key)));
                        rk++;
                    }


                } else
                    rightFinish = true;


            }
            leftFinish = true;
            rightFinish = true;
            if (leftFinish && rightFinish) {

                State state = new State(leftKey, rightKey);
                LocalNode node = new LocalNode(state);
                node.setValue(calculateValue(state));
//                System.out.println(state.to_String());
                if ( !states.contains(node))
                states.add(node);
                else
                {
                    i--;
                    continue;
                }
                int s = 0 ;
                for(int j =0 ; j < 13 ; j++)
                {
                    s += (state.leftSide.get(j));
                    s += (state.rightSide.get(j));
                }
//                System.out.println("sum is \t "+ s);
                if ( s != 325)
                {
                    System.out.println("fuck up");
                    System.exit(0);
                }
            }
        }

        return states;

    }

    @Override
    public agents.State mutate(agents.State state) {

        state = (State)state;
        Random random = new Random();
        ArrayList<Integer> newLeft = new ArrayList<>();
        ArrayList<Integer> newRight = new ArrayList<>();

        newLeft.addAll(((State) state).leftSide);
        newRight.addAll(((State) state).rightSide);



        int idx_left = random.nextInt(((State) state).leftSide.size() );
        int idx_right = random.nextInt(((State) state).rightSide.size() );

        int temp = newLeft.get(idx_left);
        newLeft.set(idx_left,newRight.get(idx_right));
        newRight.set(idx_right,temp);



        State state1  = new State(newLeft,newRight);

        if ( calculateValue(state ) >  calculateValue(state1))
            return state;



        return state1;

    }

    @Override
    public agents.State crossOver(agents.State father1, agents.State mother1) {

        mother1 = (State) mother1;
        father1 = (State) father1;
        Random rand=  new Random();
        int numberOfChanges = rand.nextInt(26);
        numberOfChanges+=1;
        boolean selected[] = new boolean[26];
        ArrayList<Integer> changes = new ArrayList<>();
        for (int i  =0 ; i < numberOfChanges ; i++)
        {


            int chosen = rand.nextInt(26);
            if (! selected[chosen]) {
                selected[chosen] = true;
                if (chosen >= 13)
                    changes.add(((State) mother1).rightSide.get(chosen - 13));
                else
                    changes.add(((State) mother1).leftSide.get(chosen));
            }else
                i--;



        }

        ArrayList<Integer> fatherLeftIndexes = new ArrayList<>();
        ArrayList<Integer> fatherRightIndexes = new ArrayList<Integer>();

        for (Integer c : changes ){


            int leftIndex = ((State) father1).leftSide.indexOf(c);
            if ( leftIndex != -1) {
                fatherLeftIndexes.add(leftIndex);
            }else {
                int rightIndex = ((State) father1).rightSide.indexOf(c);
                if(rightIndex != -1){
                    fatherRightIndexes.add(rightIndex);
                }else
                    System.out.println("fucked up");
            }

        }


ArrayList<Integer> l = new ArrayList<>();
        l.addAll(((State) mother1).leftSide);
        ArrayList<Integer> r = new ArrayList<>();
        r.addAll(((State) mother1).rightSide);
//
State mother = new State(l,r);
//
//        l = new ArrayList<>();
//        l.addAll(((State) father1).leftSide);
//
//        r = new ArrayList<>();
//        r.addAll(((State) father1).rightSide);
//
//        State father = new State(l,r);

State father = (State) father1;

        for ( int c : changes){
//            System.out.println(counter);
//            counter++;

            boolean motherLeftChanging = true;
            boolean fatherLeftChaning = true;
            int motherIndex = ((State) mother).leftSide.indexOf(c);
            if ( motherIndex == -1) {
                motherIndex = ((State) mother).rightSide.indexOf(c);
                motherLeftChanging =false;
            }

            int fatherIndex = ((State) father).leftSide.indexOf(c);
            if (fatherIndex == -1) {
                fatherIndex = ((State) father).rightSide.indexOf(c);
                fatherLeftChaning = false;
            }

            if ( motherLeftChanging){

                if (fatherLeftChaning){

//                    System.out.println(mother.to_String());

                    int temp = ((State) mother).leftSide.get(motherIndex);
                    ((State) mother).leftSide.set(motherIndex,((State) mother).leftSide.get(fatherIndex));
                    ((State) mother).leftSide.set(fatherIndex,temp);

//                    State s = new State(newLeft,newRight);
//
//                    System.out.println("at : 1");
//                    System.out.println(mother.to_String());
//                    int b = calculateValue(mother);
//

                }else{

//                    System.out.println(mother.to_String());
                    int temp = ((State) mother).leftSide.get(motherIndex);
                    ((State) mother).leftSide.set(motherIndex,((State) mother).rightSide.get(fatherIndex));
                    ((State) mother).rightSide.set(fatherIndex,temp);

//                    State s = new State(newLeft,newRight);

//                    System.out.println("at : 2");
//                    System.out.println(mother.to_String());
//                    int b = calculateValue(mother);


                }



            }else {

                if ( fatherLeftChaning){

//                    System.out.println("mother:");
//                    System.out.println(mother.to_String());
//                    System.out.println("son : ");
//                    System.out.println(new State (newLeft,newRight).to_String());
                    int temp = ((State) mother).rightSide.get(motherIndex);
                    ((State) mother).rightSide.set(motherIndex,((State) mother).leftSide.get(fatherIndex));
                    ((State) mother).leftSide.set(fatherIndex,temp);

//                    System.out.println("changing from : " + motherIndex + " to "+ fatherIndex);
//                    System.out.println("at : 3");
//                    System.out.println(mother.to_String());

//                    int b = calculateValue(mother);


                }else

                {
//                    System.out.println(mother.to_String());
                    int temp = ((State) mother).rightSide.get(motherIndex);
                    ((State) mother).rightSide.set(motherIndex,((State) mother).rightSide.get(fatherIndex));
                    ((State) mother).rightSide.set(fatherIndex,temp);

//                    State s = new State(newLeft,newRight);
//                    System.out.println("at : 4");
//                    System.out.println(mother.to_String());
//                    int b = calculateValue(mother);


                }



            }




        }

        State child = new State(((State) mother).leftSide,((State) mother).rightSide);
//        System.out.println("cross over");
//        int a = calculateValue(child);

        return child;

    }

    public class State implements agents.State {


        public ArrayList<Integer> leftSide = new ArrayList<Integer>();
        public ArrayList<Integer> rightSide = new ArrayList<Integer>();

        public State(ArrayList<Integer> leftSide, ArrayList<Integer> rightSide) {

            this.leftSide = leftSide;
            this.rightSide = rightSide;
        }


        @Override
        public String to_String() {
            String str = "left is:\t";
            for (int i = 0; i < 13; i++)
                str += (convertIntToC(leftSide.get(i)) + " ");
            str += "\nright is:\t";
            for (int i = 0; i < 13; i++)
                str += (convertIntToC(rightSide.get(i)) + " ");
            str += "\n";

            str += calculateValue(this);
            return str;

        }

        @Override
        public boolean equals(Object obj) {
            State otherState = ((State) obj);
            for (int i = 0; i < 13; i++) {

                if ( !leftSide.get(i).equals(otherState.leftSide.get(i))  ||  !rightSide.get(i).equals(otherState.rightSide.get(i)) )
                    return false;
            }
            return true;
        }
    }
}
