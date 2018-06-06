import problem.Problem;
import problem.Problem1;
import problem.Solution;
import searchAlgorithms.DFS.DFS_Graph;
import searchAlgorithms.DFS.DFS_Tree;

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


        problem.initActions(x_src,y_src,x_dest,y_dest,4);
        DFS_Graph dfs = new DFS_Graph();
        Solution sol = dfs.solve(problem,problem.getInitialState());
        System.out.println(sol.toString());






    }






}
