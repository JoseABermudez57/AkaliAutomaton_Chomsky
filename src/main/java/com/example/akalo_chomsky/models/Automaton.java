package com.example.akalo_chomsky.models;

import java.util.*;
import java.util.regex.Pattern;

public class Automaton {
    private static final HashMap<String, String> productions = new HashMap<>();

    static {
        productions.put("GV", "TD V I VA | TD V");
        productions.put("TD", "(Ent|Cdn|Bool|Dcm)");
        productions.put("V", "L RL");
        productions.put("L", "[a-zA-Z]+");
        productions.put("RL", "L RL | D RDL");
        productions.put("RDL", "D RD RL");
        productions.put("D", "[0-9]");
        productions.put("RD", "D RD");
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

    private static String getProduction(String symbol) {
        return productions.get(symbol);
    }

    private static boolean isTerminal(String symbol) {
        return !productions.containsKey(symbol);
    }

    public Validate evaluate (String text, String rule) {
        String X;
        int i = 0;
        Stack<String> rulesStack = new Stack<>();
        List<Stack<String>> states = new ArrayList<>();

        rulesStack.push("$");
        //stack.push(rule); Decide which standard you want to validate
        rulesStack.push(rule);
        states.add(rulesStack);
        // Clean up text and remove spaces
        String[] rules = text.trim().split("\\s+");

        while (!rulesStack.isEmpty()) {
            X = rulesStack.peek();

            if (isTerminal(X)){ // X is terminal
                // "TD V I VA | TD V"

                // Identify if separated by "|"
                if (Pattern.matches(X, ".*\\|.*\n")){
                    int k = 0;
                    String[] wordWithSeparations = rules[i].split("\\|"); // Separate by each "|"
                    for (String separation: wordWithSeparations) {
                        // Intentar cada camino por ejemplo "TD V I VA"
                        String[] words = separation.trim().split("\\s+");
                        if (Pattern.matches(X, words[k])) { // Si encuentra una coincidencia
                            rulesStack.pop();
                            states.add(rulesStack);
                            break;
                        }
                        k++;
                    }
                    return new Validate(false, states);
                } else { // If not, separate by chars
                    char[] wordWithoutSeparations = rules[i].toCharArray();
                    for (char separation: wordWithoutSeparations) {
                        if (Pattern.matches(X, String.valueOf(separation))) {
                            rulesStack.pop();
                            states.add(rulesStack);
                        } else {
                            return new Validate(false, states);
                        }
                    }
                }
            } else { // X isn't terminal
                if (!getProduction(X).isEmpty()){
                    rulesStack.pop();
                    String production = getProduction(X);
                    String[] symbols = production.split("\\s+");
                    if (Pattern.matches("\\|", production)) {
                        rulesStack.push(production);
                    } else {
                        for (int j = symbols.length - 1; j >= 0; j--){
                            rulesStack.push(symbols[j]);
                        }
                    }
                    states.add(rulesStack);
                } else {
                    return new Validate(false, states);
                }
            }
        }

        return new Validate(true, states);
    }
}