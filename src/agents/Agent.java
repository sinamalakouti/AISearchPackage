package agents;

/**
 * Created by sina on 6/4/18.
 */
public interface Agent<Percept, Action> {

    Action perform(Percept p);
}
