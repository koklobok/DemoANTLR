package com.koklobok.controllers;

import com.koklobok.model.BooleanStatement;
import com.koklobok.parser.BooleanStatementParser;
import com.koklobok.views.FalseCircle;
import com.koklobok.views.TrueCircle;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.fxml.FXML;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextFormatter;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Circle;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author Roman.Holiuk
 */
public class MainStage {
    private final static String LINE_DELIMITER = "\n";
    private static final int MAX_LINES = 20;

    public TextArea textExpressions;
    public Pane paneCircles;

    @FXML
    public void initialize() {
        textExpressions.setTextFormatter(createTextFormatter());
        textExpressions.textProperty().addListener((observable, oldValue, newValue) -> {
            String[] lines = newValue.split(LINE_DELIMITER);
            BooleanStatementParser parser = new BooleanStatementParser();
            paneCircles.getChildren().clear();
            int length = Math.min(lines.length, MAX_LINES);
            for (int i = 0; i < length; i++) {
                BooleanStatement statement = parser.parse(lines[i]);
                if (statement.isValid()) {
                    int lineNumber = i + 1;
                    Circle circle = statement.evaluate() ? new TrueCircle(lineNumber) : new FalseCircle(lineNumber);
                    paneCircles.getChildren().add(circle);
                }
            }

        });
    }

    private static <T> TextFormatter<T> createTextFormatter() {

        final IntegerProperty lines = new SimpleIntegerProperty(1);

        return new TextFormatter<>(change -> {
            int linesChange = countLinesString(change.getText());
            if (change.isAdded()) {
                lines.set(lines.get() + linesChange);
            } else if (change.isDeleted()) {
                lines.set(lines.get() - linesChange);
            }

            if (lines.get() > MAX_LINES) {
                change.setText("");
                lines.set(MAX_LINES);
            }
            return change;
        });
    }

    private static int countLinesString(String text) {
        Matcher m = Pattern.compile("\r?\n").matcher(text);
        int lines = 0;
        while (m.find()) {
            lines++;
        }
        return lines;
    }
}
