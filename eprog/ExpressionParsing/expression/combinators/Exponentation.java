package expression.combinators;

/**
 * The exponentation operation (^).
 */
public class Exponentation extends BinaryFunc {
    public Exponentation(Expression left, Expression right) {
        super(left, right, (x, y) -> Math.pow(x, y));
    }

    public Exponentation clone() {
        return new Exponentation(this.left.clone(), this.right.clone());
    }

    public String toString() {
        return "(" + this.left.toString() + "^" + this.right.toString() + ")";
    }
}
