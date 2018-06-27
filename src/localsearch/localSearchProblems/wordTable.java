package localsearch.localSearchProblems;

import agents.State;
import localsearch.LocalNode;
import tree.Node;

import java.util.ArrayList;
import java.util.Collections;
import java.util.PriorityQueue;

/**
 * Created by sina on 6/13/18.
 */
public class wordTable implements Problem {


    ArrayList<String> words = new ArrayList<>();
    State initialState;
    State finalState;
    int numberOfRows, numberOfCols;


    public wordTable(ArrayList<String> words,int r , int c) {
        this.words = words;
        this.numberOfCols = c;
        this.numberOfRows = r;
    }

    @Override
    public PriorityQueue<LocalNode> getNeighbours(LocalNode currentNode) {
        State currentState = (State) currentNode.getState();
        PriorityQueue<LocalNode> result = new PriorityQueue<>(1,Collections.reverseOrder());

        for (int i = 0 ; i < numberOfRows ; i++){
            for (int j = 0 ; j < numberOfCols ; j++){

                if ( j < numberOfCols - 1)  // go right
                {
                    char [][] colors = new char [numberOfRows][numberOfCols];
                    for (int k = 0; k < numberOfRows; k++) {
                        colors[k]= (currentState.getCharTable())[k].clone();

                    }
                    colors[i][j] = new Character(currentState.getCharTable()[i][j + 1]);
                    colors[i][j + 1] = new Character(currentState.getCharTable()[i][j]);
                    State newState = new State(colors);
                    LocalNode node = new LocalNode(newState);
                    node.setValue(calculateValue(newState) );
                    if(!result.contains(node))

                    result.add(node);
                }
                if ( j  > 0 ) {

                    char [][] colors = new char [numberOfRows][numberOfCols];
                    for (int k = 0; k < numberOfRows; k++) {
                        colors[k]= (currentState.getCharTable())[k].clone();

                    }

                    colors[i][j] = new Character(currentState.getCharTable()[i][j - 1]);
                    colors[i][j - 1] = new Character(currentState.getCharTable()[i][j]);
                    State newState = new State(colors);
                    LocalNode node = new LocalNode(newState);
                    node.setValue(calculateValue(newState) );
                    if(!result.contains(node))

                        result.add(node);

//

                }

                if ( i < numberOfRows - 1) {

                    char [][] colors = new char [numberOfRows][numberOfCols];
                    for (int k = 0; k < numberOfRows; k++) {
                        colors[k]= (currentState.getCharTable())[k].clone();

                    }

                    colors[i][j] = new Character(currentState.getCharTable()[i + 1][j]);
                    colors[i + 1][j] = new Character(currentState.getCharTable()[i][j]);
                    State newState = new State(colors);
                    LocalNode node = new LocalNode(newState);
                    node.setValue(calculateValue(newState) );
                    if(!result.contains(node))

                        result.add(node);


//
                }

                if ( i > 0 ){

                    char [][] colors = new char [numberOfRows][numberOfCols];
                    for (int k = 0; k < numberOfRows; k++) {
                        colors[k]= (currentState.getCharTable())[k].clone();

                    }
                    colors[i][j] = new Character(currentState.getCharTable()[i][j]);
                    colors[i - 1][j] = new Character(currentState.getCharTable()[i - 1 ][j]);
                    State newState = new State(colors);
                    LocalNode node = new LocalNode(newState);
                    node.setValue(calculateValue(newState)  ) ;
                    if(!result.contains(node))
                    result.add(node);

                }

            }
        }

        return result;
    }

    private boolean findWord(String goalWord, int r, int c , String currentResult, char [][] table){

      currentResult = currentResult.concat(table[r][c]+"");

        if ( goalWord.compareTo(currentResult) == 0 )
            return true;
        if (goalWord.contains(currentResult) == false)
            return false;


        boolean right = false ,left = false ,down = false,up  = false;
        //        go right
        if ( c < numberOfCols - 1)
            right = findWord(goalWord, r , c + 1, currentResult, table);
        if ( c > 0 ) // left
            left  = findWord(goalWord, r , c - 1, currentResult, table);
        if (r > 0)
            up  = findWord(goalWord, r -1 , c , currentResult, table);
        if( r < numberOfRows - 1)
            down  = findWord(goalWord, r + 1, c , currentResult, table);

        return right | left | up | down;
    }

