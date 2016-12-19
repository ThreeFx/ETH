package expression.combinators;

/**
 * The subtraction function (-).
 */
public class Subtraction extends BinaryFunc {
    public Subtraction(Expression left, Expression right) { super(left, right, (x, y) -> x - y); }

    public Subtraction clone() {
        return new Subtraction(this.left.clone(), this.right.clone());
    }

    public String toString() {
        return "(" + this.left.toString() + "-" + this.right.toString() + ")";
    }
}
