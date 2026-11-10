package com.hassan.cfg2pda.parse;

import com.hassan.cfg2pda.model.CFG;
import com.hassan.cfg2pda.model.Production;

import java.util.*;
import java.util.stream.Collectors;

public class CFGParser {

    public CFG parse(String raw) {
        String[] lines = Arrays.stream(raw.split("\n"))
                .map(String::trim)
                .filter(s -> !s.isEmpty())
                .toArray(String[]::new);

        if (lines.length == 0) throw new IllegalArgumentException("Empty grammar.");

        List<Production> productions = new ArrayList<>();
        Set<String> variables = new LinkedHashSet<>();
        Set<String> terminals = new LinkedHashSet<>();
        String start = null;

        for (String line : lines) {
            String[] parts = line.split("->");
            if (parts.length != 2) throw new IllegalArgumentException("Bad production: " + line);
            String lhs = parts[0].trim();
            if (start == null) start = lhs;
            variables.add(lhs);

            String rhsPart = parts[1].trim();
            String[] alts = rhsPart.split("\\|");
            for (String alt : alts) {
                String rhs = alt.trim();
                List<String> symbols;
                if (rhs.equals("ε") || rhs.equalsIgnoreCase("epsilon")) {
                    symbols = Collections.emptyList();
                } else {
                    symbols = tokenize(rhs);
                    for (String s : symbols) {
                        if (isVariableToken(s)) variables.add(s);
                        else terminals.add(s);
                    }
                }
                productions.add(new Production(lhs, symbols));
            }
        }

        if (start == null) throw new IllegalArgumentException("No start symbol found.");

        return new CFG(start, variables, terminals, productions);
    }

    private boolean isVariableToken(String s) {
        return s.matches("[A-Z][A-Za-z0-9_]*");
    }

    private List<String> tokenize(String rhs) {
        List<String> out = new ArrayList<>();
        int i = 0;
        while (i < rhs.length()) {
            char c = rhs.charAt(i);
            if (Character.isUpperCase(c)) {
                StringBuilder nt = new StringBuilder();
                nt.append(c);
                i++;
                while (i < rhs.length() && (Character.isDigit(rhs.charAt(i)) || rhs.charAt(i) == '_')) {
                    nt.append(rhs.charAt(i));
                    i++;
                }
                out.add(nt.toString());
            } else if (!Character.isWhitespace(c)) {
                out.add(String.valueOf(c));
                i++;
            } else {
                i++;
            }
        }
        return out.stream().filter(s -> !s.isBlank()).collect(Collectors.toList());
    }
}
