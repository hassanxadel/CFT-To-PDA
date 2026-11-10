package com.hassan.cfg2pda.model;

import java.util.*;

public class PDA {
    public static final String EPS = "ε";

    private final Set<String> states = new LinkedHashSet<>();
    private final Set<String> inputAlphabet = new LinkedHashSet<>();
    private final Set<String> stackAlphabet = new LinkedHashSet<>();
    private final List<PDATransition> transitions = new ArrayList<>();
    private String startState;
    private String acceptState;
    private String stackBottom;

    public void addState(String s) { states.add(s); }
    public void addInputSymbol(String a) { inputAlphabet.add(a); }
    public void addStackSymbol(String g) { stackAlphabet.add(g); }
    public void addTransition(PDATransition t) { transitions.add(t); }

    public Set<String> getStates() { return states; }
    public Set<String> getInputAlphabet() { return inputAlphabet; }
    public Set<String> getStackAlphabet() { return stackAlphabet; }
    public List<PDATransition> getTransitions() { return transitions; }
    public String getStartState() { return startState; }
    public String getAcceptState() { return acceptState; }
    public String getStackBottom() { return stackBottom; }

    public void setStartState(String s) { this.startState = s; }
    public void setAcceptState(String a) { this.acceptState = a; }
    public void setStackBottom(String b) { this.stackBottom = b; }
}
