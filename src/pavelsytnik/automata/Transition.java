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

    public Symbol getCurrentState() {
        return currentState;
    }

    public Symbol getInputSymbol() {
        return inputSymbol;
    }

    public Symbol getNextState() {
        return nextState;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this)
            return true;
        if (obj == null || obj.getClass() != this.getClass())
            return false;

        var transition = (Transition) obj;

        return transition.currentState.equals(this.currentState)
            && transition.inputSymbol.equals(this.inputSymbol)
            && transition.nextState.equals(this.nextState);
    }

    @Override
    public int hashCode() {
        int result = 17;
        result = 31 * result + currentState.hashCode();
        result = 31 * result + inputSymbol.hashCode();
        result = 31 * result + nextState.hashCode();
        return result;
    }

    @Override
    public String toString() {
        return currentState.toString() + " --" + inputSymbol.toString()
            + "-> " + nextState.toString();
    }
}
