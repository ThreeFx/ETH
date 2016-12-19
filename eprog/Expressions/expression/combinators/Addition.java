package expression.combinators;

/**
 * The addition function (+).
 */
public class Addition extends BinaryFunc {
    public Addition(Expression left, Expression right) { super(left, right, (x, y) -> x + y); }

    public Addition clone() {
        return new Addition(this.left.clone(), this.right.clone());
    }

    public String toString() {
        return "(" + this.left.toString() + "+" + this.right.toString() + ")";
    }
}
