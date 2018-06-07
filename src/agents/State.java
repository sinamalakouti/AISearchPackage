package agents;

/**
 * Created by sina on 6/4/18.
 */
public interface State {

    @Override
    public boolean equals(Object o);

    public boolean contains( State s);

    public String to_String();
}
