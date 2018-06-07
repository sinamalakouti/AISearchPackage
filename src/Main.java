import problem.*;
import searchAlgorithms.BFS.BFS_Graph;
import searchAlgorithms.DFS.DFS_Graph;
import searchAlgorithms.DFS.DFS_Tree;
import searchAlgorithms.DFS.DLS.DLS_Grpah;
import searchAlgorithms.DFS.IDS.IDS_Graph;
import searchAlgorithms.UCS.UCS_Graph;
import searchAlgorithms.astar.Astar_Graph;
import searchAlgorithms.astar.Astar_Tree;
import searchAlgorithms.bidirectional.Bidirectional_Graph;
import searchAlgorithms.bidirectional.Bidirectional_Tree;
import tree.Node;

import java.util.PriorityQueue;

import static java.lang.System.in;
import static java.lang.System.out;

public class Main {

    public static void main(String[] args) {


        Problem1 problem = new Problem1(5,5,4);
        int [] x_src , y_src , x_dest , y_dest ;
        x_src= new int[4];
        y_src= new int[4];
        x_dest= new int[4];
        y_dest= new int[4];

        x_src[0]= 3;
        x_src[1]=3;
        x_src[2]=2;
        x_src[3]=3;
        y_src[0]=2;
        y_src[1]=3;
        y_src[2]=3;
        y_src[3]=3;


        x_dest[0]= 4;
        x_dest[1]=4;
        x_dest[2]=2;
        x_dest[3]=3;
        y_dest[0]=2;
        y_dest[1]=3;
        y_dest[2]=4;
        y_dest[3]=4;

        PriorityQueue<Node> p = new PriorityQueue<>();

        Node n = new Node(problem.getInitialState());
        n.setPathCost(-4);
        p.add(n);
         Node m  = new Node(problem.getInitialState());
        m.setPathCost(-1);
        p.add(m);
        System.out.println(p.poll().getPathCost());
        System.out.println(p.poll().getPathCost());

        problem.initActions(x_src,y_src,x_dest,y_dest,4);
        Bidirectional_Tree dfs = new Bidirectional_Tree();
        Solution sol = dfs.solve(problem,problem.getInitialState());
        sol.problem = problem;
        System.out.println(sol.toString());


// Probelm 2 :


        Problem2 problem2 = new Problem2();

        int [] board = {1,4,2,7,0,5,3,6,8};
//        int [] board2 = {0,1,2,3,4,5,6,7,8};

        problem2.setInitialState( board);
        Bidirectional_Graph ucs = new Bidirectional_Graph();

        Solution sol2 = ucs.solve(problem2, (Problem.State) problem2.getInitialState());

            sol2.problem = problem2;
//        System.out.println(sol2.toString());


//        problem 3 :
        Problem3 problem3 = new Problem3();
        char [] input = {'y','b','y','b','g','y','g','y','w','g','w','g','b','w','b','w','r','r','r','r','o','o','o','o'};
        int [] board3 = new int[input.length];
        for (int i =0 ; i < input.length ; i++)
            board3[i] = problem3.parseInput(input[i]);
        problem3.createInitialState(board3);

//        BFS_Graph ucs = new BFS_Graph();
//        Solution sol2 = ucs.solve(problem3, (Problem.State) problem3.getInitialState());

        sol2.toString();



    }
// path is :( 0, 0 ) -> ( 1, 0 ) -> ( 2, 0 ) -> ( 3, 0 ) -> ( 3, 1 ) -> ( 4, 1 ) -> ( 4, 2 ) -> ( 4, 3 ) -> ( 4, 4 )
//    1 4 2
//            7 0 5
//            3 6 8
//
//            1 4 2
//            0 7 5
//            3 6 8
//
//            1 4 2
//            3 7 5
//            0 6 8
//
//            1 4 2
//            3 7 5
//            6 0 8
//
//            1 4 2
//            3 0 5
//            6 7 8
//
//            1 0 2
//            3 4 5
//            6 7 8
//
//            0 1 2
//            3 4 5
//            6 7 8






    public void check (){


    int [] board = {0,1,2,3,4,5,6,7,8};

}

}
