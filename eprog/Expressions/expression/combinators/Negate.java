package expression.combinators;

/**
 * The negation function. Negates its argument.
 */
public class Negate extends UnaryFunc {
    public Negate(Expression expr) { super(expr, x -> -x); }

    public Negate clone() {
        return new Negate(this.expr.clone());
    }

    public String toString() {
        return "-" + this.expr.toString();
    }
}
