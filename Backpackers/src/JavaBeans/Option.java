package JavaBeans;

import java.util.ArrayList;

/**
 * Created by Rahul on 11/21/17.
 */
public class Option {
    ArrayList<Leg> legs;
    int totalDuration;
    int[] layoves;
    String[] dates;
    //MORE ATTRIBUTES TO FOLLOW.

    public Option() {
        legs = new ArrayList<>();
    }

    public ArrayList<Leg> getLegs() {
        return legs;
    }

    public void setLegs(ArrayList<Leg> legs) {
        this.legs = legs;
    }

    public int getTotalDuration() {
        return totalDuration;
    }

    public void setTotalDuration(int totalDuration) {
        this.totalDuration = totalDuration;
    }

    public int[] getLayovers() {
        return layoves;
    }

    public void setLayovers(int[] layoves) {
        this.layoves = layoves;
    }

    public String[] getDates() {
        return dates;
    }

    public void setDates(String[] dates) {
        this.dates = dates;
    }
}
