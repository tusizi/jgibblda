package extra.utils;

import java.util.*;

public class MapSortUtil {
    public static Map<String, Double> sortByValue(Map<String, Double> unsortMap) {
        List<Map.Entry<String, Double>> list =
                new LinkedList<Map.Entry<String, Double>>(unsortMap.entrySet());
        Collections.sort(list, new Comparator<Map.Entry<String, Double>>() {
            public int compare(Map.Entry<String, Double> o1,
                               Map.Entry<String, Double> o2) {
                return (o2.getValue()).compareTo(o1.getValue());
            }
        });

        Map<String, Double> sortedMap = new LinkedHashMap<String, Double>();
        for (Map.Entry<String, Double> entry : list) {
            sortedMap.put(entry.getKey(), entry.getValue());
        }
        return sortedMap;
    }

    public static void main(String[] args) {
        Map<String, Double> map = new HashMap<String, Double>();
        map.put("a", 0.4);
        map.put("b", 0.5);
        map.put("c", 0.3);

        System.out.println(map);

        Map<String, Double> stringDoubleMap = sortByValue(map);

        System.out.println(stringDoubleMap);
    }
}
