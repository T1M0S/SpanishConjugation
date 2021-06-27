package sample;

import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

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
        checkButton.setOnMousePressed(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                printWord();
            }
        });
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
        answerLabel.setText("A");
        answerLabel.setText("B");
    }

    private void alignLabel(Label labelName) {
        labelName.setMaxWidth(Double.MAX_VALUE);
        AnchorPane.setLeftAnchor(labelName, 0.0);
        AnchorPane.setRightAnchor(labelName, 0.0);
        labelName.setAlignment(Pos.CENTER);
    }

}
