package localsearch;

import agents.Agent;
import agents.State;
import controller.MainProblem;
import controller.MainSolution;
import localsearch.LocalNode;
import localsearch.hillclimbing.Solution;
import localsearch.localSearchProblems.Keyboard;
import localsearch.localSearchProblems.Problem;
import org.omg.PortableServer.POA;
import searchAlgorithms.SearchAlgorithms;
import tree.Node;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;
import java.util.Set;

/**
 * Created by sina on 6/15/18.
 */
public class Genetic extends SearchAlgorithms implements Agent {

    int maxPopulation = 20;


    int numberOfIteration = 0;
    double mutationRate = 0.02d;

    public Genetic(int maxPopulation, int numberOfIteration , double mutationRate) {

        this.maxPopulation = maxPopulation;
        this.numberOfIteration = numberOfIteration;
        this.mutationRate = mutationRate;
    }

    @Override
    public MainSolution solve(MainProblem mainProblem, State start) {


        Solution sol = new Solution();
        Problem problem = ((Problem) mainProblem);
        ArrayList<LocalNode> population = new ArrayList<>();
        ArrayList<LocalNode> allStates = problem.getKStates(maxPopulation);

        for (int i = 0; i < maxPopulation; i++) {
            Random random = new Random();
            int idx = 0;

            if (allStates.size() != 1)
                idx = random.nextInt(allStates.size());
            LocalNode chosenNode = allStates.get(idx);
            allStates.remove(idx);
            population.add(chosenNode);
        }


        int counter = 0;

        while (true) {

//            if (counter == numberOfIteration) {
//                sol.finalState = this.getTheBestPerson(population).getState();
//                return sol;
//
//            }

            int[] fn = new int[population.size()];
            for (int i = 0; i < population.size(); i++)
                fn[i] = population.get(i).getValue();



            ArrayList<LocalNode> newPopulation = new ArrayList<>();
            ArrayList<LocalNode> list = createDataset(population,fn);
            for (int i = 0; i < population.size(); i++) {

                LocalNode x = randomSelection(list);
                LocalNode y = randomSelection(list);

                while ( y.equals(x)){
                     y = randomSelection(list);
                }
//



//                }

                LocalNode tempX = new LocalNode( x.getState());
                LocalNode tempy = new LocalNode(y.getState());
                State childState = produce(tempX, tempy, problem);

                childState = mutation(childState, problem, mutationRate);


                LocalNode node = new LocalNode(childState);
                node.setState(childState);
                node.setValue(problem.calculateValue(childState));
                if ( newPopulation.contains(node))
                {
                    boolean m  = newPopulation.contains(node);
                    i--;
                    continue;
                }else {
                    newPopulation.add(node);
                }

            }

            population = (ArrayList<LocalNode>) newPopulation;
//            competencyChecksCount -= 2 * maxPopulation;

            if (counter == numberOfIteration) {

                sol.finalState = this.getTheBestPerson(population).getState();
                return sol;
            }
            counter++;

        }
    }
private int numberOfdiffNodes ( ArrayList<LocalNode> arr){
    int counter = 0 ;
    for  ( int i =0 ; i < arr.size() ; i ++){
        for  ( int j =i+1 ; j < arr.size() ; j ++){

            if ( arr.get(i).equals(arr.get(j)) && i != j)
                counter++;
        }
    }

return counter;
    }

    private LocalNode getTheBestPerson(ArrayList<LocalNode> population) {

        Integer max = Integer.MIN_VALUE;
        LocalNode bestNode = null;
        for (int i = 0; i < population.size(); i++) {

            if (population.get(i).getValue() >= max)
                bestNode = population.get(i);
            max = Math.max(max, population.get(i).getValue());

        }
        return bestNode;
    }


    private LocalNode randomSelection(ArrayList<LocalNode> list) {

//        Collections.sort(list, Collections.reverseOrder());

        Random random = new Random();
        int idx = 0;
        if (list.size() != 1) {
            idx = random.nextInt(list.size() );
        }
        LocalNode node = list.get(idx);
        return node;


    }

    private State produce(LocalNode x, LocalNode y, Problem problem) {




        State child = problem.crossOver(x.getState(), y.getState());

        return child;

    }

    private ArrayList<LocalNode> createDataset(ArrayList<LocalNode> population, int[] fn) {

        int sum = 0;
        double[] probibility = new double[fn.length];
        ArrayList<LocalNode> list = new ArrayList<>();
        for (int i = 0; i < fn.length; i++)
            sum += fn[i];
        for (int i = 0; i < fn.length; i++) {
            probibility[i] = new Double((double) ((double) fn[i] / (double) sum * (double) 100d) + "");
        }
        double s = 0;
        for (int i = 0; i < probibility.length; i++)
            s += probibility[i];

        for (int i = 0; i < population.size(); i++) {
            for (int c = 0; c < Math.floor(probibility[i]); c++)
                list.add(population.get(i));
        }



        return list;
    }

    private State mutation(State child, Problem problem, double p) {


        double p_rand = Math.random();
        if (p_rand <= p) {

            child = problem.mutate(child);
        }

        return child;



    }
}
