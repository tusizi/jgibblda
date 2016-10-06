package extra.reader;

import extra.argument.Argument;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class ThetaReader {
    private Argument argument;

    public ThetaReader(Argument argument) {
        this.argument = argument;
    }

    public List<List<Double>> read() {
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

            return totalProbabilities;

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return new ArrayList<List<Double>>();
    }

}
