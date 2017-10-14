package nl.tue.algogeo;

import java.util.*;

public class DotLabelSet extends ArrayList<DotLabel> {

    private static final DotLabel[] LABELS = {
            new DotLabel(Color.RED, "Red"),
            new DotLabel(Color.GREEN, "Green"),
            new DotLabel(Color.BLUE, "Blue"),
            new DotLabel(Color.ORANGE, "Orange"),
            new DotLabel(Color.BLACK, "Black")
    };


    /**
     * Create a set with n labels
     * @param n number of labels, should be at most 5
     * @return returns a set n labels
     */
    public static DotLabelSet create(int n) {
        DotLabelSet set = new DotLabelSet();

        for (int i = 0; i < Math.min(n, LABELS.length); i++) {
            set.add(LABELS[i]);
        }

        return set;
    }
}
