package com.example.akalo_chomsky.models;

import java.util.HashMap;
import java.util.Stack;
import java.util.regex.Pattern;

public class Automaton {
    private static final HashMap<String, String> productions = new HashMap<>();

    static {
        productions.put("GV", "TD V I VA | TD V");
        productions.put("TD", "(Ent|Cdn|Bool|Dcm)");
        productions.put("V", "[a-zA-Z]+");
        productions.put("RL", "L RL | D RDL | ε");
        productions.put("RDL", "D RD RL");
        productions.put("D", "[0-9]");
        productions.put("RD", "(D RD|ε)");
        productions.put("I", "=");
        productions.put("","");
        productions.put("VCAD", "V CO");
        productions.put("VDEC", "RD RVDEC");
        productions.put("RVDEC", "P DVDEC");
        productions.put("DVDEC", "D RD");
        productions.put("CO", "\"");
        productions.put("P", "\\.");
        productions.put("B", "(true|false)");
    }

    public static String getProduction(String symbol) {
        return productions.get(symbol);
    }

    public static boolean isTerminal(String symbol) {
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

    public String evaluate () {
        String string = "Ent variable = 1";
        String dataType = "";
        Stack<String> stack = new Stack<>();

        stack.push("$");
        stack.push("GV");

        int i = 0;

        String X;

        String [] words = string.split("\\s+");

        while (true) {
            X = stack.peek();

            if (isTerminal(X)) { // X is terminal

                if (X != null && Pattern.matches(X, String.valueOf(words[i]))) {
                    if (X.equals("(Ent|Cdn|Bool|Dcm)")) dataType = words[i];
                    stack.pop();
                    i++;
                } else {
                    if (X.equals("VA") && isValidType(words[i], dataType)) {
                        return ("Cadena valida");
                    }
                    return ("error");
                }
            } else { // X is not terminal
                if (getProduction(X) != null) {
                    stack.pop();
                    String production = getProduction(X);
                    String[] symbols = production.split("\\s+");
                    for (int j = symbols.length - 1; j >= 0; j--) {
                        stack.push(symbols[j]);
                    }
                } else {
                    return ("Error");
                }
            }

            if (X.equals("$")) {
                return ("La cadena es aceptada");
            }
        }
    }
}