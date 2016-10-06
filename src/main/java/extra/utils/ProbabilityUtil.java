package extra.utils;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ProbabilityUtil {
    public static int getMaxPosition(List<Double> probabilities) {
        List list = new ArrayList(probabilities.size());
        list.addAll(probabilities);

        Collections.sort(list);
        Collections.reverse(list);

        return probabilities.indexOf(list.get(0));
    }
}
