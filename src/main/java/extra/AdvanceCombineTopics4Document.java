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
        for probabilities <- totalProbabilities {

            //定义可以由文字做key的map
            Map<String, Double> wordMap

            //循环每一个文章里面的每一个权重值
            for index,probability <- probabilities{

                //获取当前文章权重值对应的topic
                relatedTopic <- articleTopics[i]

                //循环topic每一个话题,并与当前的文章对应topic权重值相乘再相加
                for word,percent <- relatedTopic {
                    //如果wordMap里面有就加和
                    if wordMap has key word
                       wordMap[word] <- wordMap[word] + probability * percent
                    //如果没有就放进去
                    else
                       wordMap[word] <-   probability * percent
                }
            }

            //按照wordMap的value大到小排序,并输出
            Output sorted word map
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
