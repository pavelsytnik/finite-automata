package pavelsytnik.automata;

import java.util.Set;
import java.util.HashSet;

public class Automaton {

    private final Set<State> states;
    private final Set<Symbol> alphabet;
    private final Set<Transition> transitions;
    private final State startState;
    private final Set<State> acceptStates;

    public Automaton(Set<State> states, Set<Symbol> alphabet, State start, Set<State> accept) {
        this.states = states;
        this.alphabet = alphabet;
        this.transitions = new HashSet<>();
        this.startState = start;
        this.acceptStates = accept;
    }
}
