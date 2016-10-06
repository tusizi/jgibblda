package extra;

import extra.argument.Argument;
import extra.entity.Vocabulary;
import extra.reader.TopicReader;
import org.kohsuke.args4j.CmdLineException;
import org.kohsuke.args4j.CmdLineParser;
import extra.reader.ThetaReader;

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
            List<Integer> maxPositions = new ThetaReader(argument).read();
            List<List<Vocabulary>> topics = new TopicReader(argument).read();
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
