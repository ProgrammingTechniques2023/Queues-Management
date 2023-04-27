package BusinessLogic;

import Model.Server;
import Model.Task;

import java.util.ArrayList;

public class Scheduler {
    private ArrayList<Server> servers;

    private Strategy strategy;

    public Scheduler(int maxNoServers){
        this.servers=new ArrayList<>();
        this.strategy=new StrategyTime();
        for(int i=0;i<maxNoServers;i++){
            Server s=new Server(i+1);
            servers.add(s);
        }
        for(Server s:servers){
            Thread th=new Thread(s);
            th.start();
        }
    }

    public ArrayList<Server> getServers() {
        return servers;
    }

    public void changeStrategy(SelectionPolicy policy){
        if(policy==SelectionPolicy.SHORTEST_QUEUE){
            strategy=new StrategyQueue();
        }
        else if(policy==SelectionPolicy.SHORTEST_TIME)
        {
            strategy=new StrategyTime();
        }
    }

    public void dispatchTask(Task t){
        strategy.doStrategy(servers,t);
    }
}
