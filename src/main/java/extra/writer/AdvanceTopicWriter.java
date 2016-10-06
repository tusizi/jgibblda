package extra.writer;

import com.google.gson.Gson;
import extra.argument.Argument;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class AdvanceTopicWriter {
    private Argument argument;

    public AdvanceTopicWriter(Argument argument) {
        this.argument = argument;
    }

    public void write(List<Map<String, Double>> combinedProbabilities) {
        String topicsFile = argument.dir + File.separator + argument.atopicFile;
        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter(topicsFile));
            for (Map<String, Double> probabilities : combinedProbabilities) {
                List<List<Object>> formattedVocabularies = new ArrayList<List<Object>>();
                int index = 0;
                for (final Map.Entry<String, Double> entry : probabilities.entrySet()) {
                    if (index == argument.atwords) {
                        writer.write(new Gson().toJson(formattedVocabularies));
                        writer.write("\n");
                        break;
                    }
                    List<Object> objects = new ArrayList<Object>();
                    objects.add(entry.getKey());
                    objects.add(entry.getValue());
                    formattedVocabularies.add(objects);
                    index++;
                }
            }
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
