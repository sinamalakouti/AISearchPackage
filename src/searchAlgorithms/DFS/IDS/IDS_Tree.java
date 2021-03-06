package searchAlgorithms.DFS.IDS;

import agents.Agent;
import agents.State;
import controller.MainProblem;
import problem.Problem;
import problem.Solution;
import searchAlgorithms.DFS.DLS.DLS_Tree;
import searchAlgorithms.SearchAlgorithms;
import tree.Node;

/**
 * Created by sina on 6/6/18.
 */
public class IDS_Tree extends SearchAlgorithms implements Agent {
    final int maxLimitDepth = 20;


    @Override
    public Solution solve(MainProblem mainProblem, State start) {
        Problem problem = ((Problem) mainProblem);

        solution = new Solution();
        Node currentNode = new Node(start);

        for (int i = 1; i <= maxLimitDepth; i++) {
            DLS_Tree dls = new DLS_Tree(i);
            dls.setSolution(solution);
            Object res = dls.recursiveDLS(currentNode, problem, i);
            if (res == null)
                return null;
            else if (res.getClass().equals(String.class) && ((String) res).compareTo("cutOff") == 0)
                continue;
            else
                return (Solution) res;
        }
        return null;
    }


}
