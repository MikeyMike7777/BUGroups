package database.student;

import java.util.*;

public class Availability {

    private Vector<String> times = new Vector<>();

    Availability(Vector<String> time) {
        times = time;
    }

    public Vector<String> getTimes() {
        return times;
    }

    public void setTimes(Vector<String> times) {
        this.times = times;
    }

    // returns availability in comma separated string format e.g. { "Monday 5:00-6:00", "Thursday 10:30-12:15" }
    @Override
    public String toString(){
        String availability = "";
        for (String s : times){
            availability += s + ", ";
        }
        return availability;
    }
}
