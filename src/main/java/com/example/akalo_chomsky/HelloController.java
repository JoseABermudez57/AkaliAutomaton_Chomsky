package com.example.akalo_chomsky;

import com.example.akalo_chomsky.models.Automaton;
import com.example.akalo_chomsky.models.Validate;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.TextArea;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;

import java.util.Stack;


public class HelloController {

    Automaton automaton = new Automaton();

    @FXML
    private TextField cicleTextArea;

    @FXML
    private Label ciclesLabel;

    @FXML
    private TextField conditionTextArea;

    @FXML
    private Label conditionsLabel;

    @FXML
    private TextField functionTextArea;

    @FXML
    private Label functionsLabel;

    @FXML
    private TextArea stackState;

    @FXML
    private TextField variableTextArea;

    @FXML
    private Label variablesLabel;


    @FXML
    void deleteAllText(MouseEvent event) {
        variablesLabel.setText("");
        conditionsLabel.setText("");
        functionsLabel.setText("");
        ciclesLabel.setText("");
        functionTextArea.setText("");
        variableTextArea.setText("");
        cicleTextArea.setText("");
        conditionTextArea.setText("");
        stackState.setText("");
    }

    @FXML
    void validateCicle(MouseEvent event) {
        String resultText = "";

        Validate result = automaton.evaluate(cicleTextArea.getText(), "GC");
        setIsValid(ciclesLabel, result.isValid());
        stackState.setText(result.getCases().toString());
    }

    @FXML
    void validateCondition(MouseEvent event) {
        String resultText = "";
        Validate result = automaton.evaluate(conditionTextArea.getText(), "GCT");
        setIsValid(conditionsLabel, result.isValid());
        for (String state : result.getCases()) {
            resultText += state + "\n";
        }
        stackState.setText(resultText);
    }

    @FXML
    void validateFunction(MouseEvent event) {
        String resultText = "";
        Validate result = automaton.evaluate(functionTextArea.getText(), "GF");
        setIsValid(functionsLabel, result.isValid());
        for (String state : result.getCases()) {
            resultText += state + "\n";
        }
        stackState.setText(resultText);
    }

    @FXML
    void validateVariable(MouseEvent event) {
        String resultText = "";
        Validate result = automaton.evaluate(variableTextArea.getText(), "GV");
        setIsValid(variablesLabel, result.isValid());
        for (String state : result.getCases()) {
            resultText += state + "\n";
        }
        stackState.setText(resultText);
    }

    private void setIsValid (Label textLabel, boolean valid){
        if (valid) {
            textLabel.setTextFill(Color.valueOf("#58ce74"));
            textLabel.setText("Cadena valida");
        } else {
            textLabel.setTextFill(Color.valueOf("#ae0c3e"));
            textLabel.setText("Cadena invalida");
        }
    }
}