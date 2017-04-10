package com.koklobok.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.TextArea;

/**
 * @author Roman.Holiuk
 */
public class MainStage {

    public TextArea textExpressions;
    public TextArea textResults;
    
    
    @FXML
    public void initialize() {
        textExpressions.textProperty().addListener((observable, oldValue, newValue) -> {
            System.out.println("New text value: " + newValue);
        });
    }
}
