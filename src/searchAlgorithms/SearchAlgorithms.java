package searchAlgorithms;

import agents.State;
import problem.Problem;
import problem.Solution;
import tree.Node;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

/**
 * Created by sina on 6/4/18.
 */
public abstract  class SearchAlgorithms {


    protected   Solution solution;

    public abstract Solution solve (Problem problem, Problem.State start);

    public Solution getSolution() {
        return solution;
    }

    public void setSolution(Solution solution) {
        this.solution = solution;
    }
}
