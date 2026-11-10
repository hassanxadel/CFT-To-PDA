package com.hassan.cfg2pda;

import com.hassan.cfg2pda.converter.CFGToPDAConverter;
import com.hassan.cfg2pda.parse.CFGParser;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class ConverterTest {
    @Test
    void buildsTransitions() {
        String g = "S -> aA | b\nA -> aAa | ε";
        var cfg = new CFGParser().parse(g);
        var pda = new CFGToPDAConverter().convert(cfg);
        assertTrue(pda.getTransitions().size() >= 1);
        assertEquals("q0", pda.getStartState());
        assertEquals("q_accept", pda.getAcceptState());
    }
}