    @Override
    public int calculateValue(agents.State state) {
        int counter = 0;

        State currentState = (State) state;
        for (String word : words)
            for (int i = 0 ; i < numberOfRows ; i++){
                for (int j = 0 ; j < numberOfCols ; j ++){
                    char ch = currentState.getCharTable()[i][j];
                    boolean find = findWord(word, i ,j, "",currentState.getCharTable());
                    if ( find){
                        counter ++;

                    }


                }
            }

        return counter;

    }

    @Override
    public void setInitialState() {
//        TODO : set initial state

        char [][] table = new char[numberOfRows][numberOfCols];

//        table[0][0]='c';
//        table[0][1]='o';
//        table[0][2]='o';
//
//        table[1][0]='a';
//        table[1][1]='t';
//        table[1][2]='l';
//
//        table[2][0]='l';
//        table[2][1]='m';
//        table[2][2]='b';
//
//        table[3][0]='k';
//        table[3][1]='u';
//        table[3][2]='p';

        table[0][0]='a';
        table[0][1]='p';
        table[0][2]='t';

        table[1][0]='m';
        table[1][1]='l';
        table[1][2]='b';

        table[2][0]='k';
        table[2][1]='l';
        table[2][2]='o';

        table[3][0]='u';
        table[3][1]='o';
        table[3][2]='c';



        State start = new State(table);
        System.out.println(calculateValue(start));

        this.initialState = start;



    }

    @Override
    public agents.State getInitialState() {
        if (initialState == null)
            setInitialState();
        return initialState;
    }

    @Override
    public LocalNode getFirstBetterNeighbour(LocalNode currentNode) {
        return null;
    }

    @Override
    public agents.State getFinalState() {
        char[][] table = new char[numberOfRows][numberOfCols];
                table[0][0]='c';
        table[0][1]='o';
        table[0][2]='o';

        table[1][0]='a';
        table[1][1]='t';
        table[1][2]='l';

        table[2][0]='l';
        table[2][1]='m';
        table[2][2]='b';

        table[3][0]='k';
        table[3][1]='u';
        table[3][2]='p';
        finalState = new State(table);


        return finalState;
    }

    @Override
    public ArrayList<Integer> createChromosome(agents.State state) {
        return null;
    }

    @Override
    public ArrayList<Integer> getPossibleChromosomeValues() {
        return null;
    }

    @Override
    public agents.State chromosomeToState(ArrayList<Integer> chromosoe) {
        return null;
    }

    @Override
    public ArrayList<LocalNode> getKStates(int k) {
        return null;
    }

    @Override
    public agents.State mutate(agents.State state) {
        return null;
    }

    @Override
    public agents.State crossOver(agents.State father, agents.State mother) {
        return null;
    }

    public class State implements agents.State {

        char[][] charTable;

        public State(char[][] charTable) {
            this.charTable = charTable;
        }

        public char[][] getCharTable() {
            return charTable;
        }

        public void setCharTable(char[][] charTable) {
            this.charTable = charTable;
        }

        @Override
        public String to_String() {
            String str = "state is:\n";
            for (int i = 0 ; i <numberOfRows ; i++ ){
                for ( int j =0 ; j < numberOfCols ; j++){

                    str += charTable[i][j] + " ";
                }
                str +="\n";
            }

            str += "value is:\t" + calculateValue(this) +"\n";
            return str;
        }

        @Override
        public boolean equals(Object obj) {
            for (int r = 0 ; r < numberOfRows ; r++){
                for (int c = 0 ; c < numberOfCols ; c++){

                    if (Character.toString (charTable[r][c]).compareTo(Character.toString(((State)obj).charTable[r][c] ))  != 0 )
                        return false;

                }
            }
            return true;
        }
    }

}
