package com.example.akalo_chomsky.models;

import java.util.List;
import java.util.Stack;

public class Validate {
    private boolean isValid;
    private List<Stack<String>> cases;

    public Validate(boolean isValid, List<Stack<String>> cases) {
        this.isValid = isValid;
        this.cases = cases;
    }

    public boolean isValid() {
        return isValid;
    }

    public List<Stack<String>> getCases() {
        return cases;
    }

}