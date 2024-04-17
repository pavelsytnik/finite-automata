package pavelsytnik.automata;

import java.util.Set;
import java.util.HashSet;
import java.util.Queue;
import java.util.ArrayDeque;

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

    public Set<Symbol> getStates() {
        return states;
    }

    public Set<Symbol> getAlphabet() {
        return alphabet;
    }

    public Set<Transition> getTransitions() {
        return transitions;
    }

    public Symbol getStartState() {
        return startState;
    }

    public Set<Symbol> getAcceptStates() {
        return acceptStates;
    }

    public void addTransition(Symbol current, Symbol input, Symbol next) {
        validateSymbolsForTransition(current, input, next);
        transitions.add(new Transition(current, input, next));
    }

    public Automaton toDeterministic() {

        Set<Symbol> statesStroke = new HashSet<>();
        Set<Transition> transitionsStroke = new HashSet<>();

        Queue<Set<Symbol>> queue = new ArrayDeque<>();

        Set<Symbol> currentStates;
        Set<Symbol> newStates;
        Set<Symbol> accept = new HashSet<>();

        queue.add(Set.of(startState));
        currentStates = epsilonClosure(queue.peek() != null ? queue.peek() : Set.of()); // explicit null check for compiler not to complain
        var initial = Symbol.merge(epsilonClosure(currentStates));
        statesStroke.add(initial);

        while (!queue.isEmpty()) {
            currentStates = epsilonClosure(queue.poll());

            for (var s : alphabet) {
                newStates = move(currentStates, s);

                if (!statesStroke.containsAll(epsilonClosure(newStates))) {
                    queue.add(newStates);
                    statesStroke.add(Symbol.merge(epsilonClosure(newStates)));

                    for (var e : epsilonClosure(newStates)) {
                        if (acceptStates.contains(e)) {
                            accept.add(Symbol.merge(epsilonClosure(newStates)));
                            break;
                        }
                    }
                }

                if (!newStates.isEmpty()) {
                    transitionsStroke.add(new Transition(Symbol.merge(currentStates), s, Symbol.merge(epsilonClosure(newStates))));
                }
            }
        }

        var dfa = new Automaton(statesStroke, alphabet, initial, accept);
        dfa.transitions.addAll(transitionsStroke);
        return dfa;
    }

    private Set<Symbol> epsilonClosure(Symbol state) {
        Set<Symbol> reachable = new HashSet<>();
        reachable.add(state);

        for (var t : transitions) {
            if (t.getCurrentState().equals(state) && t.getInputSymbol().equals(Symbol.EPSILON)) {
                reachable.addAll(epsilonClosure(t.getNextState()));
            }
        }

        return reachable;
    }

    private Set<Symbol> epsilonClosure(Set<Symbol> states) {
        Set<Symbol> reachable = new HashSet<>();

        for (var s : states) {
            reachable.addAll(epsilonClosure(s));
        }

        return reachable;
    }

    private Set<Symbol> move(Set<Symbol> currentStates, Symbol input) {
        Set<Symbol> nextStates = new HashSet<>();

        for (var s : currentStates) {
            for (var t : transitions) {
                if (t.getCurrentState().equals(s) && t.getInputSymbol().equals(input)) {
                    nextStates.add(t.getNextState());
                }
            }
        }

        return nextStates;
    }

    private void validateStartAndAcceptStates(Symbol start, Set<Symbol> accept) {
        boolean startError = !states.contains(start);
        boolean acceptError = !states.containsAll(accept);
        if (startError || acceptError)
            throw new IllegalArgumentException(constructErrorMessage(startError, acceptError));
    }

    private void validateSymbolsForTransition(Symbol current, Symbol input, Symbol next) {
        if (!states.contains(current) || (!input.equals(Symbol.EPSILON) && !alphabet.contains(input)) || !states.contains(next))
            throw new IllegalArgumentException("to do error message");
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
