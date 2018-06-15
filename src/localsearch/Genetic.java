package localsearch;

import agents.Agent;
import agents.State;
import controller.MainProblem;
import controller.MainSolution;
import localsearch.LocalNode;
import localsearch.hillclimbing.Solution;
import localsearch.localSearchProblems.Problem;
import searchAlgorithms.SearchAlgorithms;
import tree.Node;

import java.util.ArrayList;
import java.util.Random;
import java.util.Set;

/**
 * Created by sina on 6/15/18.
 */
public class Genetic extends SearchAlgorithms implements Agent {

  int maxPopulation = 20;


 int numberOfIteration = 0 ;

    public Genetic(int maxPopulation, int numberOfIteration) {

        this.maxPopulation = maxPopulation;
        this.numberOfIteration = numberOfIteration;
    }

    @Override
    public MainSolution solve(MainProblem mainProblem, State start) {


        Solution sol = new Solution();
        Problem problem = ((Problem) mainProblem);
        ArrayList<LocalNode> population = new ArrayList<>();
        ArrayList<LocalNode> allStates = problem.getKStates(maxPopulation);

        for (int i = 0 ; i < maxPopulation ; i ++){
            Random random = new Random();
            int idx = random.nextInt(allStates.size() - 1);
            LocalNode chosenNode = allStates.get(idx);
            allStates.remove(idx);
            population.add(chosenNode);
        }

        int [] fn = new int[population.size()];
        for (int i =0 ; i < population.size(); i++)
            fn[i] = population.get(i).getValue();

        int counter = 0;

        while (true){
            if ( counter == numberOfIteration)
            {
                sol.finalState =this.getTheBestPerson(population).getState();
                return sol;

            }
            ArrayList<LocalNode> newPopulation = new ArrayList<>();
            ArrayList<LocalNode> list = this.createDataset(population,fn);
            for (int i =0 ; i< population.size() ; i++){
                LocalNode  x = randomSelection(list);
                LocalNode  y = randomSelection(list);

                ArrayList<ArrayList<Integer>> children = produce(x,y,problem);
                Random rand = new Random();
                int idx = rand.nextInt(1);
                ArrayList<Integer> child_Chromosome = children.get(idx);
                child_Chromosome = mutation(child_Chromosome,problem,0.3);
                ArrayList<Integer> child = children.get(idx);
                State childState = problem.chromosomeToState(child);

                LocalNode node = new LocalNode(childState);
                node.setState(childState);
                node.setValue(problem.calculateValue(childState));
                newPopulation.add(node);


            }

            counter ++;

        }
    }


    private LocalNode getTheBestPerson(ArrayList<LocalNode> population){

        Integer max = Integer.MIN_VALUE;
        LocalNode bestNode = null;
        for (int i =0 ; i< population.size() ; i++){

            if ( population.get(i).getValue() >= max)
                bestNode = population.get(i);
            max = Math.max(max,population.get(i).getValue());

        }
        return bestNode;
    }


    private LocalNode randomSelection (ArrayList<LocalNode> list){


        Random random = new Random();
        int idx =random.nextInt(list.size() - 1);
        LocalNode node = list.get(idx);
        return node;


    }

    private ArrayList<ArrayList<Integer>> produce (LocalNode x , LocalNode y,Problem problem){

        ArrayList<Integer> x_Chromosome =  problem.createChromosome(x.getState());
        ArrayList<Integer> y_Chromosome =  problem.createChromosome(y.getState());
        Random random = new Random();

        int idx = random.nextInt(x_Chromosome.size() - 1);

        ArrayList<Integer> child1_Chromosome = new ArrayList<>();
        ArrayList<Integer> child2_Chromosome = new ArrayList<>();

        for ( int i =0 ; i < x_Chromosome.size() ; i++){

            if( i <= idx){

                child1_Chromosome.add(x_Chromosome.get(i));
                child2_Chromosome.add(y_Chromosome.get(i));
            }else{

                child2_Chromosome.add(x_Chromosome.get(i));
                child1_Chromosome.add(y_Chromosome.get(i));

            }

        }


        ArrayList<ArrayList<Integer>>result = new ArrayList<>();
        result.add(child1_Chromosome);
        result.add(child2_Chromosome);
        return result;

    }

    private ArrayList<LocalNode> createDataset(ArrayList<LocalNode> population, int[] fn){

        int sum = 0 ;
        double[] probibility = new double[fn.length];
        ArrayList<LocalNode> list = new ArrayList<>();
        for (int i =0 ;i < fn.length ; i ++)
            sum += fn[i];
        for (int i =0 ;i < fn.length ; i ++)
            probibility[i] = (double) ((double) fn[i] / (double) sum * (double) 100 );


        for (int i = 0 ; i<population.size() ; i++){
            for (int c =0 ; c < probibility[i]; c++)
                list.add(population.get(i));
        }


        return list;
    }

    private ArrayList<Integer> mutation ( ArrayList<Integer> child, Problem problem , double p){

        double p_rand = Math.random();
        if(p_rand <= p){
                Random random  = new Random();
            int idx = random.nextInt(child.size()-1);
           int val = random.nextInt(problem.getPossibleChromosomeValues().size() - 1);

            child.set(idx, val);
        }

        return child;
    }
}
