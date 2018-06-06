package agents;

/**
 * Created by sina on 6/4/18.
 */
public abstract class Action {
    protected  int move;

    public int getMove() {
        return move;
    }

    @Override
    public abstract boolean equals(Object obj) ;
}
