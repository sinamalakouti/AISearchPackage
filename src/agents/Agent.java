package agents;

import controller.MainProblem;
import controller.MainSolution;
import problem.Problem;
import problem.Solution;
import sun.applet.Main;

/**
 * Created by sina on 6/4/18.
 */
public interface Agent {

    public MainSolution solve (MainProblem problem , State start);
}
