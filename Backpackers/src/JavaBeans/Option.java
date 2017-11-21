package JavaBeans;

import java.util.ArrayList;

/**
 * Created by Rahul on 11/21/17.
 */
public class Option {
    ArrayList<Leg> legs;
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
}
