package com.hassan.cfg2pda.model;

import java.util.List;

public class Production {
    private final String lhs;
    private final List<String> rhs;

    public Production(String lhs, List<String> rhs) {
        this.lhs = lhs;
        this.rhs = rhs;
    }

    public String getLhs() { return lhs; }
    public List<String> getRhs() { return rhs; }

    @Override
    public String toString() {
        if (rhs.isEmpty()) return lhs + " -> ε";
        return lhs + " -> " + String.join("", rhs);
    }
}
