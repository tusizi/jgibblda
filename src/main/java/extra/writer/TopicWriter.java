package extra.writer;

import com.google.gson.Gson;
import extra.argument.Argument;
import extra.entity.Vocabulary;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class TopicWriter {
    private Argument argument;

    public TopicWriter(Argument argument) {
        this.argument = argument;
    }

    public void write(List<List<Vocabulary>> topicVocabularies) {
        String topicsFile = argument.dir + File.separator + argument.topicsFile;
        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter(topicsFile));
            for (List<Vocabulary> vocabularies : topicVocabularies) {

                List<List<Object>> formattedVocabularies = new ArrayList<List<Object>>();

                for (Vocabulary vocabulary : vocabularies) {
                    formattedVocabularies.add(vocabulary.flatten());
                }

                writer.write(new Gson().toJson(formattedVocabularies));
                writer.write("\n");
            }
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
