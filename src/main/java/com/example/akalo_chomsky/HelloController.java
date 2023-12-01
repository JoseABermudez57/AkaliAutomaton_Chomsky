package com.example.akalo_chomsky;

import com.example.akalo_chomsky.models.Automaton;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.TextArea;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;


public class HelloController {

    Automaton automaton = new Automaton();


    @FXML
    private TextField cicleTextArea;

    @FXML
    private TextField conditionTextArea;

    @FXML
    private TextField functionTextArea;

    @FXML
    private Label textLabel;

    @FXML
    private TextField variableTextArea;

    @FXML
    private TextArea stackState;

    @FXML
    void deleteAllText(MouseEvent event) {
        textLabel.setText("");
        functionTextArea.setText("");
        variableTextArea.setText("");
        cicleTextArea.setText("");
        conditionTextArea.setText("");
        stackState.setText("");
    }

    @FXML
    void validateCicle(MouseEvent event) {

    }

    @FXML
    void validateCondition(MouseEvent event) {

    }

    @FXML
    void validateFunction(MouseEvent event) {

    }

    @FXML
    void validateVariable(MouseEvent event) {
        String result = automaton.evaluate();
        // Green = "#58ce74"
        // Red = #ae0c3e
        textLabel.setTextFill(Color.valueOf("#58ce74"));
        textLabel.setText(result);
        stackState.setText("Pila = [] ");
    }
}