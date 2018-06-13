import controller.MainSolution;
import localsearch.hillclimbing.HillClimbing;
import localsearch.localSearchProblems.GraphColoring;
import problem.*;
import searchAlgorithms.DFS.IDS.IDS_Graph;

public class Main {

    public static void main(String[] args) {


        Problem1 problem = new Problem1(5, 5, 4);
        int[] x_src, y_src, x_dest, y_dest;
        x_src = new int[4];
        y_src = new int[4];
        x_dest = new int[4];
        y_dest = new int[4];

        x_src[0] = 3;
        x_src[1] = 3;
        x_src[2] = 2;
        x_src[3] = 3;
        y_src[0] = 2;
        y_src[1] = 3;
        y_src[2] = 3;
        y_src[3] = 3;


        x_dest[0] = 4;
        x_dest[1] = 4;
        x_dest[2] = 2;
        x_dest[3] = 3;
        y_dest[0] = 2;
        y_dest[1] = 3;
        y_dest[2] = 4;
        y_dest[3] = 4;


//        problem.initActions(x_src,y_src,x_dest,y_dest,4);
//        Astar_Graph dfs = new Astar_Graph();
//        Solution sol = dfs.solve(problem,problem.getInitialState());
//        sol.problem = problem;
//        System.out.println(sol.toString());


// Probelm 2 :


        Problem2 problem2 = new Problem2();

        int[] board = {1, 4, 2, 7, 0, 5, 3, 6, 8};
//        int [] board = {1,4,2,3,7,5,0,6,8};

//        int [] board = {4,5,2,1,7,3,0,6,8};

//        int [] board = {4,3,5, ,2,7,0,8,1};

//        int [] board2 = {0,1,2,3,4,5,6,7,8};

//        problem2.setInitialState( board);
//        System.out.println(problem2.isSolvable());
//
//
//        if (problem2.isSolvable()) {
//
//            Astar_Graph ucs = new Astar_Graph();
//            Solution sol2 = ucs.solve(problem2, (Problem.State) problem2.getInitialState());
//
//            sol2.problem = problem2;
//            System.out.println(sol2.toString());
//
//        }else System.out.println("problem is not solvable");
//
////        problem 3 :
//        Problem3 problem3 = new Problem3();
//        char[] input = {'y', 'b', 'y', 'b', 'g', 'y', 'g', 'y', 'w', 'g', 'w', 'g', 'b', 'w', 'b', 'w', 'r', 'r', 'r', 'r', 'o', 'o', 'o', 'o'};
//        int[] board3 = new int[input.length];
//        for (int i = 0; i < input.length; i++)
//            board3[i] = problem3.parseInput(input[i]);
//        problem3.createInitialState(board3);
//
//        IDS_Graph ucs = new IDS_Graph();
//        Solution sol2 = ucs.solve(problem3, (Problem.State) problem3.getInitialState());
//        sol2.problem = problem3;
//        System.out.println(sol2.toString());


// local search problems;
            int [][]matrix = new int[4][4];
        matrix[0][0] = 1;
        matrix[1][1] = 1;
        matrix[2][2] = 1;
        matrix[3][3] = 1;

        matrix[0][1] = 1;
        matrix[1][0] = 1;

        matrix[0][3] = 1;
        matrix[3][0] = 1;

        matrix[0][2] = 1;
        matrix[2][0] = 1;

        matrix[1][3] = 1;
        matrix[3][1] = 1;




        GraphColoring graphColoring = new GraphColoring(matrix,4,2);
        HillClimbing hillClimbing = new HillClimbing();
       MainSolution sol =  hillClimbing.solve(graphColoring,graphColoring.getInitialState());
        System.out.println(sol);
    }
// path is :( 0, 0 ) -> ( 1, 0 ) -> ( 2, 0 ) -> ( 3, 0 ) -> ( 3, 1 ) -> ( 4, 1 ) -> ( 4, 2 ) -> ( 4, 3 ) -> ( 4, 4 )


    public void check() {


        int[] board = {0, 1, 2, 3, 4, 5, 6, 7, 8};

    }

}
