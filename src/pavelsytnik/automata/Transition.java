package pavelsytnik.automata;

public class Transition {

    private final Symbol currentState;
    private final Symbol inputSymbol;
    private final Symbol nextState;

    public Transition(Symbol current, Symbol input, Symbol next) {
        currentState = current;
        inputSymbol = input;
        nextState = next;
    }
}
