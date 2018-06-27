package localsearch.hillclimbing;

import agents.Agent;
import agents.State;
import controller.MainProblem;
import controller.MainSolution;
import localsearch.LocalSearchAlgorithm;
import localsearch.localSearchProblems.Problem;

/**
 * Created by sina on 6/13/18.
 */
public class RandomStartHC extends LocalSearchAlgorithm implements Agent{


    @Override
    public MainSolution solve(MainProblem problem, State start) {


        MainSolution sol = null;

        while ( sol == null ||  ((Solution)sol).value != 4 ){

            HillClimbing hc = new HillClimbing();
            sol = hc.solve(problem,((Problem)problem).getInitialState());


        }







        return sol;
    }
}
