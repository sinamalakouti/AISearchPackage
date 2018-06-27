package localsearch;

import agents.Agent;
import agents.State;
import controller.MainProblem;
import controller.MainSolution;
import demo.SampleXYDataset;
import demo.XYSeriesDemo1;
import demo.XYShapeRendererDemo1;
import localsearch.LocalNode;
import localsearch.hillclimbing.Solution;
import localsearch.localSearchProblems.Keyboard;
import localsearch.localSearchProblems.Problem;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.renderer.xy.XYLineAndShapeRenderer;
import org.jfree.data.xy.XYDataset;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;
import org.omg.PortableServer.POA;
import searchAlgorithms.SearchAlgorithms;
import tree.Node;

import javax.swing.*;
import java.awt.*;
import java.util.*;

/**
 * Created by sina on 6/15/18.
 */
public class Genetic extends SearchAlgorithms implements Agent {

    int maxPopulation = 500;


    int numberOfIteration = 0;
    double mutationRate = 0.02d;

    public ArrayList<ArrayList<Double>> storedComptenecis = new ArrayList<>();
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
            ArrayList<Double> values = getmid_min_max_Comptency(newPopulation);
            storedComptenecis.add(values);
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
    private ArrayList<Double> getmid_min_max_Comptency(ArrayList<LocalNode> nodes){

        Collections.sort(nodes, Comparator.reverseOrder());
        double max = nodes.get(0).getValue();
        double min = nodes.get(nodes.size() -1 ).getValue();
        double  sum =0 ;
        for (int i =0 ; i < nodes.size() ; i++)
            sum += nodes.get(i).getValue();
        double avg = ((double) sum) / ((double) nodes.size());
        ArrayList<Double>res = new ArrayList<>();
        res.add(min);
        res.add(avg);
        res.add(max);
        return res;

    }


    private XYDataset createDataset(int kind,ArrayList<ArrayList<Double>> values){




            XYSeriesCollection dataset = new XYSeriesCollection();
            XYSeries series1 = new XYSeries("min");
            XYSeries series2 = new XYSeries("avg");
            XYSeries series3 = new XYSeries("max");








                for (int i = 0 ; i < values.size() ; i ++){

                    series1.add(i, values.get(i).get(0));

                }




                for (int i = 0 ; i < values.size() ; i ++){

                    series2.add(i, values.get(i).get(1));

                }



        for (int i = 0 ; i < values.size() ; i ++){

            series3.add(i, values.get(i).get(2));

        }




        dataset.addSeries(series1);
        dataset.addSeries(series2);
        dataset.addSeries(series3);

        return dataset;
    }

    public void drawGraph(int kind , ArrayList<ArrayList<Double>> values){
        String tit = "graph";
        String xAxisLabel = "X";
        String yAxisLabel = "Y";

        XYDataset dataset = createDataset(kind, values);

        JFreeChart chart = ChartFactory.createXYLineChart(tit,
                xAxisLabel, yAxisLabel, dataset);
        JFrame frame = new JFrame();

        JPanel chartPanel = new ChartPanel(chart);
        frame.add(chartPanel, BorderLayout.CENTER);


        frame.setSize(640, 480);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        XYPlot plot = chart.getXYPlot();
        frame.setVisible(true);

        chartPanel.setVisible(true);
        XYLineAndShapeRenderer renderer = new XYLineAndShapeRenderer();
        plot.setRenderer(renderer);

        plot.setBackgroundPaint(Color.DARK_GRAY);


        plot.setRangeGridlinesVisible(true);
        plot.setRangeGridlinePaint(Color.BLACK);

        plot.setDomainGridlinesVisible(true);
        plot.setDomainGridlinePaint(Color.BLACK);
        switch (kind){

            case 0:

                break;
        }
    }

}
