package com.hassan.cfg2pda;

import com.hassan.cfg2pda.converter.CFGToPDAConverter;
import com.hassan.cfg2pda.parse.CFGParser;
import com.hassan.cfg2pda.simulate.PDASimulator;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class PDASimulatorTest {
    @Test
    void acceptsSimple() {
        String g = "S -> aS | ε";
        var cfg = new CFGParser().parse(g);
        var pda = new CFGToPDAConverter().convert(cfg);
        var sim = new PDASimulator();
        assertTrue(sim.run(pda, "aaa", 800).accepted);
        assertTrue(sim.run(pda, "", 800).accepted);
        assertFalse(sim.run(pda, "b", 800).accepted);
    }
}
