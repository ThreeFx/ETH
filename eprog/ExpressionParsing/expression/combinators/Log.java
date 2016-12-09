package expression.combinators;

/**
 * The logarithm function. Base e.
 */
public class Log extends UnaryFunc {
    public Log(Expression expr) { super(expr, x -> Math.log(x)); }
    
    public Log clone() {
        return new Log(this.expr.clone());
    }

    public String toString() {
        return "log(" + this.expr.toString() + ")";
    }
}
