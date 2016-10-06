package extra.reader;

import extra.argument.Argument;

import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.StringTokenizer;

public class TopicReader {
    private Argument argument;

    public TopicReader(Argument argument) {
        this.argument = argument;
    }

    public List<Integer> read() {
        try {
            List<List<Double>> totalProbabilities = new ArrayList<List<Double>>();
            String thetaFile = argument.dir + File.separator + argument.thetaFile;
            BufferedReader bufferedReader = new BufferedReader(new FileReader(thetaFile));
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                StringTokenizer stringTokenizer = new StringTokenizer(line);
                List<Double> perProbabilities = new ArrayList<Double>();
                while (stringTokenizer.hasMoreElements()) {
                    perProbabilities.add(Double.parseDouble(stringTokenizer.nextToken()));
                }
                totalProbabilities.add(perProbabilities);
            }
            List<Integer> maxPositions = new ArrayList<Integer>();
            for (List<Double> probabilities : totalProbabilities) {
                maxPositions.add(getMaxPosition(probabilities));
            }

            return maxPositions;

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return new ArrayList<Integer>();
    }

    public int getMaxPosition(List<Double> probabilities) {
        List list = new ArrayList(probabilities.size());
        list.addAll(probabilities);

        Collections.sort(list);
        Collections.reverse(list);

        return probabilities.indexOf(list.get(0));
    }
}
