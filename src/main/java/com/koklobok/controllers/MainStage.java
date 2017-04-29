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

/**
 * @author Roman.Holiuk
 */
public class MainStage {
    private final static String LINE_DELIMITER = "\n";

    public TextArea textExpressions;
    public Pane paneCircles;

    @FXML
    public void initialize() {
        textExpressions.setTextFormatter(createTextFormatter());
        textExpressions.textProperty().addListener((observable, oldValue, newValue) -> {
            String[] lines = newValue.split(LINE_DELIMITER);
            BooleanStatementParser parser = new BooleanStatementParser();
            paneCircles.getChildren().clear();
            for (int i = 0; i < lines.length; i++) {
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
            if (change.isAdded()) {
                if (change.getText().indexOf('\n') > -1) {
                    lines.set(lines.get() + 1);
                }
            } else if (change.isDeleted())
            if (lines.get() > 20) {
                change.setText("");
            }
            return change;
        });
    }
}
