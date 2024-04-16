package pavelsytnik.automata;

public class Symbol {

    public static final Symbol EPSILON = new Symbol("");

    private final String sym;

    public Symbol(String sym) {
        this.sym = sym;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this)
            return true;
        if (obj == null || obj.getClass() != this.getClass())
            return false;

        Symbol symbol = (Symbol) obj;
        return symbol.sym.equals(this.sym);
    }

    @Override
    public int hashCode() {
        int result = 17;
        result = 31 * result + sym.hashCode();
        return result;
    }

    @Override
    public String toString() {
        return sym;
    }
}
