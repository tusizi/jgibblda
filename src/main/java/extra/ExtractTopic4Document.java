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

    /*
        //读入theta文件
        Input model-final.theta

        //读入话题文件文件
        Input model-final.twords

         // 解析每行浮点数的权重值放到二维集合类里面
        List<List<Double>> totalProbabilities <- extract model-final.theta

        //解析每一个话题
        List<List<Vocabulary>> articleTopics <- extract model-final.twords

        //循环每一个文章的权重列表
        for index, probabilities <- totalProbabilities {

            //把一个文章里面的权重值从大到小排序
            tempProbabilities <- sort probabilities

            //获取最大的权重值
            maxProbability <- tempProbabilities.max

            // 获取最大的权重值在源列表中的位置
            position <- probabilities.indexOf(maxProbability)

            //通过当前的循环index和位置position得到当前文章的最关联的topic
            topic <- articleTopics[index][position]

            //输出当前文章的topic
            Output topic
        }

        //话题里面每一个词的内容和权重的类
        class Vocabulary {
            具体的词汇名字
            name

            当前词在topic中的权重值
            percent
        }
     */
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
