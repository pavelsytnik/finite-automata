package pavelsytnik.automata;

import java.util.Set;
import java.util.HashSet;

public class Main {
    public static void main(String[] args) {

        Set<Symbol> states = new HashSet<>();
        states.add(new Symbol("S"));
        states.add(new Symbol("B"));
        states.add(new Symbol("D"));
        states.add(new Symbol("E"));
        states.add(new Symbol("F"));
        states.add(new Symbol("G"));
        states.add(new Symbol("H"));
        states.add(new Symbol("J"));
        states.add(new Symbol("qf"));

        Set<Symbol> alphabet = new HashSet<>();
        alphabet.add(new Symbol("0"));
        alphabet.add(new Symbol("1"));
        alphabet.add(new Symbol("2"));
        alphabet.add(new Symbol(","));
        alphabet.add(new Symbol("+"));

        var start = new Symbol("S");

        Set<Symbol> accept = new HashSet<>();
        accept.add(new Symbol("qf"));

        var automaton = new Automaton(states, alphabet, start, accept);
        automaton.addTransition(new Symbol("S"), new Symbol("0"), new Symbol("B"));
        automaton.addTransition(new Symbol("B"), new Symbol("0"), new Symbol("B"));
        automaton.addTransition(new Symbol("B"), new Symbol("+"), new Symbol("D"));
        automaton.addTransition(new Symbol("D"), Symbol.EPSILON, new Symbol("E"));
        automaton.addTransition(new Symbol("E"), new Symbol("0"), new Symbol("F"));
        automaton.addTransition(new Symbol("F"), new Symbol(","), new Symbol("G"));
        automaton.addTransition(new Symbol("G"), new Symbol("1"), new Symbol("H"));
        automaton.addTransition(new Symbol("H"), new Symbol(","), new Symbol("J"));
        automaton.addTransition(new Symbol("J"), new Symbol("2"), new Symbol("E"));
        automaton.addTransition(new Symbol("J"), new Symbol("2"), new Symbol("qf"));

        var dfa = automaton.toDeterministic();

        printAutomaton(automaton);
        printAutomaton(dfa);
    }

    private static void printAutomaton(Automaton a) {
        System.out.println("States:");
        for (var s : a.getStates()) {
            System.out.println("\t" + s);
        }
        System.out.println();

        System.out.println("Alphabet:");
        for (var e : a.getAlphabet()) {
            System.out.println("\t" + e);
        }
        System.out.println();

        System.out.println("Initial state:");
        System.out.println("\t" + a.getStartState());
        System.out.println();

        System.out.println("Accept states:");
        for (var s : a.getAcceptStates()) {
            System.out.println("\t" + s);
        }
        System.out.println();

        System.out.println("Transitions:");
        for (var t : a.getTransitions()) {
            System.out.println("\t" + t);
        }
        System.out.println();
    }
}
