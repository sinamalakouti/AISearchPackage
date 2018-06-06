package problem;

import agents.Action;
import agents.State;

import java.util.ArrayList;

/**
 * Created by sina on 6/4/18.
 */


public interface Problem {
    ArrayList<Action> actionsFor(State state);
    State move(State state, Action action);
    int stepCost(State src, State dest, Action action);
    boolean isGoal(State state);
}