package com.example.akalo_chomsky.models;

import java.util.*;
import java.util.regex.Pattern;

public class Automaton {
    private static final HashMap<String, String> productions = new HashMap<>();

    static {
        productions.put("GV", "TD V I VA"); // Variable
        productions.put("GCT", "CN PA CD PC MY C MN"); // Conditional
        productions.put("GF", "TD V ME PR MA MY C RT MN"); // Functions
        productions.put("GC", "F PA CDF PC MY C MN"); // Cycle
        productions.put("TD", "(Fnc|Ent|Cdn|Bool|Dcm)");
        productions.put("V", "[a-zA-Z]+");
        productions.put("PR", "([a-zA-Z]|^$)+");
        productions.put("D", "[0-9]");
        productions.put("I", "=");
        productions.put("CN", "if");
        productions.put("F", "for");
        productions.put("PA", "^\\($");
        productions.put("PC", "^\\)$");
        productions.put("CD", "^[a-zA-Z0-9_]+(>=|<=|==|!=|<|>)[a-zA-Z0-9_]+$");
        productions.put("CDF", "D SS");
        productions.put("SS", "SE TC");
        productions.put("SE", "^\\|$");
        productions.put("TC", "D UC");
        productions.put("UC", "SE O");
        productions.put("O", "^[+-]$");
        productions.put("S", "(>=|<=|==|!=|<|>)");
        productions.put("MY", "=>");
        productions.put("MA", "^\\>$");
        productions.put("ME", "^\\<$");
        productions.put("MN", "<=");
        productions.put("C", "\\w+");
        productions.put("RT", "RTN V");
        productions.put("RTN", "return");
        productions.put("","");
    }

    private static String getProduction(String symbol) {
        return productions.get(symbol);
    }

    private static boolean isTerminal(String symbol) {
        return !productions.containsKey(symbol);
    }

    private boolean isValidType(String word, String dataType) {
        switch (dataType) {
            case "Ent" -> {
                if (Pattern.matches("\\d+", word)) return true;
            }
            case "Cdn" -> {
                if (Pattern.matches("\".*\"", word)) return true;
            }
            case "Dec" -> {
                if (Pattern.matches("\\d+\\.\\d+", word)) return true;
            }
            case "Bool" -> {
                if (Pattern.matches("(true|false)", word)) return true;
            }
            default -> {
                return false;
            }
        }
        return false;
    }

    public Validate evaluate (String text, String rule) {
        String dataType = "";
        Stack<String> stack = new Stack<>();
        Stack<String> states = new Stack<>();

        stack.push(rule);
        states.push(String.valueOf(stack));
        int i = 0;

        String X;
        String [] words = text.split("\\s+");

        while (!stack.isEmpty()) {
            X = stack.peek();

            if (isTerminal(X)) { // X is terminal

                if (X != null && Pattern.matches(X, String.valueOf(words[i]))) {
                    if (X.equals("(Fnc|Ent|Cdn|Bool|Dcm)")) dataType = words[i];
                    stack.pop();
                    states.push(String.valueOf(stack));
                    i++;
                } else {
                    if (X.equals("VA") && isValidType(words[i], dataType)) {
                        stack.pop();
                        states.push(String.valueOf(stack));
                        return new Validate(true, states);
                    }
                    return new Validate(false, states);
                }
            } else { // X is not terminal
                if (getProduction(X) != null) {
                    stack.pop();
                    String production = getProduction(X);
                    String[] symbols = production.split("\\s+");
                    for (int j = symbols.length - 1; j >= 0; j--) {
                        stack.push(symbols[j]);
                    }
                    states.push(String.valueOf(stack));
                } else {
                    return new Validate(false, states);
                }
            }
        }
        return new Validate(true, states);
    }
}