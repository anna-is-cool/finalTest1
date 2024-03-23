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
        return (t.hour() <= this.hour) && (t.minutes() <= this.minutes);
    }
    boolean before(BroadcastsTime t) {
        return (t.hour() >= this.hour) && (t.minutes() >= this.minutes);
    }
    boolean between(BroadcastsTime t1, BroadcastsTime t2) {
        return ((t1.hour() <= this.hour) & (this.hour <= t2.hour()) && (t1.minutes() <= this.minutes) & (this.minutes <= t2.minutes()));
    }

    @Override
    public int compareTo(BroadcastsTime t) {
        if (after(t)) return 1;
        if (before(t)) return -1;
        return 0;
    }
}
