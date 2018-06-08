package agents;

import problem.Problem;
import problem.Solution;

/**
 * Created by sina on 6/4/18.
 */
public interface Agent {

    public Solution solve (Problem problem, Problem.State start);
}
