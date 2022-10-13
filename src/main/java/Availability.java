import java.util.Collection;
import java.util.TreeSet;

public class Availability {
    // Stores string representations of 15 minute windows of availability
    private final Collection<String> times = new TreeSet<>();
    // Static storage of day name strings
    private static final String[] days = {"Monday", "Tuesday", "Wednesday",
            "Thursday", "Friday", "Saturday", "Sunday"};

    /*
     * toTime
     *
     * parameters:  String day (sunday - saturday), Integer
     *                  hour (0 - 23), Integer minutes (0, 15, 30, 45)
     * return:      String representation of time (D(H)H:MM)
     * description: Takes values for a day of the week, hour, and 15-min
     *                  increment, and converts them to time format for later
     *                  storage in availability.
     */
    public String toTime (String day, Integer hour, Integer minutes) {
        String availability = "";
        for (int i = 0; i < 7; ++i) {
            if (day.equalsIgnoreCase(days[i])) break;
            else if (i == 6) return null;
        }
        if (hour > 23 || hour < 0) return null;
        if (minutes % 15 != 0 || minutes < 0 || minutes > 59) return null;
        day = day.toUpperCase();
        if (day.equalsIgnoreCase(days[3]))
            availability += "R";
        else if (day.equalsIgnoreCase(days[5]))
            availability += "A";
        else availability += day.charAt(0);
        availability += hour.toString();
        availability += ":";
        availability += minutes.toString();
        return availability;
    }

    /*
     * toText
     *
     * parameters:  String time (D(H)H:MM)
     * return:      String array containing day (sunday - saturday), hours
     *                  (0 - 23), minutes (0, 15, 30, 45)
     * description: Takes a time format string and converts it to a String
     *                  array storing the day as element 0, hour as element 1,
     *                  and minutes as element 2.
     */
    public String[] toText (String time) {
        String[] dayAndTime = new String[3];
        for (int i = 0; i < 7; ++i) {
            if (time.charAt(0) == days[i].charAt(0))
                dayAndTime[0] = days[i];
            else if (time.charAt(0) == 'R')
                dayAndTime[0] = days[3];
            else if (time.charAt(0) == 'A')
                dayAndTime[0] = days[5];
        }
        dayAndTime[1] = time.substring(1, time.indexOf(':'));
        dayAndTime[2] = time.substring(time.indexOf(':') + 1);
        return dayAndTime;
    }

    /*
     * addTime
     *
     * parameters:  String time (D(H)H:MM)
     * return:      none
     * description: Takes a time format string and adds it to availability.
     */
    public void addTime (String time) {
        times.add(time);
    }

    /*
     * addTime
     *
     * parameters:  String day (sunday - saturday), Integer
     *                  hour (0 - 23), Integer minutes (0, 15, 30, 45)
     * return:      none
     * description: Takes values for a day of the week, hour, and 15-min
     *                  increment, and adds that time to availability.
     */
    public void addTime (String day, int hour, int minute) {
        String time = toTime(day, hour, minute);
        addTime(time);
    }

    /*
     * hasTime
     *
     * parameters:  String time (D(H)H:MM)
     * return:      boolean representation of whether availability contains
     *                  the given time
     * description: Takes a time format string and checks if it is in
     *                  availability.
     */
    public boolean hasTime (String time) {
        return times.contains(time);
    }

    /*
     * hasTime
     *
     * parameters:  String day (sunday - saturday), Integer
     *                  hour (0 - 23), Integer minutes (0, 15, 30, 45)
     * return:      boolean representation of whether availability contains
     *                  the given time
     * description: Takes values for a day of the week, hour, and 15-min
     *                  increment, and checks if that time is in availability.
     */
    public boolean hasTime (String day, int hour, int minute) {
        String time = toTime(day, hour, minute);
        return hasTime(time);
    }

    /*
     * removeTime
     *
     * parameters:  String time (D(H)H:MM)
     * return:      none
     * description: Takes a time format string and removes it from
     *                  availability.
     */
    public void removeTime (String time) {
        times.remove(time);
    }

    /*
     * removeTime
     *
     * parameters:  String day (sunday - saturday), Integer
     *                  hour (0 - 23), Integer minutes (0, 15, 30, 45)
     * return:      none
     * description: Takes values for a day of the week, hour, and 15-min
     *                  increment, and removes that time from availability.
     */
    public void removeTime (String day, int hour, int minutes) {
        String time = toTime(day, hour, minutes);
        removeTime(time);
    }
}
