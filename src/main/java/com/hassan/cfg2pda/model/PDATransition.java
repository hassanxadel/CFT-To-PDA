package com.hassan.cfg2pda.model;

public class PDATransition {
    private final String from;
    private final String input;
    private final String pop;
    private final String push;
    private final String to;

    public PDATransition(String from, String input, String pop, String push, String to) {
        this.from = from;
        this.input = input;
        this.pop = pop;
        this.push = push;
        this.to = to;
    }

    public String getFrom() { return from; }
    public String getInput() { return input; }
    public String getPop() { return pop; }
    public String getPush() { return push; }
    public String getTo() { return to; }

    @Override
    public String toString() {
        return "(" + from + ", " + input + ", " + pop + ") → (" + to + ", " + push + ")";
    }
}
