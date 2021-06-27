package sample;

import javafx.fxml.FXML;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Controller {
    @FXML
    public Label wordLabel;
    @FXML
    public Label pronounLabel;
    @FXML
    public Label tenseLabel;
    @FXML
    public Button checkButton;
    @FXML
    public Label answerLabel;

    List<String> words = new ArrayList<>();

    public Controller() {
    }

    public void fillList() {
        try {
            BufferedReader reader = new BufferedReader(new FileReader(new File("words.txt")));
            String line = reader.readLine();
            while (line != null) {
                words.add(line);
                line = reader.readLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    public void initialize() {
        fillList();
        printWord();
        checkButton.setOpacity(0);
        checkButton.setOnMousePressed(mouseEvent -> printWord());
    }

    public void printWord() {
        for (Label label : Arrays.asList(pronounLabel, tenseLabel, wordLabel, answerLabel)) {
            alignLabel(label);
        }

        if (answerLabel.getText() != null) {
            answerLabel.setText(null);
            wordLabel.setText(words.get(new Random().nextInt(words.size())));
            pronounLabel.setText(new Pronoun().getRandomPronoun());
            tenseLabel.setText(new Tense().getRandomTense());
        } else {
            setAnswer();
        }

    }

    private void setAnswer() {
        String answer = "";
        String word = wordLabel.getText();
        Pattern pattern = Pattern.compile("(\\w*)[a,ei][r]");
        Matcher matcher = pattern.matcher(word);
        matcher.find();

        switch (word.substring(matcher.end() - 2, matcher.end())) {
            case "ar" -> {
                switch (tenseLabel.getText()) {
                    case "Future" -> answer = futureCase(answer, word, matcher);
                    case "Preterite" -> {
                        switch (pronounLabel.getText()) {
                            case "yo" -> answer = word.substring(0, matcher.end() - 2) + "e'";
                            case "tu" -> answer = word.substring(0, matcher.end() - 2) + "aste";
                            case "el", "ella", "usted" -> answer = word.substring(0, matcher.end() - 2) + "o'";
                            case "nosotros" -> answer = word.substring(0, matcher.end() - 2) + "amos";
                            case "vosotros" -> answer = word.substring(0, matcher.end() - 2) + "asteis";
                            case "ellos", "ellas", "ustedes" -> answer = word.substring(0, matcher.end() - 2) + "aron";
                        }
                    }
                    case "Present" -> {
                        switch (pronounLabel.getText()) {
                            case "yo" -> answer = word.substring(0, matcher.end() - 2) + "o";
                            case "tu" -> answer = word.substring(0, matcher.end() - 2) + "as";
                            case "el", "ella", "usted" -> answer = word.substring(0, matcher.end() - 2) + "a";
                            case "nosotros" -> answer = word.substring(0, matcher.end() - 2) + "amos";
                            case "vosotros" -> answer = word.substring(0, matcher.end() - 2) + "ai's";
                            case "ellos", "ellas", "ustedes" -> answer = word.substring(0, matcher.end() - 2) + "an";
                        }
                    }
                    case "Imperfect" -> {
                        switch (pronounLabel.getText()) {
                            case "yo", "el", "ella", "usted" -> answer = word.substring(0, matcher.end() - 2) + "aba";
                            case "tu" -> answer = word.substring(0, matcher.end() - 2) + "abas";
                            case "nosotros" -> answer = word.substring(0, matcher.end() - 2) + "a'bamos";
                            case "vosotros" -> answer = word.substring(0, matcher.end() - 2) + "abais";
                            case "ellos", "ellas", "ustedes" -> answer = word.substring(0, matcher.end() - 2) + "aban";
                        }
                    }
                    case "Conditional" -> answer = ConditionalCase(answer, word, matcher);
                }
            }
            case "er" -> {
                switch (tenseLabel.getText()) {
                    case "Future" -> answer = futureCase(answer, word, matcher);
                    case "Preterite" -> answer = PreteriteCase(answer, word, matcher);
                    case "Present" -> {
                        switch (pronounLabel.getText()) {
                            case "yo" -> answer = word.substring(0, matcher.end() - 2) + "o";
                            case "tu" -> answer = word.substring(0, matcher.end() - 2) + "es";
                            case "el", "ella", "usted" -> answer = word.substring(0, matcher.end() - 2) + "e";
                            case "nosotros" -> answer = word.substring(0, matcher.end() - 2) + "emos";
                            case "vosotros" -> answer = word.substring(0, matcher.end() - 2) + "e'is";
                            case "ellos", "ellas", "ustedes" -> answer = word.substring(0, matcher.end() - 2) + "en";
                        }
                    }
                    case "Imperfect" -> {
                        switch (pronounLabel.getText()) {
                            case "yo", "ella", "usted", "el" -> answer = word.substring(0, matcher.end() - 2) + "i'a";
                            case "tu" -> answer = word.substring(0, matcher.end() - 2) + "i'as";
                            case "nosotros" -> answer = word.substring(0, matcher.end() - 2) + "i'amos";
                            case "vosotros" -> answer = word.substring(0, matcher.end() - 2) + "i'ais";
                            case "ellos", "ellas", "ustedes" -> answer = word.substring(0, matcher.end() - 2) + "i'an";
                        }
                    }
                    case "Conditional" -> answer = ConditionalCase(answer, word, matcher);
                }
            }
            case "ir" -> {
                switch (tenseLabel.getText()) {
                    case "Future" -> answer = futureCase(answer, word, matcher);
                    case "Preterite" -> answer = PreteriteCase(answer, word, matcher);
                    case "Present" -> {
                        switch (pronounLabel.getText()) {
                            case "yo" -> answer = word.substring(0, matcher.end() - 2) + "o";
                            case "tu" -> answer = word.substring(0, matcher.end() - 2) + "es";
                            case "el", "ella", "usted" -> answer = word.substring(0, matcher.end() - 2) + "e";
                            case "nosotros" -> answer = word.substring(0, matcher.end() - 2) + "imos";
                            case "vosotros" -> answer = word.substring(0, matcher.end() - 2) + "i's";
                            case "ellos", "ellas", "ustedes" -> answer = word.substring(0, matcher.end() - 2) + "en";
                        }
                    }
                    case "Imperfect" -> {
                        switch (pronounLabel.getText()) {
                            case "yo", "el", "ella", "usted" -> answer = word.substring(0, matcher.end() - 2) + "i'a";
                            case "tu" -> answer = word.substring(0, matcher.end() - 2) + "i'as";
                            case "nosotros" -> answer = word.substring(0, matcher.end() - 2) + "i'amos";
                            case "vosotros" -> answer = word.substring(0, matcher.end() - 2) + "i'ais";
                            case "ellos", "ellas", "ustedes" -> answer = word.substring(0, matcher.end() - 2) + "i'an";
                        }
                    }
                    case "Conditional" -> answer = ConditionalCase(answer, word, matcher);
                }
            }
        }

        answerLabel.setText(answer);
    }

    private String ConditionalCase(String answer, String word, Matcher matcher) {
        switch (pronounLabel.getText()) {
            case "yo", "el", "ella", "usted" -> answer = word.substring(0, matcher.end()) + "i'a";
            case "tu" -> answer = word.substring(0, matcher.end()) + "i'as";
            case "nosotros" -> answer = word.substring(0, matcher.end()) + "i'amos";
            case "vosotros" -> answer = word.substring(0, matcher.end()) + "i'ais";
            case "ellos", "ellas", "ustedes" -> answer = word.substring(0, matcher.end()) + "i'an";
        }
        return answer;
    }

    private String PreteriteCase(String answer, String word, Matcher matcher) {
        switch (pronounLabel.getText()) {
            case "yo" -> answer = word.substring(0, matcher.end() - 2) + "i'";
            case "tu" -> answer = word.substring(0, matcher.end() - 2) + "iste";
            case "el", "ella", "usted" -> answer = word.substring(0, matcher.end() - 2) + "io'";
            case "nosotros" -> answer = word.substring(0, matcher.end() - 2) + "imos";
            case "vosotros" -> answer = word.substring(0, matcher.end() - 2) + "isteis";
            case "ellos", "ellas", "ustedes" -> answer = word.substring(0, matcher.end() - 2) + "ieron";
        }
        return answer;
    }

    private String futureCase(String answer, String word, Matcher matcher) {
        switch (pronounLabel.getText()) {
            case "yo" -> answer = word.substring(0, matcher.end()) + "e'";
            case "tu" -> answer = word.substring(0, matcher.end()) + "a's";
            case "el", "ella", "usted" -> answer = word.substring(0, matcher.end()) + "a'";
            case "nosotros" -> answer = word.substring(0, matcher.end()) + "emos";
            case "vosotros" -> answer = word.substring(0, matcher.end()) + "e'is";
            case "ellos", "ellas", "ustedes" -> answer = word.substring(0, matcher.end()) + "a'n";
        }
        return answer;
    }

    private void alignLabel(Label labelName) {
        labelName.setMaxWidth(Double.MAX_VALUE);
        AnchorPane.setLeftAnchor(labelName, 0.0);
        AnchorPane.setRightAnchor(labelName, 0.0);
        labelName.setAlignment(Pos.CENTER);
    }

}
