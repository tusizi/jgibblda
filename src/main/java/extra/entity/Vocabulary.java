package extra.entity;

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
                '}';
    }
}
