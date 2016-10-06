package extra.reader;

import extra.argument.Argument;
import extra.entity.Vocabulary;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class TopicReader {
    private Argument argument;

    public TopicReader(Argument argument) {
        this.argument = argument;
    }

    public List<List<Vocabulary>> read() {
        try {
            List<List<Vocabulary>> totalVocabularies = new ArrayList<List<Vocabulary>>();
            String twordsFile = argument.dir + File.separator + argument.twordsFile;
            BufferedReader bufferedReader = new BufferedReader(new FileReader(twordsFile));
            for (int i = 0; i < argument.ntopics; i++) {
                bufferedReader.readLine();
                List<Vocabulary> perTopicVacabularies = new ArrayList<Vocabulary>();
                for (int j = 0; j < argument.twords; j++) {
                    String line = bufferedReader.readLine();
                    StringTokenizer stringTokenizer = new StringTokenizer(line);
                    Vocabulary v = new Vocabulary(stringTokenizer.nextToken(), Double.parseDouble(stringTokenizer.nextToken()));
                    perTopicVacabularies.add(v);
                }
                totalVocabularies.add(perTopicVacabularies);
            }
            return totalVocabularies;

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return new ArrayList<List<Vocabulary>>();
    }
}
