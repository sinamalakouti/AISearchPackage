package problem;

import agents.Action;
import agents.State;

import java.util.ArrayList;
import java.util.Objects;

/**
 * Created by sina on 6/6/18.
 */
public class Problem1 implements Problem {


    public int [][] board ;
    public  State [] [] states ;

    public Problem1(int n , int m, int nWalls) {
        this.board = new int [n][m];
        this.states = new State[n][m];
        initStates(n,m);


    }

    private void initStates(int n , int m ){

        for (int i = 0 ; i < n ; i++)
            for ( int j = 0 ; j < m ; j++)
                states[i][j] = new State(i,j);

    }
    public void initActions(int [] x_src , int [ ] y_src , int [] x_dest , int [ ] y_dest, int nWalls){



        for ( int row = 0 ; row < states.length ; row ++)
            for (int col =0 ; col < states[row].length ; col ++) {

              State srcState = states[row][col];

                for (int action = 0; action <= 4; action++) {

                    boolean flag = checkWall(x_src,y_src,x_dest,y_dest,nWalls,action,srcState);
                    if(flag == true)
                        srcState.insertAction(action);
                }

            }

    }



    private boolean checkWall(int [] x_src , int [ ] y_src , int [] x_dest , int [ ] y_dest, int nWalls , int action, State srcState){



            if(isValidAction(srcState, action)){

                for (int i = 0 ; i < nWalls ; i++){

                    if (srcState.row == x_src[i] && srcState.col == y_src[i] ){

                        State desState = move(srcState , action);

                        if(desState.row == x_dest[i] && desState.col == y_dest[i])
                            return  false;
                        else return true;

                    }else
                        return  true;




            }
        }else return false;


        return false;
    }

    private boolean isValidAction ( State src , int action ){

        int a = action ;

        switch (action){
            case  0 : // up
                if ( src.row >= 1)
                    return true;
                break;
            case 2 :  // right

                 if (src.col < states[0].length - 1)
                     return  true;
                break;
            case 1 : // down
                 if ( src.row < states.length -1 )
                     return true;
                break;
            case 3 : // left
                if ( src.col >= 1)
                    return true;
                break;

        }
        return false;


    }
    @Override
    public ArrayList<agents.Action> actionsFor(agents.State state) {
         state =((State) state);

        return  ((State) state).actions;
    }

    @Override
    public ArrayList<agents.Action> reverseActionsFor(agents.State state) {

        ArrayList<agents.Action> result = new ArrayList<>();

        for (int row = 0 ; row < states.length ; row ++){

            for (int col = 0 ; col < states[0].length ; col ++){

                State s = states[row][col];
                for (agents.Action action : s.actions){

                    if ( state.equals(move(s,action)))
                        result.add(action);
                }




            }

        }

        return result;

    }

    @Override
    public Problem.State move(agents.State state, agents.Action action) {

        State src = ((State) state);
        int a = ((Action) action).getMove();
        if( isValidAction((State)state, action.getMove())) {
            switch (a) {
                case 0: // up
                    return states[src.row - 1][src.col];
                case 2:  // right
                    return states[src.row][src.col + 1];
                case 1: // down
                    return states[src.row + 1][src.col];
                case 3: // left
                    return states[src.row][src.col -1];


            }
        }
        return null;
    }



    public State move(agents.State state, int action) {

        State src = ((State) state);
        switch (action){
            case  0 : // up
                return states[src.row - 1 ][src.col  ];
            case 2 :  // right
                return  states[src.row][src.col + 1];
            case 1 : // down
                return states[src.row + 1][src.col];
            case 3 : // left
                return  states[src.row][src.col -1];

        }
        return null;
    }

    @Override
    public int getHeuristic(Problem.State source, Problem.State dest ){

        double distance = Math.pow(source.getRow() - dest.getRow(), 2)  +  Math.pow(source.getCol() - dest.getCol(),2);


        return (int) Math.floor(Math.sqrt(distance));

    }





    @Override
    public Problem.State reverseMove(agents.State state, agents.Action action) {

        for (int row = 0 ; row < states.length ; row ++)
            for (int col =0 ; col < states[0].length ; col ++)
            {
                State s = states[row][col];

                agents.State tmpState  = move(s,action);

                if (tmpState != null && tmpState.equals(state))
                    return  s;

            }
        return  null;
    }

    @Override
    public int stepCost(agents.State src, agents.State dest, agents.Action action) {
        return 1;
    }

    @Override
    public boolean isGoal(agents.State state) {

        if (((State) state).row == states.length - 1 && ((State) state).col == states[0].length - 1)
            return  true;
        else
            return false;
    }

    @Override
    public State getInitialState() {
        return states[0][0];
    }

    @Override
    public ArrayList<agents.State> getFinalState() {

        ArrayList <agents.State> arrayList = new ArrayList<>();
        arrayList.add( states[states.length - 1][states[0].length-1]);
        return arrayList;
    }

    @Override
    public String parseAction(Problem.State src, Problem.State dest) {
        // 0 -> up, 1-> down, 2 -> right ,    3 -> left

        if ( src.getRow() > dest.getRow() )
            return "U";
        else if (src.getRow() < dest.getRow())
            return "D";
        else if (src.getCol() > dest.getCol())
            return "L";
        else return "R";
    }

    public class State extends Problem.State {


        public State(int row , int col ) {
            this.row = row;
            this.col = col;
        }


        public void insertAction( int action){
            if(!actions.contains(action))
                actions.add(new Action(action));
        }

        public int getRow() {
            return row;
        }

        public void setRow(int row) {
            this.row = row;
        }

        public int getCol() {
            return col;
        }

        public void setCol(int col) {
            this.col = col;
        }

        @Override
        public boolean equals(Object s) {
            s = (agents.State)s;
            if (this.row == ((State) s).row && this.col == ((State) s).col)
                return true;
            return false;

        }

        @Override
        public boolean contains(agents.State s) {

//TODO
            return false;
        }

        @Override
        public String to_String() {
            return "( "+ this.row + ", " + this.col+" )";
        }
    }


    public class Action extends agents.Action {

         // 0 -> up, 1-> down, 2 -> right ,    3 -> left
//        public State source;
//        public State dest;

        public Action(int move) {
            this.move = move;
//            this.source = source;
//            this.dest = dest;
        }

        @Override
        public boolean equals(Object obj) {
            if (((Action)obj).getMove() == this.move)
                return true;
            return false;
        }

    }
}
