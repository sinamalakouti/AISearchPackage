package searchAlgorithms.DFS.IDS;

import agents.Agent;
import agents.State;
import problem.Problem;
import problem.Solution;
import searchAlgorithms.DFS.DLS.DLS_Grpah;
import searchAlgorithms.DFS.DLS.DLS_Tree;
import searchAlgorithms.SearchAlgorithms;
import tree.Node;

/**
 * Created by sina on 6/6/18.
 */
public class IDS_Graph extends SearchAlgorithms implements Agent {

    final int maxLimitDepth = 20;



    @Override
    public Solution solve(Problem problem, State start) {
        Node currentNode = new Node(start);

        for (int i = 1; i <= maxLimitDepth; i++) {
            DLS_Grpah dls = new DLS_Grpah(i);
            Object res = dls.recursiveDLS(currentNode, problem, i);
            if (res == null)
                return null;
            else if (res.getClass().isInstance(String.class) && ((String) res).compareTo("cutOff") == 0)
                continue;
            else
                return (Solution) res;



        }
        return null;
    }


    @Override
    public Object execute(Object p) {
        return null;
    }
}
