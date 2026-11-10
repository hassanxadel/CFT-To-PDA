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
}
