package pavelsytnik.automata;

import java.util.Set;
import java.util.HashSet;

public class Automaton {

    private final Set<Symbol> states;
    private final Set<Symbol> alphabet;
    private final Set<Transition> transitions;
    private final Symbol startState;
    private final Set<Symbol> acceptStates;

    public Automaton(Set<Symbol> states, Set<Symbol> alphabet, Symbol start, Set<Symbol> accept) {
        this.states = states;
        this.alphabet = alphabet;
        this.transitions = new HashSet<>();
        this.startState = start;
        this.acceptStates = accept;
    }
}
