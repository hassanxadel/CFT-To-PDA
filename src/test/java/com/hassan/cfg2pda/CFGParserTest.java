package com.hassan.cfg2pda;

import com.hassan.cfg2pda.model.CFG;
import com.hassan.cfg2pda.parse.CFGParser;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class CFGParserTest {
    @Test
    void parsesSimple() {
        String g = "S -> aA | b\nA -> aAa | ε";
        CFG cfg = new CFGParser().parse(g);
        assertEquals("S", cfg.getStartSymbol());
        assertTrue(cfg.getVariables().contains("A"));
        assertTrue(cfg.getTerminals().contains("a"));
        assertTrue(cfg.getTerminals().contains("b"));
        assertEquals(4, cfg.getProductions().size());
    }

    @Test
    void splitsTerminalNonterminalTerminalWithoutMerging() {
        // "aAa" must tokenize as a, A, a (not a, Aa) so a single uppercase
        // letter is always its own nonterminal unless followed by digits/underscore.
        CFG cfg = new CFGParser().parse("A -> aAa | ε");
        var rhs = cfg.getProductions().get(0).getRhs();
        assertEquals(java.util.List.of("a", "A", "a"), rhs);
        assertFalse(cfg.getVariables().contains("Aa"));
    }
}
