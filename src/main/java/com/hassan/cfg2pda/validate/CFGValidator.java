package com.hassan.cfg2pda.validate;

import com.hassan.cfg2pda.model.CFG;
import com.hassan.cfg2pda.model.Production;

public class CFGValidator {
    public void validate(CFG cfg) {
        if (!cfg.getVariables().contains(cfg.getStartSymbol()))
            throw new IllegalArgumentException("Start symbol is not in variables.");

        for (Production p : cfg.getProductions()) {
            if (!cfg.getVariables().contains(p.getLhs()))
                throw new IllegalArgumentException("LHS not a variable: " + p.getLhs());
        }
    }
}
