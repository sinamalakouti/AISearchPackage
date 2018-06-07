package problem;

import agents.Action;
import agents.State;

import java.util.ArrayList;

/**
 * Created by sina on 6/4/18.
 */


public interface Problem {
    ArrayList<Action> actionsFor(agents.State state);
    ArrayList<Action> reverseActionsFor(agents.State state);

    agents.State move(agents.State state, Action action);
    agents.State reverseMove(agents.State state, Action action);
    int stepCost(agents.State src, agents.State dest, Action action);
    boolean isGoal(agents.State state);
    agents.State getInitialState();
    agents.State getFinalState();

    public String parseAction(State src , State dest);
    public int getHeuristic( State source , State dest);


    public abstract class State implements agents.State{

        protected int row ,col;
        protected  ArrayList<agents.Action> actions = new ArrayList<>();


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
    }

}