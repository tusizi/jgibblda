package extra;

import extra.argument.Argument;
import extra.entity.Vocabulary;
import extra.reader.ThetaReader;
import extra.reader.TopicReader;
import extra.utils.MapSortUtil;
import extra.writer.AdvanceTopicWriter;
import org.kohsuke.args4j.CmdLineException;
import org.kohsuke.args4j.CmdLineParser;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class AdvanceCombineTopics4Document {
    public static void main(String[] args) {
        Argument argument = new Argument();
        CmdLineParser parser = new CmdLineParser(argument);
        if (args.length == 0) {
            printMessage(parser);
            return;
        }

        try {
            parser.parseArgument(args);
            List<List<Double>> probabilities = new ThetaReader(argument).read();
            List<List<Vocabulary>> topics = new TopicReader(argument).read();

            List<Map<String, Double>> combinedProbabilities = new ArrayList<Map<String, Double>>();

            for (List<Double> preProbabilities : probabilities) {
                Map<String, Double> wordAndProbabilities = new HashMap<String, Double>();
                for (int i = 0; i < preProbabilities.size(); i++) {
                    double currentPercent = preProbabilities.get(i);
                    List<Vocabulary> vocabularies = topics.get(i);
                    for (Vocabulary vocabulary : vocabularies) {
                        double newPercent = vocabulary.getPercent() * currentPercent;
                        if (wordAndProbabilities.containsKey(vocabulary.getWord())) {
                            wordAndProbabilities.put(vocabulary.getWord(),
                                    wordAndProbabilities.get(vocabulary.getWord()) + newPercent);
                        } else {
                            wordAndProbabilities.put(vocabulary.getWord(), newPercent);
                        }
                    }
                }
                Map<String, Double> sortedProbabilities = MapSortUtil.sortByValue(wordAndProbabilities);
                combinedProbabilities.add(sortedProbabilities);
            }

            new AdvanceTopicWriter(argument).write(combinedProbabilities);

        } catch (CmdLineException e) {
            System.out.println("Command line error: " + e.getMessage());
            printMessage(parser);
            return;
        }
    }

    private static void printMessage(CmdLineParser parser) {
        System.out.println("Advance Combine Topics 4 Document [options ...] [arguments...]");
        parser.printUsage(System.out);
    }
}
