package student;

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
}
