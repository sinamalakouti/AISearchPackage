package controller;

/**
 * Created by sina on 6/4/18.
 */
public class Controller {
    private static Controller ourInstance = new Controller();

    public static Controller getInstance() {
        return ourInstance;
    }

    private Controller() {
    }
}
