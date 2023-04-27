package Model;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.atomic.AtomicInteger;

public class Server implements Runnable{
    private BlockingQueue<Task> tasks;
    private AtomicInteger waitingPeriod;
    private int id;

    public Server(int id) {
        this.id = id;
        tasks=new LinkedBlockingQueue<>();
        waitingPeriod = new AtomicInteger(0);
    }

    public BlockingQueue<Task> getTasks() {
        return tasks;
    }


    public AtomicInteger getWaitingPeriod() {
        return waitingPeriod;
    }


    public void addTask(Task newTask){
        tasks.add(newTask);
        waitingPeriod.addAndGet(newTask.getServiceTime());
    }

    public void run(){
        while(true){
            Task t=tasks.peek();
            if(t!=null){
                while(t.getServiceTime()>0){
                    try {
                        Thread.sleep(1000);
                    }
                    catch(InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                    t.setServiceTime(t.getServiceTime()-1);
                    waitingPeriod.decrementAndGet();
                }
                tasks.remove(t);
            }
        }
    }

    public String serverToString(){
        String s="Queue ";
        s+=id;
        s+=": ";
        if(tasks.size()==0){
            s+="Closed";
        }

        for(Task t:tasks){
            s+=t.toString();
        }
        return s;
    }
}
