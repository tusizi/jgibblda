package extra.entity;

import java.util.ArrayList;
import java.util.List;

public class Vocabulary {
    private String word;
    private Double percent;

    public Vocabulary(String word, Double percent) {
        this.word = word;
        this.percent = percent;
    }

    @Override
    public String toString() {
        return "Vocabulary{" +
                "word='" + word + '\'' +
                ", percent=" + percent +
                '}' + "\n";
    }

    public List<Object> flatten() {
        ArrayList<Object> objects = new ArrayList<Object>();
        objects.add(this.word);
        objects.add(this.percent);
        return objects;
    }
}
