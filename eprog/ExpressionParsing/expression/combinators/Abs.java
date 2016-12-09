package expression.combinators;

/**
 * The absolute value function (abs).
 */
public class Abs extends UnaryFunc {
    public Abs(Expression expr) { super(expr, x -> Math.abs(x)); }

    public Abs clone() {
        return new Abs(this.expr.clone());
    }

    public String toString() {
        return "abs(" + this.expr.toString() + ")";
    }
}
