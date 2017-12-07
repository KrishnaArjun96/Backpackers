package JavaBeans;

import java.util.Comparator;
import java.util.function.Function;
import java.util.function.ToDoubleFunction;
import java.util.function.ToIntFunction;
import java.util.function.ToLongFunction;

/**
 * Created by Rahul on 12/07/17.
 */
public class OptionComparator implements Comparator<Option> {
    @Override
    public int compare(Option o1, Option o2) {
        if(o1.getTotalDuration() > o2.getTotalDuration()) return -1;
        else if (o1.getTotalDuration() == o2.getTotalDuration()) return 0;
        else return 1;
    }
}
