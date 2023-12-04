package com.example.akalo_chomsky.models;

import java.util.Stack;

public class Validate {
    private boolean isValid;
    private Stack<String> cases;

    public Validate(boolean isValid, Stack<String> cases) {
        this.isValid = isValid;
        this.cases = cases;
    }

    public boolean isValid() {
        return isValid;
    }

    public Stack<String> getCases() {
        return cases;
    }

}