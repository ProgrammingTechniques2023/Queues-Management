package BusinessLogic;

import Model.Server;
import Model.Task;

import java.util.ArrayList;

import static java.lang.Integer.MAX_VALUE;

public class StrategyQueue implements Strategy{
    private static int wT=0;
    @Override
    public void doStrategy(ArrayList<Server>servers, Task t){
        Server aux=null;
        int minNoTask=MAX_VALUE;
        for (Server s:servers) {

            if(s.getTasks().size()<minNoTask)
            {
                minNoTask=s.getTasks().size();
                aux=s;
            }

        }
        wT+=aux.getWaitingPeriod().get();
        aux.addTask(t);
    }

    public static int getwT() {
        return wT;
    }
}
