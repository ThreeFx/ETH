package expression.combinators;

/**
 * The multiplication function (*).
 */
public class Multiplication extends BinaryFunc {
    public Multiplication(Expression left, Expression right) { super(left, right, (x, y) -> x * y); }

    public Multiplication clone() {
        return new Multiplication(this.left.clone(), this.right.clone());
    }

    public String toString() {
        return "(" + this.left.toString() + "*" + this.right.toString() + ")";
    }
}
