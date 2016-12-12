package expression.combinators;

import expression.evaluation.*;
import expression.exceptions.*;

import java.util.*;
import java.util.function.*;

/**
 * An abstract class for all unary operators/functions.
 */
public abstract class UnaryFunc extends Expression {
    protected Expression expr;
    protected final java.util.function.Function<Double, Double> f;

    UnaryFunc(Expression expr, java.util.function.Function<Double, Double> f) {
        this.expr = expr;
        this.f = f;
    }

    public double evaluateWith(Context context) throws EvaluationException {
        return this.f.apply(this.expr.evaluateWith(context));
    }

    public <T> Set<T> getProperty(Function<Expression, T> selector) {
        Set<T> res = new HashSet<T>();
        T prop = selector.apply(this);

        if (prop != null) res.add(prop);

        res.addAll(this.expr.getProperty(selector));
        return res;
    }

    public Expression replaceVariable(Variable var, Expression newExpr) {
        this.expr = this.expr.replaceVariable(var, newExpr);
        return this;
    }
}
