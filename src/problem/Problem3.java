package problem;

import java.util.ArrayList;

/**
 * Created by sina on 6/7/18.
 */
public class Problem3 implements Problem {

    //    colors:
    final private int RED = 0;
    final private int GREEN = 1;
    final private int BLUE = 2;
    final private int WHITE = 3;
    final private int ORANGE = 4;
    final private int YELLOW = 5;

//    moves :

    final private int T = 0;     // top , clockwise
    final private int TC = 1;  // top , counter clockwise
    final private int F = 2;    //   front , clockwise
    final private int FC = 3;   //  front counter clockwise
    final private int R = 4;    //  right clock wise
    final private int RC = 5;   //   right , counter clockwise


    agents.State initialState;


    @Override
    public ArrayList<agents.Action> actionsFor(agents.State state) {
        ArrayList<agents.Action> actions = new ArrayList<>();

        actions.add(new Action(T));
        actions.add(new Action(TC));
        actions.add(new Action(F));
        actions.add(new Action(FC));
        actions.add(new Action(R));
        actions.add(new Action(RC));
        return actions;

    }

    @Override
    public ArrayList<agents.Action> reverseActionsFor(agents.State state) {
        return null;
    }

    @Override
    public agents.State move(agents.State state, agents.Action action) {
        int move = action.getMove();
        State newState = new State();


        int[] board = ((State) state).board;
        int[] newBoard = board.clone();
        switch (move) {
//TODO:
            case T:

                newBoard[16] = board[4];
                newBoard[17] = board[5];
                newBoard[14] = board[16];
                newBoard[15] = board[17];
                newBoard[21] = board[14];
                newBoard[20] = board[15];
                newBoard[4] = board[20];
                newBoard[5] = board[21];

                newBoard[0] = board[2];
                newBoard[1] = board[0];
                newBoard[2] = board[3];
                newBoard[3] = board[1];


                break;
            case TC:


                newBoard[20] = board[4];
                newBoard[21] = board[5];
                newBoard[5] = board[17];
                newBoard[4] = board[16];
                newBoard[17] = board[14];
                newBoard[16] = board[15];
                newBoard[15] = board[20];
                newBoard[14] = board[21];
                newBoard[0] = board[1];
                newBoard[1] = board[3];
                newBoard[2] = board[0];
                newBoard[3] = board[2];


                break;
            case F:

                newBoard[2] = board[19];
                newBoard[3] = board[17];
                newBoard[20] = board[2];
                newBoard[22] = board[3];
                newBoard[9] = board[20];
                newBoard[8] = board[22];
                newBoard[19] = board[9];
                newBoard[17] = board[8];
                newBoard[4] = board[6];
                newBoard[5] = board[4];
                newBoard[6] = board[7];
                newBoard[7] = board[5];

                break;
            case FC:

                newBoard[2] = board[20];
                newBoard[3] = board[22];
                newBoard[20] = board[9];
                newBoard[22] = board[8];
                newBoard[9] = board[19];
                newBoard[8] = board[17];
                newBoard[19] = board[3];
                newBoard[17] = board[3];
                newBoard[4] = board[5];
                newBoard[5] = board[7];
                newBoard[6] = board[4];
                newBoard[7] = board[6];


                break;
            case R:


                newBoard[1] = board[5];
                newBoard[3] = board[7];
                newBoard[5] = board[9];
                newBoard[7] = board[11];
                newBoard[9] = board[13];
                newBoard[11] = board[15];
                newBoard[13] = board[1];
                newBoard[15] = board[3];
                newBoard[20] = board[21];
                newBoard[21] = board[23];
                newBoard[22] = board[20];
                newBoard[23] = board[22];


                break;
            case RC:

                newBoard[1] = board[13];
                newBoard[3] = board[15];
                newBoard[5] = board[1];
                newBoard[7] = board[3];
                newBoard[9] = board[5];
                newBoard[11] = board[7];
                newBoard[13] = board[9];
                newBoard[15] = board[11];
                newBoard[20] = board[22];
                newBoard[21] = board[20];
                newBoard[22] = board[23];
                newBoard[23] = board[21];


                break;

        }
        newState.setBoard(newBoard);
        return newState;
    }

    @Override
    public agents.State reverseMove(agents.State state, agents.Action action) {
        return null;
//        niazi nis
    }

    @Override
    public int stepCost(agents.State src, agents.State dest, agents.Action action) {
        return 1;
    }

    @Override
    public boolean isGoal(agents.State state) {
        int[] board = ((State) state).board;
        for (int i = 1; i <= 6; i++) {
            int first = board[(i - 1) * 4];
            for (int j = 1; j < 4; j++)
                if (board[(i - 1) * 4 + j] != first)
                    return false;
        }
        return true;
    }

    @Override
    public agents.State getInitialState() {
        return this.initialState;
    }

    public void setInitialState(State state) {
        this.initialState = state;

    }

    public void createInitialState(int[] board) {
        initialState = new State();
        ((State) initialState).setBoard(board);
    }

    @Override
    public ArrayList<agents.State> getFinalState() {
// niazi nis

        State state = new State();
        int[] board = new int[24];

        return null;
    }


    @Override
    public String parseAction(Problem.State src, Problem.State dest) {

        ArrayList<agents.Action> actions = actionsFor(src);
        int act = 0;
        for (int i = 0; i < actions.size(); i++) {
            agents.State next = move(src, actions.get(i));
            if (dest.equals(next))
                act = actions.get(i).getMove();
        }

        switch (act) {

            case T:
                return "T";

            case TC:
                return "TC";

            case R:
                return "R";

            case RC:
                return "RC";

            case F:
                return "F";

            case FC:
                return "FC";


        }

        return null;

    }

    @Override
    public int getHeuristic(Problem.State source, Problem.State dest) {
//        niazi nis
        return 0;
    }

    public int parseInput(char ch) {
        int res = 0;
        switch (ch) {
            case 'y':
                res = YELLOW;
                break;
            case 'r':
                res = RED;
                break;
            case 'g':
                res = GREEN;
                break;
            case 'w':
                res = WHITE;
                break;
            case 'o':
                res = ORANGE;
                break;
            case 'b':
                res = BLUE;
                break;

        }
        return res;

    }

    public class State extends Problem.State {

        private int[] board = new int[24];

        @Override
        public boolean equals(Object s) {
            s = (agents.State) s;
            for (int i = 0; i < board.length; i++)
                if (this.board[i] != ((State) s).board[i])
                    return false;

            return true;
        }

        public int[] getBoard() {
            return board;
        }

        public void setBoard(int[] board) {
            this.board = board;
        }


        @Override
        public String to_String() {
            String str = "";

            for (int i = 0; i < board.length; i++) {
                str += board[i] + " ";

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
            if (this.move == ((Action) obj).getMove())
                return true;
            return false;
        }
    }
}
