package expression.combinators;

/**
 * The division operation (/).
 */
public class Division extends BinaryFunc {
    public Division(Expression left, Expression right) { super(left, right, (x, y) -> x / y); }

    public Division clone() {
        return new Division(this.left.clone(), this.right.clone());
    }

    public String toString() {
        return "(" + this.left.toString() + "/" + this.right.toString() + ")";
    }
}
