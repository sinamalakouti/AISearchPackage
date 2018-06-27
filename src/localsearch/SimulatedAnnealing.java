package localsearch;

import agents.Agent;
import agents.State;
import controller.MainProblem;
import controller.MainSolution;
import localsearch.hillclimbing.Solution;
import localsearch.localSearchProblems.Problem;

import java.util.PriorityQueue;
import java.util.Random;
import java.util.regex.Matcher;

/**
 * Created by sina on 6/13/18.
 */
public class SimulatedAnnealing extends LocalSearchAlgorithm implements Agent {


    double T_init = new Double("1");


    @Override
    public MainSolution solve(MainProblem mainProblem, State start) {
        LocalNode prev = null;
        Problem problem = ((Problem) mainProblem);
        Solution sol = new Solution();
        LocalNode currentNode = new LocalNode(start);
        currentNode.setValue(problem.calculateValue(start) );



        PriorityQueue<LocalNode> neighbours = null;

            for (int t = 1 ; t < Integer.MAX_VALUE  ; t ++){
//                cooling schedule
                double T = schedule(t,2);
                if (T == 0 || currentNode.getState().equals(problem.getFinalState()))
                {
                    sol.finalState = currentNode.getState();
//                    sol.setBestPath(currentNode);
                    return sol;
                }



//                pick random




                    neighbours = problem.getNeighbours(currentNode);


                if ( prev != null)
                {
                    if ( currentNode.equals(currentNode)){
                        sol.numberOfVisitedNodes +=neighbours.size();
                        sol.numberOfExpanedNodes++;
                    }
                }
                    prev = currentNode;

//                }

                Object [] neighArray =  neighbours.toArray();
                Random random = new Random();
                prev = currentNode;

                int rand1 =random.nextInt(neighArray.length -1);

                LocalNode neighbour = (LocalNode) neighArray[rand1];

//                neighbours.remove(neighbour);

                double deltaE = neighbour.getValue()  - currentNode.getValue();


                if ( deltaE > 0 ) {

                    LocalNode parent = new LocalNode(currentNode.getState());
                    parent.setParent(currentNode.getParent());
                    neighbour.setParent(parent);

                    currentNode = neighbour;
                }
                else if(deltaE <= 0)
                {
                    double p = Math.exp(deltaE / T);
                    double rand = Math.random();

                    if(rand < p ) {

                        LocalNode parent = new LocalNode(currentNode.getState());
                        parent.setParent(currentNode.getParent());
                        neighbour.setParent(parent);
                        currentNode = neighbour;
                    }

                }



            }


        sol.finalState = currentNode.getState();
//        sol.setBestPath(currentNode);
        return sol;




    }


    private double schedule( int t , int  way){

        double T = 0;
        switch (way){

            case 0:
                double alpha = new Double("0.0001");



                double b = T_init - alpha;


                T = this.T_init - alpha * t;

                break;
            case 1:
                alpha = 0.95d;
                 T = T_init * Math.pow(alpha,t);

                break;
            case 2:
                alpha = 10d;

                T = T_init / ( new Double("1") + alpha * Math.log((1 + t)));
//                System.out.println(T);
                if(T < 0.01d)
                T=0;
                break;


        }


        if ( T < 0 )
            T = 0;
        return  T;

    }
}
