package bsu.edu.cs.Parsers;

public class Ride {
    final private int id;
    final private String name;
    final private Boolean is_open;
    final private int wait_time;
    final private String last_updated;


    public Ride(int id, String name, Boolean is_open, int wait_time, String last_updated){
        this.id = id;
        this.name = name;
        this.is_open = is_open;
        this.wait_time = wait_time;
        this.last_updated = last_updated;

    }

    public int getId(){
        return id;
    }

    public String getName(){
        return name;
    }

    public Boolean getIsOpen(){
        return is_open;
    }

    public int getWaitTime(){
        return wait_time;
    }

    public String getLastUpdated(){return last_updated;}

}
