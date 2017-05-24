package pl.codewise.internship;

/**
 * Created by wojtek on 24.05.17.
 */
public class TimerIdImpl implements TimerId {
    private int index;
    static private int counter = 1;


    public TimerIdImpl() {
        this.index = counter++;
    }

    @Override
    public int getIndex() {
        return index;
    }

    public int getCounter(){
        return counter;
    }
}
