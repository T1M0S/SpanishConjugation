package sample;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class Tense {
    private static final List<String> tenses = new ArrayList<>(Arrays.asList("Future", "Preterite",
            "Present", "Imperfect", "Conditional"));

    public String getRandomTense() {
        return tenses.get(new Random().nextInt(tenses.size()));
    }
}
