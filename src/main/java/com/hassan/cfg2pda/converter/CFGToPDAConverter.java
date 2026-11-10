package com.hassan.cfg2pda.converter;

import com.hassan.cfg2pda.model.*;

import java.util.stream.Collectors;

public class CFGToPDAConverter {

    public PDA convert(CFG cfg) {
        PDA pda = new PDA();
        // q0 has no incoming transitions, so the init step below can only ever
        // fire once. Without this separation the init transition is just another
        // (q, eps, $) rule, and the simulator can "replay" it whenever the stack
        // bottoms out mid-string, wrongly accepting concatenations of substrings
        // that are each individually in the language (e.g. "aa" as "a" + "a").
        String q0 = "q0";
        String q = "q";
        String qAcc = "q_accept";
        String bottom = "$";

        pda.addState(q0);
        pda.addState(q);
        pda.addState(qAcc);
        pda.setStartState(q0);
        pda.setAcceptState(qAcc);
        pda.setStackBottom(bottom);

        cfg.getTerminals().forEach(pda::addInputSymbol);
        cfg.getVariables().forEach(pda::addStackSymbol);
        cfg.getTerminals().forEach(pda::addStackSymbol);
        pda.addStackSymbol(bottom);

        // Init: (q0, ε, $) -> (q, S$) so that top is S above $
        pda.addTransition(new PDATransition(q0, PDA.EPS, bottom, cfg.getStartSymbol() + bottom, q));

        // Productions: (q, ε, A) -> (q, α)
        cfg.getProductions().forEach(pr -> {
            String A = pr.getLhs();
            String pushStr = pr.getRhs().isEmpty() ? PDA.EPS : String.join("", pr.getRhs());
            pda.addTransition(new PDATransition(q, PDA.EPS, A, pushStr, q));
        });

        // Terminals: (q, a, a) -> (q, ε)
        cfg.getTerminals().forEach(a ->
                pda.addTransition(new PDATransition(q, a, a, PDA.EPS, q))
        );

        // Accept: (q, ε, $) -> (q_accept, ε)
        pda.addTransition(new PDATransition(q, PDA.EPS, bottom, PDA.EPS, qAcc));

        return pda;
    }

    public static String prettyTransitions(PDA pda) {
        return pda.getTransitions().stream()
                .map(PDATransition::toString)
                .collect(Collectors.joining("\n"));
    }
}
