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

        validateStartAndAcceptStates(start, accept);
        this.startState = start;
        this.acceptStates = accept;
    }

    private void validateStartAndAcceptStates(Symbol start, Set<Symbol> accept) {
        boolean startError = !states.contains(start);
        boolean acceptError = !states.containsAll(accept);
        if (startError || acceptError)
            throw new IllegalArgumentException(constructErrorMessage(startError, acceptError));
    }

    private String constructErrorMessage(boolean startError, boolean acceptError) {
        String errorMessage;

        if (startError && acceptError)
            errorMessage = "sa";
        else if (startError)
            errorMessage = "s";
        else if (acceptError)
            errorMessage = "a";
        else
            errorMessage = "";

        return errorMessage;
    }
}
