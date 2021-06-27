package sample;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class Pronoun {
    private static final List<String> pronouns = new ArrayList<>(Arrays.asList("yo", "tu", "el", "ella", "usted",
            "nosotros", "vosotros", "ellos", "ellas", "ustedes"));

    public String getRandomPronoun() {
        return pronouns.get(new Random().nextInt(pronouns.size()));
    }
}
