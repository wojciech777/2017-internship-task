package pl.codewise.internship;


import java.util.HashMap;
import java.util.Map;

/**
 * Created by wojtek on 24.05.17.
 */
public class SchedulerImpl implements Scheduler {
    private volatile Map<TimerId,Thread> tasks = new HashMap<>();

    public TimerId register(int expireInSeconds, Runnable callback) {
        TimerId timerId = new TimerIdImpl();

        tasks.put(timerId,new Thread(callback));
        removingTasks(timerId);
        startTask(timerId);

        return timerId;
    }



    public void stop(TimerId timerId) {
        if(tasks.containsKey(timerId)  && !tasks.get(timerId).isAlive())
            tasks.remove(timerId);
    }

    private void startTask(TimerId timerId) {
        while(true){
            synchronized (this){
                if(tasks.containsKey(timerId) &&
                        !tasks.values().stream().anyMatch(x -> x.isAlive()))
                    new Thread(tasks.get(timerId)).start();
                else break;
            }

        }

    }

    private void removingTasks(final TimerId timerId){
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        stop(timerId);
    }
}
