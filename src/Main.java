import problem.Problem;
import problem.Problem1;
import problem.Problem2;
import problem.Solution;
import searchAlgorithms.BFS.BFS_Graph;
import searchAlgorithms.DFS.DFS_Graph;
import searchAlgorithms.DFS.DFS_Tree;
import searchAlgorithms.DFS.DLS.DLS_Grpah;
import searchAlgorithms.DFS.IDS.IDS_Graph;
import searchAlgorithms.UCS.UCS_Graph;
import searchAlgorithms.astar.Astar_Graph;
import searchAlgorithms.astar.Astar_Tree;
import searchAlgorithms.bidirectional.Bidirectional_Graph;
import tree.Node;

import java.util.PriorityQueue;

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
        Astar_Graph dfs = new Astar_Graph();
//        Solution sol = dfs.solve(problem,problem.getInitialState());
//        System.out.println(sol.toString());


// Probelm 2 :


        Problem2 problem2 = new Problem2();

        int [] board = {1,4,2,7,0,5,3,6,8};

        problem2.setInitialState( board);
        UCS_Graph ucs = new UCS_Graph();
        Solution sol2 = ucs.solve(problem2, (Problem.State) problem2.getInitialState());


        int [] board2 = {0,1,2,3,4,5,6,7,8};
        Problem2.State state = problem2.createState(board);
        System.out.println(state.equals(problem2.getFinalState()));
        System.out.println(sol2.toString());




    }
// path is :( 0, 0 ) -> ( 1, 0 ) -> ( 2, 0 ) -> ( 3, 0 ) -> ( 3, 1 ) -> ( 4, 1 ) -> ( 4, 2 ) -> ( 4, 3 ) -> ( 4, 4 )




public void check (){


    int [] board = {0,1,2,3,4,5,6,7,8};

}

}
