package Model;

public class Task implements Comparable<Task> {
    private int ID;
    private int arrivalTime;
    private int serviceTime;

    public Task(int ID, int arrivalTime, int serviceTime) {
        this.ID = ID;
        this.arrivalTime = arrivalTime;
        this.serviceTime = serviceTime;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public void setServiceTime(int serviceTime) {
        this.serviceTime = serviceTime;
    }

    public int getID() {
        return ID;
    }

    public int getArrivalTime() {
        return arrivalTime;
    }

    public int getServiceTime() {
        return serviceTime;
    }

    @Override
    public int compareTo(Task t) {
        if(this.getArrivalTime()<t.getArrivalTime()){
            return -1;
        }
        if(this.getArrivalTime()>t.getArrivalTime()){
            return 1;
        }
        return 0;
    }

    public String toString(){
        return "("+getID()+","+getArrivalTime()+","+getServiceTime()+")";
    }

}
