package BusinessLogic;

import GUI.View;
import Model.Server;
import Model.Task;

import javax.swing.*;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static java.lang.Integer.MIN_VALUE;

public class SimulationManager implements Runnable {
    //data read from UI
    private int timeLimit; //max processing time
    private int maxProcessingTime;
    private int minProcessingTime;
    private int numberOfClients;
    private SelectionPolicy selectionPolicy;

    private int minArrival;
    private int maxArrival;

    private Scheduler scheduler;

    private ArrayList<Task> generatedTasks=new ArrayList<>();
    private ArrayList<Server> servers;

    public FileWriter fw;
    private View view;


    public SimulationManager(int timeLimit, int maxProcessingTime, int minProcessingTime, int numberOfServers, int numberOfClients, int minArrival, int maxArrival, SelectionPolicy policy) {
        this.timeLimit = timeLimit;
        this.maxProcessingTime = maxProcessingTime;
        this.minProcessingTime = minProcessingTime;
        this.numberOfClients = numberOfClients;
        this.minArrival = minArrival;
        this.maxArrival = maxArrival;
        selectionPolicy=policy;


        generateNRandomTasks();
        servers=new ArrayList<>(numberOfServers);
        scheduler=new Scheduler(numberOfServers);
    }

    public void setView(View view) {
        this.view = view;
    }

    public void sortTasks(List<Task> tasks) {
        Collections.sort(tasks);
        for(int i=0;i<tasks.size();i++)
        {
            tasks.get(i).setID(i+1);
        }
    }
    public void generateNRandomTasks(){
        for (int i=0;i<numberOfClients;i++) {
            int idClient=i+1;
            int timeArrival;
            int timeService;
            timeArrival = (int) ((Math.random() * (maxArrival - minArrival)) + minArrival);
            timeService = (int) ((Math.random() * (maxProcessingTime - minProcessingTime)) + minProcessingTime);
            Task t = new Task(idClient, timeArrival, timeService);
            generatedTasks.add(t);
        }
        sortTasks(generatedTasks);

        /*for(int i=0;i<generatedTasks.size();i++){
            System.out.println(generatedTasks.get(i));
        }*/
    }
    @Override
    public void run(){
        int maxTasks=MIN_VALUE;
        int peak=0;
        int currentTime=0;
        float avgTime=avgService();
        try {
            fw=new FileWriter("test.txt");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        while(currentTime<timeLimit){
            printTime(currentTime);
            boolean ok=true;
            Task task=null;
            while(ok && generatedTasks.size()>0) {
                ok = false;
                if (currentTime == generatedTasks.get(0).getArrivalTime()) {
                    task = generatedTasks.get(0);
                    scheduler.changeStrategy(selectionPolicy);
                    scheduler.dispatchTask(task);
                    generatedTasks.remove(0);
                    ok = true;
                }
            }
            int taskForServer=0;
            for (Server s : scheduler.getServers()) {
                taskForServer+=s.getTasks().size(); }
            if(taskForServer>maxTasks) {
                maxTasks=taskForServer;
                peak=currentTime; }
            printAtCrtTime();
            try { Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace(); }
            currentTime++;
        }
        printFinal(avgTime,peak);
        try {
            fw.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void printFinal(float avgTime, int peak){
        System.out.println("Waiting Time: "+ waitingTime());
        try {
            fw.write("Waiting Time: "+ waitingTime()+"\n");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        System.out.println("Average Service Time: "+ avgTime);
        try {
            fw.write("Average Service Time: "+ avgTime+"\n");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        System.out.println("Peak h: "+ peak);
        try {
            fw.write("Peak h: "+ peak+"\n");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        view.getTextArea().append("Waiting Time: "+ waitingTime()+"\n");
        view.getTextArea().append("Average Service Time: "+ avgTime+"\n");
        view.getTextArea().append("Peak h: "+ peak+"\n"+"\n"+"\n");
    }
    public void printAtCrtTime(){
        System.out.println("Waiting tasks: "+ waiting(generatedTasks));
        try {
            fw.write("Waiting tasks: "+ waiting(generatedTasks)+"\n");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        System.out.println(tasksInServer(scheduler.getServers()));
        try {
            fw.write(tasksInServer(scheduler.getServers())+"\n");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        view.getTextArea().append("Waiting tasks: "+ waiting(generatedTasks)+"\n");
        view.getTextArea().append(tasksInServer(scheduler.getServers())+"\n");
    }
    public void printTime(int currentTime){
        System.out.println("Time "+currentTime);
        try {
            fw.write("Time "+currentTime+"\n");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        view.getTextArea().append("Time "+currentTime+"\n");
    }
    public  String tasksInServer(ArrayList<Server>s){
        String txt="";
        for(Server s1:s){
            txt+=s1.serverToString();
            txt+="\n";
        }
        return txt;
    }
    public String waiting(ArrayList<Task>t){
        String s="";
        for(Task t1:t){
            s+=t1.toString();
        }
        return s;
    }

    public float avgService(){
        float avgTime=0;
        for(Task t:generatedTasks){
            avgTime+=t.getServiceTime();
        }
        return avgTime/generatedTasks.size();
    }

    public float waitingTime(){
        float avgTime=0;
        if(selectionPolicy==SelectionPolicy.SHORTEST_TIME){
            avgTime=StrategyTime.getwT();
        }
        if(selectionPolicy==SelectionPolicy.SHORTEST_QUEUE){
            avgTime=StrategyQueue.getwT();
        }
        return avgTime/numberOfClients;
    }


    public static void main(String[] args){
       //SimulationManager gen=new SimulationManager(10, 5,1,2,20,2,4);
       //Thread t=new Thread(gen);
       //t.start();
        JFrame frame = new View();
        frame.setVisible(true);
    }
}
