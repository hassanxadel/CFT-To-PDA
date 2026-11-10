package com.hassan.cfg2pda.model;

import java.util.*;

public class CFG {
    private final String startSymbol;
    private final Set<String> variables;
    private final Set<String> terminals;
    private final List<Production> productions;

    public CFG(String startSymbol, Set<String> variables, Set<String> terminals, List<Production> productions) {
        this.startSymbol = startSymbol;
        this.variables = variables;
        this.terminals = terminals;
        this.productions = productions;
    }

    public String getStartSymbol() { return startSymbol; }
    public Set<String> getVariables() { return variables; }
    public Set<String> getTerminals() { return terminals; }
    public List<Production> getProductions() { return productions; }
}
