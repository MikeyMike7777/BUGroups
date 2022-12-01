package student;

import java.util.*;

public class Availability {

    private Collection<String> times = new Vector<>();
    private static final Map<String, String> encode = new TreeMap<>();

    Availability() {
        encode.put("monday", "m");
        encode.put("tuesday", "t");
        encode.put("wednesday", "w");
        encode.put("thursday", "r");
        encode.put("friday", "f");
        encode.put("saturday", "s");
        encode.put("sunday", "n");
    }

    public void addAvailability(String day, String time) {
        times.add(encode.get(day.toLowerCase()) + time);
    }

    public boolean removeAvailability(String availability) {
        return times.remove(availability);
    }
}
