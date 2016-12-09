package expression.combinators;

/**
 * The cosine function (cos).
 */
public class Cos extends UnaryFunc {
    public Cos(Expression expr) { super(expr, x -> Math.cos(x)); }

    public Cos clone() {
        return new Cos(this.expr.clone());
    }

    public String toString() {
        return "cos(" + this.expr.toString() + ")";
    }
}
