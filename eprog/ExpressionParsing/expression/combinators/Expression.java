/*
 * Author: Ben Fiedler
 * for ItP1.
 *
 * Expression.java
 * The abstract base class for expressions.
 */

package expression.combinators;

import expression.evaluation.Context;
import expression.exceptions.*;

import java.util.*;
import java.util.function.*;

/**
 * An abstract representation of an expression.
 * It must support evaluation.
 */
public abstract class Expression {
    public double evaluate() throws EvaluationException {
        return this.evaluateWith(new Context());
    }

    /**
     * Evaluates the expression downward using the current context.
     * The context stores information such as custom function definitions
     * and variable values.
     */
    public abstract double evaluateWith(Context context) throws EvaluationException;

    /**
     * Returns a set of non-null properties picked by the selector function.
     */
    public abstract <T> Set<T> getProperty(Function<Expression, T> selector);

    /**
     * Replaces all occurrences of Variable(varName) with a clone of the expression.
     * Returns the modified expression.
     */
    public abstract Expression replaceVariable(Variable var, Expression newExpr);

    /**
     * Returns a deep copy of the current expression.
     */
    public abstract Expression clone();
}
