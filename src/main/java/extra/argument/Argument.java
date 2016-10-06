package extra.argument;

import org.kohsuke.args4j.Option;

public class Argument {

    @Option(name = "-dir", usage = "Specify directory")
    public String dir = "";

    @Option(name = "-datopicsFile", usage = "Specify output each document advance topic file")
    public String atopicFile = "model-final.atopics";

    @Option(name = "-dtopicsFile", usage = "Specify output each document topic file")
    public String topicsFile = "model-final.topics";

    @Option(name = "-dthetaFile", usage = "Specify theta file")
    public String thetaFile = "model-final.theta";

    @Option(name = "-dtwordsFile", usage = "Specify twords file")
    public String twordsFile = "model-final.twords";

    @Option(name = "-ntopics", usage = "Specify the number of topics")
    public int ntopics = 100;

    @Option(name = "-twords", usage = "Specify the number of most likely words to be printed for each topic")
    public int twords = 100;

    @Option(name = "-tawords", usage = "Specify the number of most likely advance words to be printed for each document")
    public int atwords = 100;
}
