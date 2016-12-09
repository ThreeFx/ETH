package expression.combinators;

/**
 * The sine function (sin).
 */
public class Sin extends UnaryFunc {
    public Sin(Expression expr) { super(expr, x -> Math.sin(x)); }

    public Sin clone() {
        return new Sin(this.expr.clone());
    }

    public String toString() {
        return "sin(" + this.expr.toString() + ")";
    }
}
