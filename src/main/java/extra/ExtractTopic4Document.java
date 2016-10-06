package extra;

import extra.argument.Argument;
import extra.entity.Vocabulary;
import extra.reader.ThetaReader;
import extra.reader.TopicReader;
import extra.utils.ProbabilityUtil;
import extra.writer.TopicWriter;
import org.kohsuke.args4j.CmdLineException;
import org.kohsuke.args4j.CmdLineParser;

import java.util.ArrayList;
import java.util.List;

public class ExtractTopic4Document {
    public static void main(String[] args) {
        Argument argument = new Argument();
        CmdLineParser parser = new CmdLineParser(argument);
        if (args.length == 0) {
            printMessage(parser);
            return;
        }

        try {
            parser.parseArgument(args);
            List<List<Double>> totalProbabilities = new ThetaReader(argument).read();

            List<Integer> maxPositions = new ArrayList<Integer>();
            for (List<Double> probabilities : totalProbabilities) {
                maxPositions.add(ProbabilityUtil.getMaxPosition(probabilities));
            }

            List<List<Vocabulary>> topics = new TopicReader(argument).read();

            List<List<Vocabulary>> articleTopics = new ArrayList<List<Vocabulary>>();

            for (Integer position : maxPositions) {
                articleTopics.add(topics.get(position));
            }

            new TopicWriter(argument).write(articleTopics);

        } catch (CmdLineException e) {
            System.out.println("Command line error: " + e.getMessage());
            printMessage(parser);
            return;
        }
    }

    private static void printMessage(CmdLineParser parser) {
        System.out.println("Extract Topic 4 Document [options ...] [arguments...]");
        parser.printUsage(System.out);
    }
}
