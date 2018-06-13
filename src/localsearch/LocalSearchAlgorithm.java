package localsearch;

import problem.Solution;

/**
 * Created by sina on 6/13/18.
 */
public abstract  class LocalSearchAlgorithm {

    protected Solution solution;

    public Solution getSolution() {
        return solution;
    }

    public void setSolution(Solution solution) {
        this.solution = solution;
    }
}
