package problem;

import sun.management.Agent;

import java.util.ArrayList;

/**
 * Created by sina on 6/4/18.
 */


public interface Problem<State, Action> {
    ArrayList<Action> actionsFor(State state);
    State move(State state, Action action);
    boolean isGoal(State state);
}