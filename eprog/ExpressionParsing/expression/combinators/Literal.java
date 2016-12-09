package expression.combinators;

import expression.evaluation.Context;

import java.util.*;
import java.util.function.*;

/**
 * A literal value. Always a double.
 */
public class Literal extends Atom {
    double value;
    public Literal(double value) {
        this.value = value;
    }

    public double evaluateWith(Context context) {
        return this.value;
    }

    public <T> Set<T> getProperty(Function<Expression, T> selector) {
        T prop = selector.apply(this);
        Set<T> res = new HashSet<T>();

        if (prop != null) res.add(prop);

        return res;
    }

    public Expression replaceVariable(String varName, Expression expression) {
        return this;
    }

    public Literal clone() {
        return new Literal(this.value);
    }

    public String toString() {
        return this.value + "";
    }
}
