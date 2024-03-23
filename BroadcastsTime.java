package final_test;

public class BroadcastsTime implements Comparable<BroadcastsTime> {
    private byte hour;
    private byte minutes;

    BroadcastsTime (String format) {
        String[] time = format.split(":");
        this.hour = (byte) (Integer.parseInt(String.valueOf(time[0].charAt(0))) * 10 + Integer.parseInt(String.valueOf(time[0].charAt(1))));
        this.minutes = (byte) (Integer.parseInt(String.valueOf(time[1].charAt(0))) * 10 + Integer.parseInt(String.valueOf(time[1].charAt(1))));
    }
    byte hour() {
        return hour;
    }
    byte minutes() {
        return minutes;
    }
    boolean after(BroadcastsTime t) {
        if (hour() != t.hour()) return hour() > t.hour();
        else {
            return minutes() > t.minutes();
        }
    }
    boolean before(BroadcastsTime t) {
        if (hour() != t.hour()) return hour() < t.hour();
        else {
            return minutes() < t.minutes();
        }
    }
    boolean between(BroadcastsTime t1, BroadcastsTime t2) {
        if (t1.hour() > t2.hour()) {
            return false;
        }
        if (t1.hour() <= hour() && hour() <= t2.hour()) {
            if ((t1.hour() == hour() && t1.minutes() > minutes())) {
                return false;
            }
            if ((t2.hour() == hour()) && minutes() >= t2.minutes()) {
                return false;
            }
            return true;
        }
        return false;
    }
    public String toString() {
        return hour + ":" + min;
    }
    @Override
    public int compareTo(BroadcastsTime t) {
        if (after(t)) return 1;
        if (before(t)) return -1;
        return 0;
    }
}
