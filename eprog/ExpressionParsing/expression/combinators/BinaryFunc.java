package expression.combinators;

import expression.evaluation.*;
import expression.exceptions.*;

import java.util.*;
import java.util.function.*;

/**
 * An abstract description of a binary function.
 */
public abstract class BinaryFunc extends Expression {
    protected Expression left, right;
    protected final BiFunction<Double, Double, Double> f;

    BinaryFunc(Expression left, Expression right, BiFunction<Double, Double, Double> f) {
        this.left = left;
        this.right = right;
        this.f = f;
    }

    public double evaluateWith(Context context) throws EvaluationException {
        return this.f.apply(left.evaluateWith(context), right.evaluateWith(context));
    }

    public <T> Set<T> getProperty(Function<Expression, T> selector) {
        Set<T> res = new HashSet<T>();
        T prop = selector.apply(this);

        if (prop != null) res.add(prop);

        res.addAll(this.left.getProperty(selector));
        res.addAll(this.right.getProperty(selector));
        return res;
    }

    /**
     * Recusively replace var in all subexpressions. Stateful.
     */
    public Expression replaceVariable(Variable var, Expression newExpr) {
        this.left = this.left.replaceVariable(var, newExpr);
        this.right = this.right.replaceVariable(var, newExpr);
        return this;
    }
}
