package BusinessLogic;

import Model.Server;
import Model.Task;

import java.util.ArrayList;

import static java.lang.Integer.MAX_VALUE;

public class StrategyTime implements Strategy{
    private static int wT=0;
    @Override
    public void doStrategy(ArrayList<Server>servers, Task t){
        int minTime=MAX_VALUE;
        Server aux=null;
        for(Server s:servers){
            if(s.getWaitingPeriod().get()<minTime){
                minTime=s.getWaitingPeriod().get();
                aux=s;
            }
        }
        wT+=minTime;
        aux.addTask(t);
    }

    public static int getwT() {
        return wT;
    }
}
