package com.hassan.cfg2pda.simulate;

import com.hassan.cfg2pda.model.PDA;
import com.hassan.cfg2pda.model.PDATransition;

import java.util.*;

public class PDASimulator {

    public static class Step {
        public final int stepNo;
        public final String state;
        public final String remainingInput;
        public final String stack;
        public final String transition;

        public Step(int stepNo, String state, String remainingInput, Deque<String> stack, PDATransition t) {
            this.stepNo = stepNo;
            this.state = state;
            this.remainingInput = remainingInput;
            this.stack = String.join("", stack);
            this.transition = (t == null) ? "-" : t.toString();
        }
    }

    public static class Result {
        public final boolean accepted;
        public final List<Step> steps;

        public Result(boolean accepted, List<Step> steps) {
            this.accepted = accepted;
            this.steps = steps;
        }
    }

    public Result run(PDA pda, String input, int maxSteps) {
        List<Step> trace = new ArrayList<>();
        Deque<String> stack = new ArrayDeque<>();
        stack.push(pda.getStackBottom());
        return dfs(pda, pda.getStartState(), input, stack, trace, 0, maxSteps);
    }

    private Result dfs(PDA pda, String state, String input, Deque<String> stack, List<Step> trace, int step, int maxSteps) {
        if (step > maxSteps) return new Result(false, new ArrayList<>(trace));

        trace.add(new Step(step, state, input, stack, null));

        if (state.equals(pda.getAcceptState()) && input.isEmpty()) {
            return new Result(true, new ArrayList<>(trace));
        }

        for (PDATransition t : pda.getTransitions()) {
            if (!t.getFrom().equals(state)) continue;

            if (stack.isEmpty() || !stack.peek().equals(t.getPop())) continue;

            boolean consumes = !t.getInput().equals(PDA.EPS);
            if (consumes) {
                if (input.isEmpty() || !input.startsWith(t.getInput())) continue;
            }

            Deque<String> ns = new ArrayDeque<>(stack);
            ns.pop();

            String push = t.getPush();
            if (!push.equals(PDA.EPS) && !push.isEmpty()) {
                for (int i = push.length() - 1; i >= 0; i--) {
                    ns.push(String.valueOf(push.charAt(i)));
                }
            }

            String newInput = consumes ? input.substring(t.getInput().length()) : input;

            List<Step> nt = new ArrayList<>(trace);
            nt.add(new Step(step + 1, t.getTo(), newInput, ns, t));

            Result r = dfs(pda, t.getTo(), newInput, ns, nt, step + 1, maxSteps);
            if (r.accepted) return r;
        }

        return new Result(false, new ArrayList<>(trace));
    }
}
