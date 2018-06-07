package problem;

import agents.Action;
import agents.State;

import java.util.ArrayList;

/**
 * Created by sina on 6/7/18.
 */
public class Problem2 implements Problem {


    public enum Moves {UP, DOWN, LEFT, RIGHT}

    ;
    final int UP = 0;
    final int LEFT = 1;
    final int RIGHT = 2;
    final int DOWN = 3;
    agents.State initialState;
//    up = 0     left = 2
//    down = 1    right = 3

    @Override
    public ArrayList<agents.Action> actionsFor(agents.State state) {

        ArrayList<agents.Action> legalActions = new ArrayList<agents.Action>();
        int[] board = ((State) state).getBoard();
        int blankPosition = 0;
        while (board[blankPosition] != 0) blankPosition++;
        if (blankPosition > 2) legalActions.add(new Action(UP));
        if (blankPosition % 3 != 0) legalActions.add(new Action(LEFT));
        if (blankPosition % 3 != 2) legalActions.add(new Action(RIGHT));
        if (blankPosition < 6) legalActions.add(new Action(DOWN));
        return legalActions;
    }

    @Override
    public ArrayList<agents.Action> reverseActionsFor(agents.State state) {

        return actionsFor(state);
    }

    @Override
    public agents.State move(agents.State state, agents.Action action) {
        int offset = 0;
        switch (action.getMove()) {
            case UP:
                offset = -3;
                break;
            case DOWN:
                offset = 3;
                break;
            case RIGHT:
                offset = 1;
                break;
            case LEFT:
                offset = -1;
                break;
        }

        int blankPosition = 0;
        state = (State) state;
        while (((State) state).getBoard()[blankPosition] != 0) blankPosition++;
        int newBlankPosition = blankPosition + offset;
//        Board result = new Board(board.tiles);
        int[] result = ((State) state).getBoard().clone();

        result[blankPosition] = ((State) state).getBoard()[newBlankPosition];
        result[newBlankPosition] = 0;

        State nextState = new State(result);
        return nextState;
    }

    public void setInitialState(int[] board) {
        initialState = new State(board);
    }

    @Override
    public agents.State reverseMove(agents.State state, agents.Action action) {


        return move(state,action);
    }

    @Override
    public int stepCost(agents.State src, agents.State dest, agents.Action action) {
        return 1;
    }

    @Override
    public boolean isGoal(agents.State state)
    {
        return state.equals(getFinalState().get(0));
    }

    @Override
    public agents.State getInitialState() {

        return initialState;
    }

    @Override
    public ArrayList <agents.State> getFinalState() {

        ArrayList<agents.State> arrayList = new ArrayList<>();
        int[] board = {0, 1, 2, 3, 4, 5, 6, 7, 8};

        State finalState = new State(board);
        arrayList.add(finalState);
        return arrayList;
    }

    @Override
    public String parseAction(Problem.State src, Problem.State dest) {
        {

            ArrayList<agents.Action> actions = actionsFor(src);
            int act = 0;
            for (int i = 0; i < actions.size(); i++) {
                agents.State next = move(src, actions.get(i));
                if (dest.equals(next))
                    act = actions.get(i).getMove();
            }

            switch (act) {

                case UP:
                    return "u";

                case LEFT:
                    return "l";

                case RIGHT:
                    return "r";

                case DOWN:
                    return "d";


            }

            return null;

        }
    }

    @Override
    public int getHeuristic(Problem.State source, Problem.State dest) {
        double distance = Math.pow(source.getRow() - dest.getRow(), 2) + Math.pow(source.getCol() - dest.getCol(), 2);


        return (int) Math.floor(Math.sqrt(distance));
    }


    public boolean isSolvable() {
        boolean solvable = true;
        int[] board = ((State) this.getInitialState()).getBoard();
        for (int i = 0; i < board.length; i++) {
            if (board[i] == 0) continue;
            for (int j = i + 1; j < board.length; j++) {
                if (board[j] == 0) continue;
                if (board[i] > board[j]) solvable = !solvable;
            }
        }
        return solvable;
    }


    public class State extends Problem.State {

        private int[] board = new int[9];


        public State(int[] board) {
            this.board = board;
            int index = 0;
            for (int i = 0; i < 3; i++)
                for (int j = 0; j < 3; j++) {

                    if (board[index] != 0)
                        index++;
                    else {
                        row = i;
                        col = j;

                    }
                }
        }

        public int[] getBoard() {
            return board;
        }

        public void setBoard(int[] board) {
            this.board = board;
        }

        @Override
        public boolean equals(Object s) {
            s = (agents.State)s;
            for (int i = 0; i < board.length; i++)
                if (this.board[i] != ((State) s).getBoard()[i]) {
                    return false;

                }


            return true;
        }

        @Override
        public boolean contains(agents.State s) {
            System.out.println("dkajsdf;fkasd;");
            return false;
        }

        @Override
        public String to_String() {
            String str = "";

            for (int i = 0; i < 9; i++) {
                str += board[i] + " ";
                if (i == 2 || i == 5)
                    str += "\n";
            }

            return str;
        }
    }

    public class Action extends agents.Action {

        public Action(int move) {
            this.move = move;
        }

        @Override
        public boolean equals(Object obj) {
            if (this.move == ((Action) obj).move)
                return true;
            return false;
        }
    }


    public State createState(int[] board) {

        return new State(board);
    }
}
