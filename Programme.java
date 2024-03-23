package final_test;

public class Programme {
    private String channel;
    private BroadcastsTime time;
    private String name;
    Programme (String channel, String time, String name) throws Exception{
        this.channel = channel;
        this.time = new BroadcastsTime(time);
        this.name = name;
    }
    public String toString() {
        return "Programme of channel " + channel + ", streaming time " + time + ", name " + name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public BroadcastsTime getTime() {
        return time;
    }

    public void setTime(BroadcastsTime time) {
        this.time = time;
    }

    public String getChannel() {
        return channel;
    }

    public void setChannel(String channel) {
        this.channel = channel;
    }
}
